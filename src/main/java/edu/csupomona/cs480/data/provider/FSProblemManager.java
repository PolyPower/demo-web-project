package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Problem;
import edu.csupomona.cs480.data.ProblemMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link ProblemManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSProblemManager implements ProblemManager {

	/**
	 * We persist all the problem related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the problem map from the local file.
	 *
	 * @return
	 */
	private ProblemMap getProblemMap() {
		ProblemMap problemMap = null;
		File problemFile = ResourceResolver.getProblemFile();
        if (problemFile.exists()) {
        	// read the file and convert the JSON content
        	// to the ProblemMap object
            try {
				problemMap = JSON.readValue(problemFile, ProblemMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	problemMap = new ProblemMap();
        }
        return problemMap;
	}

	/**
	 * Save and persist the problem map in the local file.
	 *
	 * @param problemMap
	 */
	private void persistProblemMap(ProblemMap problemMap) {
		try {
			// convert the problem object to JSON format
            JSON.writeValue(ResourceResolver.getProblemFile(), problemMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Problem getProblem(String problemId) {
		ProblemMap problemMap = getProblemMap();
        return problemMap.get(problemId);
	}

	@Override
	public void updateProblem(Problem problem) {
		ProblemMap problemMap = getProblemMap();
		problemMap.put(problem.getId(), problem);
		persistProblemMap(problemMap);
	}

	@Override
	public void deleteProblem(String problemId) {
		ProblemMap problemMap = getProblemMap();
		problemMap.remove(problemId);
		persistProblemMap(problemMap);
	}

	@Override
	public List<Problem> listAllProblems() {
		ProblemMap problemMap = getProblemMap();
		return new ArrayList<Problem>(problemMap.values());
	}

}
