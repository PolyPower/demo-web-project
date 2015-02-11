package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.SubmissionId;
import edu.csupomona.cs480.data.SubmissionMap;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link SubmissionManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSSubmissionManager implements SubmissionManager {

	/**
	 * We persist all the submission related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();	

	/**
	 * Load the submission map from the local file.
	 *
	 * @return
	 */
	private SubmissionMap getSubmissionMap() {
		SubmissionMap submissionMap = null;
		File submissionFile = ResourceResolver.getSubmissionFile();
        if (submissionFile.exists()) {
        	// read the file and convert the JSON content
        	// to the SubmissionMap object
            try {
				submissionMap = JSON.readValue(submissionFile, SubmissionMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	submissionMap = new SubmissionMap();
        }
        return submissionMap;
	}

	/**
	 * Save and persist the submission map in the local file.
	 *
	 * @param submissionMap
	 */
	private void persistSubmissionMap(SubmissionMap submissionMap) {
		
		try {
			// convert the submission object to JSON format
            JSON.writeValue(ResourceResolver.getSubmissionFile(), submissionMap);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}

	@Override
	public Submission getSubmission(SubmissionId submissionId) {
		SubmissionMap submissionMap = getSubmissionMap();
        return submissionMap.get(submissionId);
	}
	

	@Override
	public void updateSubmission(Submission submission) {
		SubmissionMap submissionMap = getSubmissionMap();
		submissionMap.put(submission.getSubmissionId(), submission);
		persistSubmissionMap(submissionMap);
	}

	@Override
	public void deleteSubmission(SubmissionId submissionId) {
		SubmissionMap submissionMap = getSubmissionMap();
		submissionMap.remove(submissionId);
		persistSubmissionMap(submissionMap);
	}
	
	@Override
	public List<Submission> listAllSubmissions() {
		SubmissionMap submissionMap = getSubmissionMap();
		return new ArrayList<Submission>(submissionMap.values());
	}
	@Override
	public List<Submission> listFiles() {
		SubmissionMap submissionMap = getSubmissionMap();
		return new ArrayList<Submission>(submissionMap.values());
	}
	
	
}
