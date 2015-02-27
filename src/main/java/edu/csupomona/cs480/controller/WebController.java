package edu.csupomona.cs480.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.*;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.NewReleaseProb;
import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserScore;

import edu.csupomona.cs480.data.provider.NewReleaseProbManager;
import edu.csupomona.cs480.data.provider.SubmissionManager;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map each HTTP API Path to the
 * correspondent method.
 * 
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */

	private UserManager userManager;

	@Autowired
	private NewReleaseProbManager newReleaseProbManager;

	@Autowired
	private SubmissionManager submissionManager;

	/**
	 * This is an example of sending an HTTP POST request to update a user's
	 * information (or create the user if not exists before).
	 * 
	 * You can test this with a HTTP client by sending
	 * http://localhost:8080/cs480/user/user101 name=John major=CS
	 * 
	 * Note, the URL will not work directly in browser, because it is not a GET
	 * request. You need to use a tool such as curl.
	 * 
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/cs480/user/{userId}", method =
	 * RequestMethod.POST) User updateUser(@PathVariable("userId") String id,
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam(value = "major", required = false) String major) { User
	 * user = new User(); user.setId(id); // user.setMajor(major); //
	 * user.setName(name); userManager.updateUser(user); return user; }
	 */
	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 * 
	 * @param userId
	 */
	/*
	 * @RequestMapping(value = "/cs480/user/{userId}", method =
	 * RequestMethod.DELETE) void deleteUser(@PathVariable("userId") String
	 * userId) { userManager.deleteUser(userId); }
	 */
	/**
	 * This API lists all the users in the current database.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	@RequestMapping(value = "/cs480/problems/list", method = RequestMethod.GET)
	List<NewReleaseProb> listAllProblems() {
		return newReleaseProbManager.listAllProblems();
	}

	/*
	 * @RequestMapping(value = "/cs480/users/files", method = RequestMethod.GET)
	 * List<User> listFiles() { return userManager.listFiles(); }
	 * 
	 * @RequestMapping(value = "/cs480/users/score", method = RequestMethod.GET)
	 * List<User> listScores() { return userManager.listScores(); }
	 */

	// ///// all the above code is an example from the professor ////////

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */

	/**
	 * This API lists all the submissions for a specified user in the current
	 * file system.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list/{userId}/admin-view", method = RequestMethod.GET)
	ArrayList<Submission> listSubmissionsForUser(
			@PathVariable("userId") String userId) {
		return submissionManager.getSubmissions(userId);
	}

	@RequestMapping(value = "/list/admin", method = RequestMethod.GET)
	ArrayList<Submission> listSubmissionForAll() {
		return submissionManager.listAllSubmissionsInStorage();
	}

	@RequestMapping(value = "/list/userid", method = RequestMethod.GET)
	ArrayList<UserScore> listUser() {
		// System.out.println("user id ");
		return submissionManager.listUser();

	}

	/*********** Web UI Test Utility for Submission List **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
	ModelAndView getUsersSubmissions(@PathVariable("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
		return modelAndView;
	}

	@RequestMapping(value = "/list/user", method = RequestMethod.POST)
	ModelAndView getUsersFromSearch(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/list/user", method = RequestMethod.POST)
	ModelAndView getUsersInAdmin(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("AdminHome");
		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		if (list != null) {
			modelAndView.addObject("submissions",
					listSubmissionsForUser(userId));
			return modelAndView;
		}
		System.out.println("user is not exist");
		modelAndView.addObject("submissions", new ArrayList<Submission>());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/list/{user}/listing", method = RequestMethod.GET)
	ModelAndView getUsersbyClick(@PathVariable("user") String userId) {
		ModelAndView modelAndView = new ModelAndView("AdminHome");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
		return modelAndView;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	ModelAndView getall() {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("submissions", listSubmissionForAll());
		return modelAndView;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	ModelAndView getUsersSubmissions() {
		ModelAndView modelAndView = new ModelAndView("upload-form");
		return modelAndView;
	}

	/**
	 * This is an example of sending an HTTP POST request to update a user's
	 * submissions (or create the submission record if it did not exist before).
	 * 
	 * You can test this with a HTTP client by sending
	 * http://localhost:8080/cs480/submission/kas/1
	 * 
	 * Note, the URL will not work directly in browser, because it is not a GET
	 * request. You need to use a tool such as postman.
	 * 
	 * @param userId
	 * @param weekNo
	 * @param uvaID
	 * @param filePath
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	ModelAndView updateSubmission(
			@RequestParam("userId") String userId,
			@RequestParam("weekNo") int weekNo,
			@RequestParam("problemId") String probID,
			@RequestParam("file") MultipartFile file) {

		String name = null;
		String dir = System.getProperty("user.home") + "/cs480/";
		if (!file.isEmpty()) {
			try {
				Submission submission = new Submission();
				name = file.getOriginalFilename();
				submission.setUserId(userId);
				submission.setWeekNo(weekNo);
				submission.setProbID(probID);
				submission.setFileName(name);
				submission.setFilePath(dir + name);
				submission.setStatus(false); // hard-coded value
				submission.setScore(0); // hard-coded value
				submissionManager.updateSubmissionList(submission);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				ModelAndView modelAndView = new ModelAndView("upload-form");
				return modelAndView;

			} catch (Exception e) {

				ModelAndView modelAndView = new ModelAndView("upload-form");
				return modelAndView;
			}

		} else {

			ModelAndView modelAndView = new ModelAndView("upload-form");
			return modelAndView;
		}

	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */

	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.GET)
	ModelAndView getUsercodeSubmit() {
		ModelAndView modelAndView = new ModelAndView("codeSubmit");

		 modelAndView.addObject("submissions", listSubmissionForAll());

		//modelAndView.addObject("submissions", listAllUsers());
		modelAndView.addObject("problems", listAllProblems());

		return modelAndView;
	}

	@RequestMapping(value = "/cs480/loginHome", method = RequestMethod.GET)
	ModelAndView getlogin() {
		ModelAndView modelAndView = new ModelAndView("loginHome");

		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome", method = RequestMethod.GET)
	ModelAndView getAdmin() {
		ModelAndView modelAndView = new ModelAndView("AdminHome");
		modelAndView.addObject("submissions", listSubmissionForAll());
	//	modelAndView.addObject("problems", listAllProblems());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/page", method = RequestMethod.GET)
	ModelAndView getFirstPage() {
		ModelAndView modelAndView = new ModelAndView("AdminPage");
		modelAndView.addObject("userscores", listUser());
		return modelAndView;
	}

	/**
	 * Download file, done
	 * 
	 * file should be in the repository folder
	 * 
	 * 
	 * @param fileName
	 * @param response
	 * @return
	 * @throws IOException
	 */

	// @RequestMapping(value = "/file/{fileName}/user/{user}/download", method =
	// RequestMethod.GET)
	@RequestMapping(value = "/user/{user}/{week}/download", method = RequestMethod.GET)
	public void getFile(
			@PathVariable("user") String userId,
			@PathVariable("week") int week, 
			HttpServletResponse response)
			throws IOException {
		
		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		Submission user = list.get(week - 1);
		String path = user.getFilePath();
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		InputStream file = new FileInputStream(f);

		// 2. Return the photo file as an output stream using the code below
		IOUtils.copy(file, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename= \""
				+ f.getName() + "\"");

		response.flushBuffer();

	}

	@RequestMapping(value = "/problem/{problem}/download", method = RequestMethod.GET)
	public void getProblemFile(
			@PathVariable("problem") String probId,
			HttpServletResponse response) 
			throws IOException {

		NewReleaseProb problem = newReleaseProbManager.getProbId(probId);
		String path = problem.getFilePath();
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		// 1. Get the file of your photo based on the userId and photoId
		InputStream file = new FileInputStream(f);

		// 2. Return the photo file as an output stream using the code below
		IOUtils.copy(file, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename= \""
				+ f.getName() + "\"");

		response.flushBuffer();

	}

	@RequestMapping(value = "/cs480/AdminHome", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView releaseFileUpload(
			@RequestParam("ProblemID") String prob, 
			@RequestParam("Weeks") int weekNo,
			@RequestParam("file") MultipartFile file){
		String name = null;
		String dir = System.getProperty("user.home") + "\\cs480\\";
		if (!file.isEmpty()) {
			try {

				NewReleaseProb newProb = new NewReleaseProb();
				name = file.getOriginalFilename();

				newProb.setWeek(weekNo);
				newProb.setprob(prob);

				newProb.setFileName(name);
				newProb.setFilePath(dir + name);

				newReleaseProbManager.updateNewProblem(newProb);// add

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				ModelAndView modelAndView = new ModelAndView("/AdminHome");
				modelAndView.addObject("problems", listAllProblems());
				//modelAndView.addObject("users", listAllUsers());
				modelAndView.addObject("submissions", listSubmissionForAll());

				return modelAndView;
			} catch (Exception e) {
				ModelAndView modelAndView = new ModelAndView(
						"You failed to upload " + " => " + e.getMessage());
				modelAndView.addObject("problems", listAllProblems());
				modelAndView.addObject("submissions", listSubmissionForAll());

				//modelAndView.addObject("users", listAllUsers());
				return modelAndView;
			}
		} else {

			ModelAndView modelAndView = new ModelAndView(
					"You failed to upload " + " because the file was empty.");
			return modelAndView;
		}

	}

	@RequestMapping(value = "/cs480/AdminHome/{userId}/{week}/setScore", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView setScore(
			@PathVariable("userId") String id,
			@PathVariable("week") int week,
			@RequestParam(value = "score") int score) {

		submissionManager.setScore(id, week, score);

		ModelAndView modelAndView = new ModelAndView("/AdminHome");
		modelAndView.addObject("submissions", listSubmissionForAll());
		return modelAndView;

	}

	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView handleFileUpload(
			@RequestParam("UserID") String id,
			@RequestParam("ProblemID") String prob,
			@RequestParam("Weeks") int weekNo,
			@RequestParam("file") MultipartFile file) {
		String name = null;
		String dir = System.getProperty("user.home") + "\\cs480\\";
		if (!file.isEmpty()) {
			try {

				User user = new User();
				name = file.getOriginalFilename();
				user.setId(id);
				user.setWeek(weekNo);
				user.setprob(prob);
				user.setStatus(true);
				user.setFileName(name);
				user.setFilePath(dir + name);
				user.setStat();
				user.setScore("-");
				userManager.updateUser(user);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				ModelAndView modelAndView = new ModelAndView("/codeSubmit");
				modelAndView.addObject("users", listAllUsers());
				return modelAndView;
			} catch (Exception e) {
				ModelAndView modelAndView = new ModelAndView(
						"You failed to upload " + " => " + e.getMessage());
				modelAndView.addObject("users", listAllUsers());
				return modelAndView;
			}
		} else {

			ModelAndView modelAndView = new ModelAndView(
					"You failed to upload " + " because the file was empty.");
			return modelAndView;
		 }
	}

}