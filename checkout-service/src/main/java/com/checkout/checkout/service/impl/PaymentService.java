package com.checkout.checkout.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.checkout.checkout.client.OrderGameCoinDetailClient;
import com.checkout.checkout.dto.OrderDetailGameCoinRequest;
import com.checkout.checkout.model.DetailGameCoinResponse;
import com.checkout.checkout.model.OrderDetailGameCoinPayment;
import com.checkout.exception.ResourceNotFoundExceptionRequest;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {

    @Autowired
    private OrderGameCoinDetailClient orderGameCoinDetailClient;

    private static final String CLIENT_ID = "Ab3iwLdPmG9dnZWw44EyEk0RHPB4oD75SIrmK-m8ByJuClk4TEshK-kHI-qnQHSwlvu5GQx8McErINj_";
    private static final String CLIENT_SECRET = "EATEnr0GzzQDygMb2Rbab7nA2My_4PqrqDIFRNS2u8ewB4miMMo4ngiEkhIPtoJ58K8Q7fAud3TBEhjp";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetailGameCoinRequest request) throws PayPalRESTException {

        var orderDetail = orderGameCoinDetailClient.create(request).getBody();

        if (orderDetail == null) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred when creating order detail");
        }

        var user = orderDetail.getUser();

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(user.getName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail());

        payer.setPayerInfo(payerInfo);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("cancel");
        redirectUrls.setReturnUrl("return");

        List<Transaction> listTransaction = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction)
                .setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment);

        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }

        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDetailGameCoinPayment orderDetail) {
        String subTotal = String.format("%.2f", orderDetail.getTotal());
        List<DetailGameCoinResponse> detailGameCoin = orderDetail.getLCoinResponses();

        Details details = new Details();
        details.setSubtotal(subTotal);

        Amount amount = new Amount();
        amount.setCurrency("PE");
        amount.setTotal(subTotal);
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("product of replavigame");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();

        for (DetailGameCoinResponse productDetail : detailGameCoin) {

            String price = String.format("%.2f", productDetail.getSubtotal());

            Item item = new Item();
            item.setCurrency("PE")
                    .setName(productDetail.getGameCoin().getName())
                    .setPrice(price)
                    .setQuantity(productDetail.getQuantify().toString());

            items.add(item);
        }
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<Transaction>();
        listTransaction.add(transaction);

        return listTransaction;
    }
}
