package com.example.billing_service.feign;

import com.example.billing_service.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getDefaultProduct")
    Product findProductById(@PathVariable("id") Long id);


    default Product getDefaultProduct(Long id, Exception e) {
        e.printStackTrace();
        return Product.builder().id(id).build();
    }
}