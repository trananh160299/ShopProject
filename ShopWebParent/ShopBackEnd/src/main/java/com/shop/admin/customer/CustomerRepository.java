package com.shop.admin.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shop.common.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	
	@Query("SELECT c FROM Customer c WHERE CONCAT(c.email, ' ', c.firstName, ' ',"
			+ "c.lastName, ' ', c.addressDetail, ' ', c.city, ' ',"
			+ "c.state, ' ', c.country.name, ' ', c.postalCode) LIKE %?1%")
	public Page<Customer> findAll(String keyword, Pageable pageable);
	
	@Query("UPDATE Customer c SET c.enabled = ?2 WHERE c.id = ?1")
	@Modifying
	public void updateEnableStatus(Integer id, boolean enabled);
	
	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	public Customer findByEmail(String email);
	
	public Long countById(Integer id);

}
