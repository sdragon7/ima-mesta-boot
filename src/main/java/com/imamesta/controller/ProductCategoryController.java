package com.imamesta.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.ProductCategory;
import com.imamesta.services.ProductCategoryService;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/test")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<ProductCategory> getProductCategories() {
		return productCategoryService.getAll();
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
