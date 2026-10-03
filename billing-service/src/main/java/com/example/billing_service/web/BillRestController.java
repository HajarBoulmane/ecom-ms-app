package com.example.billing_service.web;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.feign.CustomerServiceRestClient;
import com.example.billing_service.feign.InventoryServiceRestClient;
import com.example.billing_service.repositories.BillRepository;
import com.example.billing_service.repositories.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BillRestController {

    @Autowired
    private final BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private  CustomerServiceRestClient customerRestClient;
    @Autowired
    private  InventoryServiceRestClient inventoryRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElseThrow();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi ->
                pi.setProduct(inventoryRestClient.findProductById(pi.getProductId())));
        return bill;
    }
}