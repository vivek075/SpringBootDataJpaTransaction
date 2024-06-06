package com.example.transaction.controller;

import com.example.transaction.service.PurchaseService;
import com.example.transaction.service.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{customerId}/{productId}")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long customerId, @PathVariable Long productId) {
        try {
            purchaseService.purchaseProduct(customerId, productId);
            return new ResponseEntity<>("Purchase successful", HttpStatus.OK);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
