package com.suncorp.monthlymaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncorp.monthlymaster.model.MonthlyMasterBK;
import com.suncorp.monthlymaster.repository.MonthlyMasterRepository;

@Service
public class MonthlyMasterBkService {

	@Autowired
	private MonthlyMasterRepository monthlyMasterRepo;
	
	/*
	 * 
	 */
	public List<MonthlyMasterBK> getAllMonthlyMasterBK() {
		
		List<MonthlyMasterBK> monthlyMasterBkList = new ArrayList<MonthlyMasterBK>();
		monthlyMasterRepo.findAll().forEach(monthlyMasterBkList::add);
		return monthlyMasterBkList;

	}
	
	public void addMonthlyMasterBK(MonthlyMasterBK monthlyMasterBK) {
		monthlyMasterRepo.save(monthlyMasterBK);
	}

	public Optional<MonthlyMasterBK> getMonthlyMasterBK(Integer id) {
		return monthlyMasterRepo.findById(id);
	}

	public void updateMonthlyMasterBk(MonthlyMasterBK monthlyMasterBk, Integer id) {
		monthlyMasterRepo.save(monthlyMasterBk);
	}

	public void deleteMonthlyMasterBk(Integer id) {
		monthlyMasterRepo.deleteById(id);
	}

	public void validateMonthlyMasterBK() {
		
	}

	public List<MonthlyMasterBK> validateNvic(List<MonthlyMasterBK> monthlyMasterBKlist) {
		List<MonthlyMasterBK> validatedList = new ArrayList<MonthlyMasterBK>();
		//For each 
		monthlyMasterBKlist.stream()
		.filter(mmbk -> mmbk.getOldNewFlag().equals("NEW") && (mmbk.getNvic().equals("") 
				|| mmbk.getNvic() == null || mmbk.getNvic().length() > 6 ))
		.forEach(validatedList::add);
		return validatedList;
	}

	public void updateAll(List<MonthlyMasterBK> nvicList) {
		monthlyMasterRepo.saveAll(nvicList);
	}

	public List<MonthlyMasterBK> validateYear(List<MonthlyMasterBK> monthlyMasterBKList) {
		List<MonthlyMasterBK> validatedList = new ArrayList<MonthlyMasterBK>();
		monthlyMasterBKList.stream()
		.filter(mmbk -> mmbk.getOldNewFlag().equals("NEW") && (mmbk.getYear().equals("") 
				|| mmbk.getYear() == null || mmbk.getYear().length() > 4 ))
		.forEach(validatedList::add);
		return validatedList;
	}
	
}
