package com.shop.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shop.common.entity.product.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {
	
	@Autowired ProductRepository repo;
	
	@Test
	public void testFindByAlias() {
		String alias = "Acer-Aspire-TC-885-UA92-Desktop";
		Product product = repo.findByAlias(alias);
		
		assertThat(product).isNotNull();

	}

}
