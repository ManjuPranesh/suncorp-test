package com.suncorp.monthlymaster.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.suncorp.monthlymaster.model.MonthlyMasterBK;

// tag::setup[]
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	// end::setup[]

	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<MonthlyMasterBK> reader() {
		return new FlatFileItemReaderBuilder<MonthlyMasterBK>()
			.name("personItemReader")
			.resource(new ClassPathResource("sample-data.csv"))
			.delimited()
			.names(new String[]{"nvic", "old_New_Flag","family", "year", "bodystyle",
					"country", "turbo", "fuel", "drive", "valid_data","id"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<MonthlyMasterBK>() {{
				setTargetType(MonthlyMasterBK.class);
			}})
			.build();
	}

	@Bean
	public MonthlyMasterBKProcessor processor() {
		return new MonthlyMasterBKProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<MonthlyMasterBK> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<MonthlyMasterBK>()
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("INSERT INTO MONTHLY_MASTER_BK_CHANGED_ATTR_INTERVIEW "
					+ "(nvic, old_new_flag,family,year1,bodystyle,country,"
					+ "turbo,fuel,drive,valid_data, id)"
					+ " VALUES (:nvic, :oldNewFlag,:family, :year, :bodystyle,"
					+ ":country, :turbo, :fuel, :drive, :valid_data, :id)")
			.dataSource(dataSource)
			.build();
	}
	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step1)
			.end()
			.build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<MonthlyMasterBK> writer) {
		return stepBuilderFactory.get("step1")
			.<MonthlyMasterBK, MonthlyMasterBK> chunk(10)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}
	// end::jobstep[]
}
