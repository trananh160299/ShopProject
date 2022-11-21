package com.shop.admin.order;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.common.entity.order.Order;

@Service
public class OrderService {

	public static final int ORDERS_PER_PAGE = 10;
	
	@Autowired private OrderRepository repo;
	
	public Page<Order> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = null;
		if("destination".equals(sortField)) {
			sort = Sort.by("country").and(Sort.by("state")).and(Sort.by("city"));
		} else {
			sort = Sort.by(sortField);
		}
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, ORDERS_PER_PAGE, sort);
		
		if(keyword != null) {
			return repo.findAll(keyword, pageable);
		}
		return repo.findAll(pageable);
	}
	
	public Order get(Integer id) throws OrderNotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new OrderNotFoundException("Không tìm thấy hóa đơn nào có ID: " +id);
		}
	}
	
	public void delete(Integer id) throws OrderNotFoundException {
		Long countById = repo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new OrderNotFoundException("Không tìm thấy hóa đơn nào có ID: " +id);
		}
		
		repo.deleteById(id);
	}
}