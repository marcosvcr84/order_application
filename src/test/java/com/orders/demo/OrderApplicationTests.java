package com.orders.demo;

import com.orders.demo.dto.OrderRequest;
import com.orders.demo.model.Order;
import com.orders.demo.repository.OrderRepository;
import com.orders.demo.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@Test
	public void createOrderTest() {
		long controlNumber = 123456L;

		OrderRequest request = new OrderRequest();
		request.setControlNumber(controlNumber);
		request.setProductName("Test Product");
		request.setUnitPrice(100.0);
		request.setQuantity(2);
		request.setClientId(1);

		HttpEntity<OrderRequest> entity = new HttpEntity<>(request);

		ResponseEntity<Order> response = restTemplate.exchange(
				"http://localhost:" + port + "/api/orders",
				HttpMethod.POST, entity, Order.class);

		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(controlNumber, response.getBody().getControlNumber());
	}

	@Test
	public void getOrderByControlNumberTest() {
		Optional<Order> order = orderRepository.findByControlNumber(123456L);
		assertNotNull(order);
	}

	@AfterEach
	public void tearDown() {
		orderService.deleteOrderByControlNumber(123456L);
	}
}
