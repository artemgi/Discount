package com.gizzatullin.discount.service;

import com.gizzatullin.discount.controller.exceptions.SaleRegistrationException;
import com.gizzatullin.discount.dao.ClientRepository;
import com.gizzatullin.discount.dao.HourDiscountRepository;
import com.gizzatullin.discount.dao.ProductRepository;
import com.gizzatullin.discount.dao.SaleRepository;
import com.gizzatullin.discount.model.HourDiscount;
import com.gizzatullin.discount.model.client.Client;
import com.gizzatullin.discount.model.product.Product;
import com.gizzatullin.discount.model.sale.Sale;
import com.gizzatullin.discount.model.sale.SalePosition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService {
	static final String CLIENT_DEFAULT = "artem";
	private final SaleRepository saleRepository;
	private final ClientRepository clientRepository;
	private final ProductRepository productRepository;
	private final HourDiscountRepository hourDiscountRepository;

	public String registrationSale(Map<String, String> items, String totalCost) throws SaleRegistrationException {
		Sale sale = new Sale();
		Optional<Client> clientOptional = clientRepository.findByName(CLIENT_DEFAULT);
		if (clientOptional.isPresent()) {
			sale.setClient(clientOptional.get());
		} else {
			Client client = new Client();
			client.setName(CLIENT_DEFAULT);
			sale.setClient(client);
		}
		sale.setSaleDate(LocalDateTime.now());
		List<SalePosition> salePositions = new ArrayList<>();
		for (Map.Entry<String, String> product : items.entrySet()) {
			Optional<Product> productOptional = productRepository.findById(Long.valueOf(product.getKey()));
			if (productOptional.isPresent()) {
				Product foundProduct = productOptional.get();
				Integer quantity = Integer.valueOf(product.getValue());
				SalePosition salePosition = new SalePosition();
				salePosition.setProduct(foundProduct);
				salePosition.setQuantity(quantity);
				salePosition.setInitialPrice(foundProduct.getPrice());
				if (BigDecimal.valueOf(Double.parseDouble(totalCost)).compareTo(getFinalPrice(foundProduct, quantity)) != 0) {
					throw new SaleRegistrationException("Итоговая стоимость не соответствует рассчитанной на момент регистрации продажи");
				}
				salePosition.setFinalPrice(new BigDecimal(0));
				salePosition.setDiscount(new BigDecimal(0));
				salePositions.add(salePosition);
			} else {
				log.error(String.format("Продукт с ID %s не найден", product.getKey()));
			}

		}
		sale.setItems(salePositions);
		Optional<Sale> lastSalesOptional = saleRepository.findFirstByOrderByIdDesc();
		String receiptNumber = "00100";
		if (lastSalesOptional.isPresent()) {
			Sale lastSales = lastSalesOptional.get();
			if (lastSales.getSaleDate().toLocalDate().equals(LocalDate.now())) {
				receiptNumber = incriminateStringReceipt(lastSales.getReceiptNumber());
			}
		}
		sale.setReceiptNumber(receiptNumber);
		saleRepository.save(sale);
		return sale.getReceiptNumber();
	}

	public BigDecimal getFinalPrice(Product product, Integer quantityProducts) {
		Optional<HourDiscount> byProductIdOrderByEndDiscount = hourDiscountRepository.findDiscountHours(product.getId());
		BigDecimal amount = new BigDecimal(0);
		amount = amount
				.add(product.getPrice()
						.multiply(BigDecimal.valueOf(quantityProducts)));

		if (byProductIdOrderByEndDiscount.isPresent()) {
			amount = amount
					.multiply(BigDecimal.valueOf(1 - 0.01 * byProductIdOrderByEndDiscount.get().getPercentDiscount()));
		}
		return amount;
	}

	public String incriminateStringReceipt(String receipt) {
		DecimalFormat df = new DecimalFormat("00000");
		int number = Integer.parseInt(receipt);
		number++;
		return df.format(number);
	}
}
