
package edu.csupomona.cs480.data;

import java.io.FileInputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/**
 * The basic user object.
 */
public class User {

	private String filePath;
	
	/** The unique user Id */
    private String id;
    /** The unique user Id */
    private String name;
    /** The unique user Id */
    private String major;
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();
    
    private boolean status;
    
    private MultipartFile file; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}


	public String getFilePath()	{
		return filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}


  
