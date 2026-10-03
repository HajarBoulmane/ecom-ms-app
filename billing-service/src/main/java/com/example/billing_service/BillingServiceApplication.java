package com.example.billing_service;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.repositories.BillRepository;
import com.example.billing_service.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository) {
		return args -> {
			List<Long> customerIds = List.of(1L, 2L, 3L);
			List<Long> productIds = List.of(1L, 2L, 3L);
			Random random = new Random();

			customerIds.forEach(customerId -> {
				Bill bill = billRepository.save(Bill.builder()
						.billingDate(new Date())
						.customerId(customerId)
						.build());

				productIds.forEach(productId -> productItemRepository.save(
						ProductItem.builder()
								.bill(bill)
								.productId(productId)
								.quantity(1 + random.nextInt(20))
								.price(1000 + random.nextDouble() * 600)
								.build()));
			});
		};
	}
}