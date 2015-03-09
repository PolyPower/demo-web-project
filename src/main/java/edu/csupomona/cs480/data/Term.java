package edu.csupomona.cs480.data;

/**
 * This class is meant to represent when a problem was
 * released through CodeSubmit. This is a flyweight object
 * and is used as the value in the HashMap of TermMenu object. 
 * @author khamille
 *
 */
public class Term {
	
	private int weekNo;
	private String quarter;
	private int year;
	
	public Term() {
		this.weekNo = 0;
		this.quarter = "summer";
		this.year = 2000;
	}
	
	public Term(int weekNo, String quarter, int year) {
		this.weekNo = weekNo;
		this.quarter = quarter;
		this.year = year;
	}
	
	public int getWeekNo() {
		return weekNo;
	}
	
	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}
	
	public String getQuarter() {
		return quarter;
	}
	
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

}
