package com.cg.osm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.SweetItemNotFoundException;

public interface SweetItemService {
	public List<SweetItem> addSweetItem(SweetItem sweetItem)throws SweetItemNotFoundException;// Adding sweet item
	public List<SweetItem> updateSweetItem(SweetItem sweetItem)throws SweetItemNotFoundException;//Updating the sweet item
	public  List<SweetItem>  cancelSweetItem(int sweetItemId) throws SweetItemNotFoundException;//Deleting the sweet item
	public List<SweetItem> showAllSweetItems();                                                 // Show the list of sweet items
	public SweetItem findSweet(Integer id)throws SweetItemNotFoundException;                    //Find sweet item by id
	public SweetItem saveImage(MultipartFile file);
}
