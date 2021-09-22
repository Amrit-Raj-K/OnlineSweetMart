package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.SweetItemNotFoundException;
import com.cg.osm.service.SweetItemService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SweetItemController {

	@Autowired
	private SweetItemService sweetservice;

	@GetMapping("/sweetitem")
	public ResponseEntity<List<SweetItem>> showAllSweetItems() {
		List<SweetItem> sw = sweetservice.showAllSweetItems();
		if (sw.isEmpty()) {
			return new ResponseEntity("Sorry! Sweet Item is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SweetItem>>(sw, HttpStatus.OK);

	}
	
	@GetMapping("/sweetitem/{id}")
	public ResponseEntity<SweetItem> showAllSweetItemsById(@PathVariable("id") Integer id) throws SweetItemNotFoundException {
		SweetItem sw = sweetservice.findSweet(id);
		if (sw==null) {
			return new ResponseEntity("Sorry! Sweet Item is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SweetItem>(sw, HttpStatus.OK);

	}
	
	@PostMapping("/sweetitem")
	public ResponseEntity<List<SweetItem>> insertProduct(@RequestBody SweetItem sweetItem)
			throws SweetItemNotFoundException {
		try {
			List<SweetItem> sw = sweetservice.addSweetItem(sweetItem);
			if (sw.isEmpty()) {
				return new ResponseEntity("Sorry! Sweet Item not available!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<SweetItem>>(sw, HttpStatus.OK);
		} catch (Exception e) {
			throw new SweetItemNotFoundException(e.getMessage());
		}
	}

	@DeleteMapping("/sweetitem/{id}")
	public ResponseEntity<List<SweetItem>> deleteProduct(@PathVariable("id") Integer id)
			throws SweetItemNotFoundException {
		try {
			SweetItem sew=sweetservice.findSweet(id);
			sew.setCategory(null);
			sweetservice.updateSweetItem(sew);
			List<SweetItem> swe = sweetservice.cancelSweetItem(id);
			return new ResponseEntity<List<SweetItem>>(swe, HttpStatus.OK);
		} catch (Exception e) {
			throw new SweetItemNotFoundException(e.getMessage());
		}
	}

	@PutMapping("/sweetitem/{id}")
	public ResponseEntity<List<SweetItem>> updateProduct(@PathVariable("id") int id, @RequestBody SweetItem sweetItem)
			throws SweetItemNotFoundException {
		try {
			 //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			// sweetItem.setImage(fileName);
		         
		       // SweetItem savedUser = sweetservice.saveImage(multipartFile) ;
		 
		       // String uploadDir = "user-photos/" + savedUser.getId();
		 
		       // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		         
		       // return new RedirectView("/users", true);
			sweetservice.findSweet(id);
			if (id == sweetItem.getSweetItemId()) {

				List<SweetItem> sweet = sweetservice.updateSweetItem(sweetItem);

				return new ResponseEntity<List<SweetItem>>(sweet, HttpStatus.OK);
			}
			throw new SweetItemNotFoundException("Please enter Same SweetID");
		} catch (Exception e) {
			throw new SweetItemNotFoundException(e.getMessage());
		}

	}
}
