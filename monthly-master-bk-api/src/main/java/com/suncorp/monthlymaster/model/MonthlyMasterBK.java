package com.suncorp.monthlymaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MONTHLY_MASTER_BK_CHANGED_ATTR_INTERVIEW")
public class MonthlyMasterBK {

	private String nvic;

	@Column(name = "old_new_flag")
	private String oldNewFlag;

	private String family;

	// "year" is a function in apache derby and hence couldn't create column with
	// same name.
	@Column(name = "year1")
	private String year;
	private String bodystyle;
	private String country;
	private String turbo;
	private String fuel;
	private String drive;
	private int valid_data;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	public MonthlyMasterBK(String nvic, String oldNewFlag, String family, String year, String bodystyle, String country,
			String turbo, String fuel, String drive, int valid_data, int id) {
		super();
		this.nvic = nvic;
		this.oldNewFlag = oldNewFlag;
		this.family = family;
		this.year = year;
		this.bodystyle = bodystyle;
		this.country = country;
		this.turbo = turbo;
		this.fuel = fuel;
		this.drive = drive;
		this.valid_data = valid_data;
		this.id = id;
	}

	public MonthlyMasterBK(String nvic, int id) {
		super();
		this.nvic = nvic;
		this.id = id;
	}

	public MonthlyMasterBK() {
		super();
	}

	public String getNvic() {
		return nvic;
	}

	public void setNvic(String nvic) {
		this.nvic = nvic;
	}

	public String getOldNewFlag() {
		return oldNewFlag;
	}

	public void setOldNewFlag(String oldNewFlag) {
		this.oldNewFlag = oldNewFlag;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBodystyle() {
		return bodystyle;
	}

	public void setBodystyle(String bodystyle) {
		this.bodystyle = bodystyle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTurbo() {
		return turbo;
	}

	public void setTurbo(String turbo) {
		this.turbo = turbo;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public int getValid_data() {
		return valid_data;
	}

	public void setValid_data(int valid_data) {
		this.valid_data = valid_data;
	}

	public int getId() {
		return id;
	}

	
	  public void setId(int id) { this.id = id; }
	 

}
