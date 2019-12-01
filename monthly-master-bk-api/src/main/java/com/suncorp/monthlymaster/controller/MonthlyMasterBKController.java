package com.suncorp.monthlymaster.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suncorp.monthlymaster.model.MonthlyMasterBK;
import com.suncorp.monthlymaster.service.MonthlyMasterBkService;

@RestController
public class MonthlyMasterBKController {
	
	@Autowired
	private MonthlyMasterBkService monthlyMasterBkService;

	@RequestMapping("/monthlymasterbk")
	public List<MonthlyMasterBK> getAllMonthlyMasterBk() {
	
		return monthlyMasterBkService.getAllMonthlyMasterBK();
		
	}
	
	@RequestMapping("/monthlymasterbk/{id}")
	public Optional<MonthlyMasterBK> getMonthlyMasterBk(@PathVariable Integer id) {
		
		return monthlyMasterBkService.getMonthlyMasterBK(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/monthlymasterbk")
	public void addMonthlyMasterBk(@RequestBody MonthlyMasterBK monthlyMasterBK) {
	
		 monthlyMasterBkService.addMonthlyMasterBK(monthlyMasterBK);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/monthlymasterbk/{id}")
	public void updateMonthlyMasterBk(@RequestBody MonthlyMasterBK monthlyMasterBk, 
			@PathVariable Integer id) {
		monthlyMasterBkService.updateMonthlyMasterBk(monthlyMasterBk,id);
	}

	@RequestMapping(method=RequestMethod.DELETE,value="/monthlymasterbk/{id}")
	public void deleteMonthlyMasterBk(@PathVariable Integer id) {
		monthlyMasterBkService.deleteMonthlyMasterBk(id);
	}
	
	/*
	 * Validates the table MONTHLY_MASTER_BK_ATTR
	 * @param none
	 * @return void
	 */
	@RequestMapping("monthlymasterbk/validate")
	public String validateMonthlyMasterBK() {
		
		//get all the entries from the table
		List<MonthlyMasterBK> monthlyMasterBKList = 
				monthlyMasterBkService.getAllMonthlyMasterBK();
		
		//filter the list based on nvic if it is null 
		List<MonthlyMasterBK> validatedList = 
				monthlyMasterBkService.validateNvic(monthlyMasterBKList);
		
		//Add the validated year list to previously validated list
		validatedList.addAll(monthlyMasterBkService.validateYear(monthlyMasterBKList));
		
		//update the table with list of validate entries
		monthlyMasterBkService.updateAll(validatedList);
		
		return "Total number of records updated in table are "+validatedList.size();
	}
}
