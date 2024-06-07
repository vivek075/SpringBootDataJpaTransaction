package com.example.transaction.controller;

import com.example.transaction.service.PurchaseService;
import com.example.transaction.service.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
@ActiveProfiles("test")
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseProduct_Success() throws Exception {
        // Arrange
        Long customerId = 1L;
        Long productId = 1L;

        // Act & Assert
        mockMvc.perform(post("/api/purchase/{customerId}/{productId}", customerId, productId))
                .andExpect(status().isOk())
                .andExpect(content().string("Purchase successful"));

        verify(purchaseService).purchaseProduct(customerId, productId);
    }

    @Test
    public void testPurchaseProduct_InsufficientFunds() throws Exception {
        // Arrange
        Long customerId = 1L;
        Long productId = 1L;

        doThrow(new InsufficientFundsException("Insufficient funds for the purchase")).when(purchaseService).purchaseProduct(customerId, productId);

        // Act & Assert
        mockMvc.perform(post("/api/purchase/{customerId}/{productId}", customerId, productId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Insufficient funds for the purchase"));

        verify(purchaseService).purchaseProduct(customerId, productId);
    }

    @Test
    public void testPurchaseProduct_InternalServerError() throws Exception {
        // Arrange
        Long customerId = 1L;
        Long productId = 1L;

        doThrow(new RuntimeException("An error occurred")).when(purchaseService).purchaseProduct(customerId, productId);

        // Act & Assert
        mockMvc.perform(post("/api/purchase/{customerId}/{productId}", customerId, productId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred"));

        verify(purchaseService).purchaseProduct(customerId, productId);
    }
}
