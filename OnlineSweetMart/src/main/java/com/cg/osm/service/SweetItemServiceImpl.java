package com.cg.osm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.SweetItemNotFoundException;
import com.cg.osm.repository.SweetItemRepo;

@Service
public class SweetItemServiceImpl implements SweetItemService { //Sweet Item Service layer
	@Autowired
	private SweetItemRepo sweet; // Object of sweet item repository which is autowired to service layer

	@Override
	public List<SweetItem> addSweetItem(SweetItem sweetItem) throws SweetItemNotFoundException {
		// TODO Auto-generated method stub
		if (sweet.findById(sweetItem.getSweetItemId()).isEmpty()) {
			sweet.saveAndFlush(sweetItem);
			return sweet.findAll();
		}

		throw new SweetItemNotFoundException("Sorry,sweet item id already exists");
	}

	@Override
	public List<SweetItem> updateSweetItem(SweetItem sweetItem) {
		// TODO Auto-generated method stub
		sweet.saveAndFlush(sweetItem);
		return sweet.findAll();
	}

	@Override
	public List<SweetItem> cancelSweetItem(int sweetItemId) throws SweetItemNotFoundException {
		// TODO Auto-generated method stub
		sweet.findById(sweetItemId)
				.orElseThrow(() -> new SweetItemNotFoundException("Cannot delete an empty or null value"));
		sweet.deleteById(sweetItemId);
		return sweet.findAll();
	}

	@Override
	public List<SweetItem> showAllSweetItems() {
		// TODO Auto-generated method stub
		return sweet.findAll();
	}

	@Override
	public SweetItem findSweet(Integer id) throws SweetItemNotFoundException {
		// TODO Auto-generated method stub
		Optional<SweetItem> sweets = Optional.ofNullable(sweet.findById(id)
				.orElseThrow(() -> new SweetItemNotFoundException("Cannot update a non existing value")));
		return sweets.get();
	}

	  public SweetItem saveImage(MultipartFile file) {
		  String docname = file.getOriginalFilename();
	      SweetItem sweet=null;
	      sweet.setImage(docname);
		 
		  return sweet;
	  }
}
