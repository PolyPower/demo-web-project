package edu.csupomona.cs480.data;

import java.io.FileInputStream;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * The basic user object.
 */
public class User {

	private String filePath;

	/** The unique user Id */
	private String id;
	/** The timestamp when the user is being created */
	private String creationTime = new Date(System.currentTimeMillis())
			.toString();
	private boolean status;
	private String prob;
	private String stat;
	private String fileName;
	private int week;
	private String score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getprob() {
		return prob;
	}

	public void setprob(String prob) {
		this.prob = prob;
	}

	public String getStat() {
		return stat;
	}

	public void setStat() {
		if (status) {
			stat = "Submitted";
		} else {
			stat = "unSubmitted";
		}
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		if (this.score == null || isNumeric(score)) {
			this.score = score;
		} else {
			System.out.println("invalid");
		}
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
