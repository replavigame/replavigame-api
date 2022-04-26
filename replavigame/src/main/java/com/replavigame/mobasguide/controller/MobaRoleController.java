package com.replavigame.mobasguide.controller;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaRoleRequest;
import com.replavigame.mobasguide.dto.MobaRoleResponse;
import com.replavigame.mobasguide.service.MobaRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobaroles")
public class MobaRoleController {

    @Autowired
    private MobaRoleService mobaRoleService;

    @GetMapping
    private ResponseEntity<List<MobaRoleResponse>> getAll() {
        var response = mobaRoleService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<MobaRoleResponse> getById(@PathVariable(name = "id") Long id) {
        var response = mobaRoleService.getByMobaRoleById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<MobaRoleResponse> create(@RequestBody MobaRoleRequest request) {
        var response = mobaRoleService.createMobaRole(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<MobaRoleResponse> update(@RequestBody MobaRoleRequest request,
            @PathVariable(name = "id") Long id) {
        var response = mobaRoleService.updateMobaRole(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        mobaRoleService.deleteMobaRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
