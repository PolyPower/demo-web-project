package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.Problem;

public interface ProblemManager {

	/**
	 * Get the user information object based on
	 * the given problem id (would like to change
	 * this to Session).
	 * <p>
	 * If the problem does not exist, no action.
	 *
	 * @param problemId
	 * @return the Problem object
	 */
	public Problem getProblem(String problemId);

	/**
	 * Update the given user object and persist it.
	 * <p>
	 * If the user does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param user object
	 */
	public void updateProblem(Problem problem);

	/**
	 * Delete the given problem from the storage.
	 *
	 * @param problemId
	 */
	public void deleteProblem(String problemId);

	/**
	 * List all the current problems in the storage.
	 *
	 * @return
	 */
	public List<Problem> listAllProblems();
}
