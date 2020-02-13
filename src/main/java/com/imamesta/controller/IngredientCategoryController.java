package com.imamesta.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.dao.IngredientRepository;
import com.imamesta.dao.UpdateMessageRepository;
import com.imamesta.domain.Ingredient;
import com.imamesta.domain.IngredientCategory;
import com.imamesta.domain.UpdateMessage;
import com.imamesta.dto.UpdateMessageDto;
import com.imamesta.dto.WhStatisticsDto;
import com.imamesta.services.IngredientCategoryService;

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
	
	@GetMapping("/warehouse/ingredients")
	public List<Ingredient> getIngredients() {

		return irepo.findAll();
		
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
		toBeUpdated.setRemainingQuantity(i.getRemainingQuantity() + i.getLastQuantityUpdate());
		toBeUpdated.setLastQuantityUpdate(i.getLastQuantityUpdate());
		return irepo.save(toBeUpdated);
		
	}
	
	@PutMapping("/warehouse/ingredients")
	public List<Ingredient> updateIngredients(@RequestBody List<Ingredient> ilist) {
		List<Ingredient> retval = new ArrayList<>();
		for(Ingredient ingr : ilist) {
			Ingredient toBeUpdated = irepo.findById(ingr.getId()).get();
			toBeUpdated.setRemainingQuantity(ingr.getRemainingQuantity() + ingr.getLastQuantityUpdate());
			toBeUpdated.setLastQuantityUpdate(ingr.getLastQuantityUpdate());
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
	
	@PostMapping("/warehouse/statistics")
	public void getList(@RequestBody WhStatisticsDto whStatisticsDto) {
		IngredientCategory ingrCategory = icr.getById(whStatisticsDto.getIngradientCategoryId()).get();
		for(Ingredient ingredient : ingrCategory.getIngredients()) {
			for(UpdateMessage msg : ingredient.getMessages()) {
				if(msg.getDate().after(whStatisticsDto.getStartDate()) && 
						msg.getDate().before(whStatisticsDto.getEndDate())) {
					
					//
					
				}
			}
		}
		
		System.out.println(ingrCategory.getName() + ": " + whStatisticsDto.getStartDate() + " *** " + whStatisticsDto.getEndDate());
	}
	
}
