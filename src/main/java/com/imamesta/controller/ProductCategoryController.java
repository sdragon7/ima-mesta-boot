package com.imamesta.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.Product;
import com.imamesta.domain.ProductCategory;
import com.imamesta.domain.ProductCategoryItem;
import com.imamesta.domain.ProductIngredients;
import com.imamesta.services.ProductCategoryItemService;
import com.imamesta.services.ProductCategoryService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private ProductCategoryItemService productCategoryItemService;
	
	@GetMapping("/category/list")
	public List<ProductCategory> getProductCategories() {
		return productCategoryService.getAll();
	}
	
	@GetMapping("/category/{id}/items")
	public List<ProductCategoryItem> getProductCategoryItems(@PathVariable("id") Long id) {
		return productCategoryItemService.getByProductCategoryId(id);
	}
	
	@GetMapping(value = "/images")
	public boolean gfgfgetAllRooms() throws IOException {
		
		/*File f = new File("C:\\Users\\Stefan\\Desktop\\kafa.jpg");
		byte[] fileContent = Files.readAllBytes(f.toPath());*/
		
		//List<ProductCategory> pc = productCategoryService.getAll();
		
		/*for(ProductCategory p : pc) {
			p.setImage(fileContent);
			productCategoryService.save(p);
			
		}
		*/
		/*try {
		for(Room r : rooms) {
			if(r.getName().equals("Cooking")) {
				File f = new File("C:\\Users\\Stefan\\Desktop\\cap.png");
				byte[] fileContent = Files.readAllBytes(f.toPath());
				r.setThumbnail(fileContent);
				roomService.saveRoom(r);
			}
			
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		
		return true;
	}	
	
}
