package com.suncorp.monthlymaster.batch;

import org.springframework.batch.item.ItemProcessor;

import com.suncorp.monthlymaster.model.MonthlyMasterBK;

public class MonthlyMasterBKProcessor implements ItemProcessor<MonthlyMasterBK, MonthlyMasterBK> {

	@Override
	public MonthlyMasterBK process(MonthlyMasterBK item) throws Exception {
		//As no processing was needed at the time of loading, object is returned as it is
		return item;
	}

}
