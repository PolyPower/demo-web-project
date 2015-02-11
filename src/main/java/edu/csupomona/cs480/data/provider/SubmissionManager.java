package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.SubmissionId;
import edu.csupomona.cs480.data.User;

public interface SubmissionManager {

	/**
	 * Get the submission information object based on
	 * the given submissionId.
	 * <p>
	 * If the submission does not exist, simply create
	 * one.
	 *
	 * @param submissionId
	 * @return the Submission object
	 */
	public Submission getSubmission(SubmissionId submissionId);


	/**
	 * Update the given submission object and persist it.
	 * <p>
	 * If the submission does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param user object
	 */
	public void updateSubmission(Submission submission);

	/**
	 * Delete the given submission from the storage.
	 *
	 * @param submissionId
	 */
	public void deleteSubmission(SubmissionId submissionId);

	/**
	 * List all the current submissions in the storage.
	 *
	 * @return List of Submissions
	 */
	public List<Submission> listAllSubmissions();
	
	/**
	 * List all the current source code files in the storage.
	 * 
	 * @return List of file names
	 */
	public List<Submission> listFiles();



	

}
