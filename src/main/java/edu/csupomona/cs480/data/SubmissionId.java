package edu.csupomona.cs480.data;

public class SubmissionId {
	
	public String userId;
	public int weekNo;
	
	public SubmissionId(String userId, int weekNo) {
		this.userId = userId;
		this.weekNo = weekNo;
	}
	
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

	@Override
	public String toString() {
		return userId + ":" + weekNo;
	}

	

}
