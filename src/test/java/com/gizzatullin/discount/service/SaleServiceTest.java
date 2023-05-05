package com.gizzatullin.discount.service;

import com.gizzatullin.discount.dao.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Service
@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
	@Mock
	private SaleService underTest;
	@InjectMocks
	private ClientRepository clientRepository;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void registrationSale() {
		//Given

		//When


		//Then
	}
}