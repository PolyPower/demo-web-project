package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic Problem object.
 */
public class Problem {

	/** Unique problem ID given by UVa */
    private String id;
    
    /** The problem's title */
    private String title;
    
    /** Quarter of CSS release */
    private String quarter;
    
    /** Year of CSS release */
    private String year;
    
    /** Week of CSS release */
    private String week;
    
    /** Link to PDF */
    private String pdfUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
}
