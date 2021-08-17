package com.cg.osm.testing;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.SweetItemNotFoundException;
import com.cg.osm.repository.SweetItemRepo;

import com.cg.osm.service.SweetItemServiceImpl;

@ExtendWith(MockitoExtension.class)
class SweetItemServiceApplicationTests { //Testing for SweetItem service layer
	
		
    @Mock
	private SweetItemRepo sweetrepo;
    
    
    @Autowired
	@InjectMocks
	private SweetItemServiceImpl sweet;
	private SweetItem sweetitem1;
	private SweetItem sweetitem2;
	private List<SweetItem>sweetlist;
	
	@BeforeEach
    public void setUp() {
		sweetlist = new ArrayList<>();
		sweetitem1 = new SweetItem(1, "Laddu", 301,true);
		sweetitem2 = new SweetItem(2, "Burfi", 301,true);
        sweetlist.add(sweetitem1);
        sweetlist.add(sweetitem1);
    }
	
	@AfterEach
    public void tearDown() {
		sweetitem1 = sweetitem2 = null;
		sweetitem2 = null;
    }
	

    // Test Case for Saving a Order
    @Test
    void givenSweetItemToAddShouldReturnAddedSweetItem() throws SweetItemNotFoundException {
        sweet.addSweetItem(sweetitem1);
        verify(sweetrepo, times(1)).saveAndFlush(sweetitem1);
    }
    @Test
    public void GivenGetAllSweetsShouldReturnListOfAllSweets() {
    	sweetrepo.saveAndFlush(sweetitem1);
        // stubbing mock to return specific data
        when(sweetrepo.findAll()).thenReturn(sweetlist);
        List<SweetItem> sweetList1 = sweet.showAllSweetItems();
        assertEquals(sweetList1, sweetlist);
        verify(sweetrepo, times(1)).saveAndFlush(sweetitem1);
        verify(sweetrepo, times(1)).findAll();
    }

	
 @Test
 void testDeleteSweetByIdShouldThrowSweetItemNotFoundException() {
     assertThrows(SweetItemNotFoundException.class, ()->{
         sweet.cancelSweetItem(2);
     });
 }


@Test
void testFindSweetByIdShouldThrowSweetItemNotFoundException() {
  assertThrows(SweetItemNotFoundException.class, ()->{
	  sweet.findSweet(1);
  });
}


	@Test
	void contextLoads() {
	}

}
