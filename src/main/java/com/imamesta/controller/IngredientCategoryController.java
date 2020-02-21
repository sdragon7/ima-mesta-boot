package com.imamesta.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.dao.IngredientRepository;
import com.imamesta.dao.UpdateMessageRepository;
import com.imamesta.domain.Ingredient;
import com.imamesta.domain.IngredientCategory;
import com.imamesta.domain.Product;
import com.imamesta.domain.ProductIngredients;
import com.imamesta.domain.UpdateMessage;
import com.imamesta.domain.UpdateType;
import com.imamesta.dto.IngredientDto;
import com.imamesta.dto.UpdateMessageDto;
import com.imamesta.dto.WhStatisticsDto;
import com.imamesta.dto.WhStatisticsList;
import com.imamesta.services.IngredientCategoryService;
import com.imamesta.services.ProductService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class IngredientCategoryController {
	
	@Autowired
	private UpdateMessageRepository umr;

	@Autowired
	private IngredientCategoryService icr;
	
	@Autowired
	private IngredientRepository irepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductService productService;
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@InitBinder
	public void initBinder(WebDataBinder binder){
		String dateTimeFormat = "dd/MM/yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	private UpdateMessage convertMessageDTOToMessageEntity(UpdateMessageDto dto) {
		
		UpdateMessage umsg = modelMapper.map(dto, UpdateMessage.class);
		umsg.setIngredient(irepo.findById(umsg.getId()).get());
		return umsg;
	}
	
	private UpdateMessageDto convertMessageEntityToMessageDTO(UpdateMessage umsg) {
		UpdateMessageDto dto = modelMapper.map(umsg, UpdateMessageDto.class);
		dto.setId(umsg.getIngredient().getId());
		return dto;
	}
	
	
	@GetMapping("/warehouse/categories")
	public List<IngredientCategory> getIngredientCategories() {
		List<IngredientCategory> lista = icr.findAll();
		for( IngredientCategory l : lista) {
			for(Ingredient i : l.getIngredients()) {
			}
		}
		return lista;	
	}
	@PostMapping(value = "/warehouse/categories/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public IngredientCategory addCategory(@RequestParam String name) {
		IngredientCategory ic = new IngredientCategory();
		ic.setName(name);
		return icr.save(ic);
	}
	
	
	
	@GetMapping("/warehouse/ingredients")
	public List<Ingredient> getIngredients() {

		return irepo.findAll();
		
	}
	@PostMapping("/warehouse/ingredients")
	public Ingredient addIngredient(@RequestBody IngredientDto idto) {
		Ingredient i = new Ingredient();
		i.setName(idto.getName());
		i.setRemainingQuantity(idto.getRemainingQuantity());
		i.setIngredientCategory(icr.getById(idto.getCategoryId()).get());
		i.setUnit(idto.getUnit());
		return irepo.save(i);
	}
	
	@GetMapping("/warehouse/{id}")
	public List<Ingredient> getAllIngredientsByCategoryId(@PathVariable ("id") Long id) {
		Optional<IngredientCategory> optVal = icr.getById(id);
		IngredientCategory ic = optVal.get();
		ic.getIngredients().size();
		return ic.getIngredients();
	}
	
	@PutMapping("/warehouse/ingredient")
	public Ingredient updateIngredient(@RequestBody Ingredient i) {
		
		Ingredient toBeUpdated = irepo.findById(i.getId()).get();
		double lastQtyUp = i.getLastQuantityUpdate();
		toBeUpdated.setRemainingQuantity(toBeUpdated.getRemainingQuantity() + lastQtyUp);
		toBeUpdated.setLastQuantityUpdate(lastQtyUp);
		if(lastQtyUp > 0) {
			toBeUpdated.setLastQuantityIncrease(lastQtyUp);
		}
		else { 
			toBeUpdated.setLastQuantityDecrease(-lastQtyUp);
		}
		return irepo.save(toBeUpdated);
	}
	
	@PutMapping("/warehouse/ingredients")
	public List<Ingredient> updateIngredients(@RequestBody List<Ingredient> ilist) {
		List<Ingredient> retval = new ArrayList<>();
		for(Ingredient ingr : ilist) {
			Ingredient toBeUpdated = irepo.findById(ingr.getId()).get();
			double lastQtyUp = ingr.getLastQuantityUpdate();
			toBeUpdated.setRemainingQuantity(ingr.getRemainingQuantity() + lastQtyUp);
			toBeUpdated.setLastQuantityUpdate(lastQtyUp);
			if(lastQtyUp > 0) {
				toBeUpdated.setLastQuantityIncrease(lastQtyUp);
			}
			else { 
				toBeUpdated.setLastQuantityDecrease(-lastQtyUp);
			}
			retval.add(irepo.save(toBeUpdated));

		}
		//return retval;
		return irepo.findAll();
	}
	
	
	@PostMapping("/warehouse/messages")
	public void saveMessages(@RequestBody List<UpdateMessageDto> msgs) {
		for(UpdateMessageDto msg : msgs) {
			umr.save(convertMessageDTOToMessageEntity(msg));
		}
	}
	
	@GetMapping("/warehouse/potrosnja/{productId}")
	public boolean ingredientPotrosnja(@PathVariable("productId") Long id) {
		Product product = productService.getById(id);
		for(ProductIngredients pi : product.getProductIngredients()) {
			Ingredient ingredient = pi.getIngredient();
			double q = ingredient.getRemainingQuantity() - 1.0;
			ingredient.setRemainingQuantity(q);
			ingredient.getMessages().add(new UpdateMessage(ingredient, q, UpdateType.POTROSNJA));	
		}
		
		productService.saveProduct(product);
		
		return true;
	}
	
	
	@PostMapping("/warehouse/statistics/list")
	public List<WhStatisticsList> getLfdfist(@RequestBody WhStatisticsDto whStatisticsDto) {
		List<WhStatisticsList> list = new ArrayList<>();
		double nabavka = 0, otpis = 0, potrosnja = 0;
		IngredientCategory ingrCategory = icr.getById(whStatisticsDto.getIngredientCategoryId()).get();
		for(Ingredient ingredient : ingrCategory.getIngredients()) {
			for(UpdateMessage msg : ingredient.getMessages()) {
				if(msg.getDate().after(whStatisticsDto.getStartDate()) && 
						msg.getDate().before(whStatisticsDto.getEndDate())) {
					
					if(msg.getType() == UpdateType.NABAVKA) {
						nabavka += msg.getQuantity();
					} else if(msg.getType() == UpdateType.OTPIS) {
						otpis += msg.getQuantity();
					}
					
					potrosnja += msg.getQuantity();
				}
			}
			list.add(new WhStatisticsList(ingredient.getName(), nabavka, otpis, potrosnja));
			nabavka = 0; otpis = 0; potrosnja = 0;
		}
		
		return list;
	}
}
