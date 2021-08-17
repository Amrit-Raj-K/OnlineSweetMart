package com.cg.osm.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.exception.SweetOrderNotFoundException;
import com.cg.osm.repository.SweetOrderRepository;



@Service
public class SweetOrderServiceImpl implements SweetOrderService {
	@Autowired
	private SweetOrderRepository sweetOrderRepository;

	SweetOrderServiceImpl(SweetOrderRepository sweetOrderRepository) {
		this.sweetOrderRepository = sweetOrderRepository;
	}

	@Override
	public SweetOrder addSweetOrder(SweetOrder sweetOrder) {
		SweetOrder order = sweetOrderRepository.saveAndFlush(sweetOrder);
		return order;
	}

	@Override
	public SweetOrder updateSweetOrder(SweetOrder sweetOrder) throws SweetOrderNotFoundException {
		SweetOrder order = sweetOrderRepository.saveAndFlush(sweetOrder);
		return order;
	}

	@Override
	public SweetOrder cancelSweetOrder(int sweetOrderId) throws SweetOrderNotFoundException {
		SweetOrder order = sweetOrderRepository.findById(sweetOrderId)
				.orElseThrow(() -> new SweetOrderNotFoundException("Cannot delete an empty or null value"));
		sweetOrderRepository.deleteById(sweetOrderId);
		return order;
	}

	@Override
	public List<SweetOrder> showAllSweetOrders() {
		return sweetOrderRepository.findAll();
	}

	@Override
	public SweetOrder findOrder(int sweetOrderId) throws SweetOrderNotFoundException {

		return sweetOrderRepository.findById(sweetOrderId)
				.orElseThrow(() -> new SweetOrderNotFoundException("Can not find sweet order for id " + sweetOrderId));

	}

}
