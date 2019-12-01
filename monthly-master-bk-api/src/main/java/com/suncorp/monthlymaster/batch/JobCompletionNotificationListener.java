package com.suncorp.monthlymaster.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.suncorp.monthlymaster.model.MonthlyMasterBK;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("BATCH JOB FINISHED! Following Data been inserted!!!");

			jdbcTemplate.query("SELECT nvic, id FROM MONTHLY_MASTER_BK_CHANGED_ATTR_INTERVIEW",
				(rs, row) -> new MonthlyMasterBK(
					rs.getString(1),
					rs.getInt(2))
			).forEach(monthlyMasterBK -> log.info("Found <" + monthlyMasterBK + "> in the database."));
		}
	}
}
