package edu.csupomona.cs480.data;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/**
 * The basic user object.
 */
public class User {

	private int week;
	/** The unique user Id */
    private String id;
    /** The unique user problem number */
    private String problemid;
    /** The unique user's file */
    private MultipartFile file; 
    /** The name of the upload file  */
    private String fileName;
    /** If file is upload, then status is true*/
    private boolean status;
    /** It status is true, then string is "Submitted*/
    private String stat;
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString(); 
    /** User can have score of the file, but user can not change it. "-"*/
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

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getProblemid() {
		return problemid;
	}

	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
