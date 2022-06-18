package com.checkout.checkout.service.impl;

import java.text.DecimalFormat;
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
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderGameCoinDetailClient orderGameCoinDetailClient;

    private static final String CLIENT_ID = "ATOnCqVzaLmmpPuKYqx7LwgSVefMJWgs-CZLcL1N21lO7XVJ7VHcOFMvmqx3ucZ2kcUaq5QhKSYQ--Y6";
    private static final String CLIENT_SECRET = "EKDcn2wb8GG9hjKvg25DeVvvZcwZ-RWoOvUjZwsGzoBSMMFiQJMcHAnaSTLJ4bsjm7zL4Q-vBLu216WC";
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
        redirectUrls.setCancelUrl("http://localhost:8112/captures/cancel");
        redirectUrls.setReturnUrl("http://localhost:8112/captures/accept");
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
        System.out.println(listTransaction);
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

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
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
        DecimalFormat df = new DecimalFormat("#.##");
        var total = String.valueOf(df.format(orderDetail.getTotal()));

        List<DetailGameCoinResponse> detailGameCoin = orderDetail.getLCoinResponses();

        Details details = new Details();
        details.setSubtotal(total);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total);
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("product of replavigame");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();

        for (DetailGameCoinResponse productDetail : detailGameCoin) {

            String price = String.valueOf(df.format(productDetail.getGameCoin().getPrice()));

            Item item = new Item();
            item.setCurrency("USD")
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
