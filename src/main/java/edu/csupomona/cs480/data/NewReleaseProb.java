package edu.csupomona.cs480.data;

import java.io.FileInputStream;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * The basic user object.
 */
public class NewReleaseProb {

	private String filePath;

	private String prob;
	
	private String fileName;
	private int week;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	public String getprob() {
		return prob;
	}

	public void setprob(String prob) {
		this.prob = prob;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	

	public static boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
