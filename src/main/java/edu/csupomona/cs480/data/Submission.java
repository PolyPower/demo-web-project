package edu.csupomona.cs480.data;

import java.io.FileInputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * The basic user object.
 */
public class Submission {
	
	
	//private SubmissionId submissionId;
	
	/**
	 * The user's self-defined user ID. Ideally, the user
	 * would use the same user ID every time.
	 */
	private String userId;

	/**
	 * The week number that corresponds to this submission.
	 */
	private int weekNo;

	/**
	 * The UVa problem ID that corresponds to the problem answered
	 * in this submission.
	 */
	private String uvaID;
	
	/**
	 * The filepath of the user's uploaded code.
	 */
	private String filePath;
	
	/**
	 * The actual file object of the submission.
	 */
	private MultipartFile sourceCode;
	
	/**
	 * The status of the user's submission. Values can either be
	 * 'Submitted' or 'Reviewed' and can only change from Submitted
	 * to Reviewed by the Admin.
	 */
	private boolean status; 
	
	/** 
	 * According to the Admin, this is the submission's score.
	 */
	private int score;
	
	/**
	 * The timestamp when the code was submitted.
	 */
	private String creationTime = new Date(System.currentTimeMillis()).toString();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}

	public void setUvaID(String uvaID) {
		this.uvaID = uvaID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public MultipartFile getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(MultipartFile sourceCode) {
		this.sourceCode = sourceCode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	

}
