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
import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.SubmissionId;
import edu.csupomona.cs480.data.User;
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
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works. It returns a String
	 * "OK" in the HTTP response. To try it, run the web application locally, in
	 * your web browser, type the link: http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "Hello World! Seulki ⚘ Hetal ⚘ Khamille";
	}

	/**
	 * This is a simple example of how to use a data manager to retrieve the
	 * data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be automatically converted
	 * to JSON format.
	 * <p>
	 * Try it in your web browser: http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user", method = RequestMethod.GET)
	User getUser(@RequestParam("UserId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

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
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		// user.setMajor(major);
		// user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	@RequestMapping(value = "/cs480/users/files", method = RequestMethod.GET)
	List<User> listFiles() {
		return userManager.listFiles();
	}

	@RequestMapping(value = "/cs480/users/score", method = RequestMethod.GET)
	List<User> listScores() {
		return userManager.listScores();
	}

	// ///// all the above code is an example from the professor ////////

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */
	@Autowired
	private SubmissionManager submissionManager;

	/**
	 * This is a simple example of how to use a data manager to retrieve the
	 * data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be automatically converted
	 * to JSON format.
	 * <p>
	 * Try it in your web browser: http://localhost:8080/cs480/submission/kas/1
	 */
	@RequestMapping(value = "/cs480/submission/{userId}/{weekNo}", method = RequestMethod.GET)
	ArrayList<Submission> getSubmissions(@PathVariable("userId") String userId,
			@PathVariable("weekNo") int weekNo) {
		ArrayList<Submission> submissionList = submissionManager
				.getSubmissions(userId);
		return submissionList;
	}

	/**
	 * This is an example of sending an HTTP POST request to update a
	 * submission's information (or create the submission if it does not exists
	 * before).
	 *
	 * You can test this with a HTTP client by sending
	 * http://localhost:8080/cs480/submission/kas/1 ====*name=John major=CS*====
	 *
	 * Note, the URL will not work directly in browser, because it is not a GET
	 * request. You need to use a tool such as postman.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/submission/{userId}", method = RequestMethod.POST)
	Submission updateSubmission(@PathVariable("userId") String userId,
			@RequestParam("weekNo") int weekNo,
			@RequestParam("uvaID") String uvaID,
			@RequestParam("filePath") String filePath,
			@RequestParam("file") MultipartFile file) { // omitted: status,
														// sourceCode, score

		// String filename = "";

		Submission submission = new Submission();
		submission.setUserId(userId);
		submission.setWeekNo(weekNo);
		submission.setUvaID(uvaID);
		submission.setFilePath(filePath);
		// submission.setSourceCode(file);
		submission.setStatus(false); // hard-coded value
		submission.setScore(0); // hard-coded value
		submissionManager.updateSubmissionList(submission);

		/*
		 * byte[] bytes = file.getBytes(); BufferedOutputStream stream = new
		 * BufferedOutputStream( new FileOutputStream(new File(filename)));
		 * stream.write(bytes); stream.close();
		 */

		return submission;
	}

	/*********** Web UI Test Utility for Submission List **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/submitCode", method = RequestMethod.GET)
	ModelAndView getSubmitPage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	/*********** Web UI Test Utility **********/

	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.GET)
	ModelAndView getUsercodeSubmit() {
		ModelAndView modelAndView = new ModelAndView("codeSubmit");
		modelAndView.addObject("users", listAllUsers());

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
		modelAndView.addObject("users", listAllUsers());;
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
	@RequestMapping(value = "/user/{user}/download", method = RequestMethod.GET)
	public void getFile(/* @PathVariable("fileName") String fileName, */
	@PathVariable("user") String userId, HttpServletResponse response)
			throws IOException {
		// System.out.println(fileName);
		User user = userManager.getUser(userId);
		String path = user.getFilePath();
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		// 1. Get the file of your photo based on the userId and photoId
		InputStream file = new FileInputStream(f);

		// 2. Return the photo file as an output stream using the code below
		IOUtils.copy(file, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename= \""
				+ f.getName() + "\"");// fileName);

		response.flushBuffer();

	}

	/**
	 * Khamille's contribution for Assignment 5: HashBasedTable using Google
	 * Guava.
	 */
	@RequestMapping(value = "/cs480/guava", method = RequestMethod.GET)
	public String guava() {
		HashBasedTable twoKeyMap = HashBasedTable.create();
		return "Hello World! I created a HashTable whose values are accessible using two key values using Google Guava.";
	}

	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.POST)
	public @ResponseBody ModelAndView handleFileUpload(
			@RequestParam("UserID") String id,
			@RequestParam("ProblemID") String promb,
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
				user.setprob(promb);
				user.setStatus(true);
				user.setFileName(name);
				user.setFilePath(dir + name);
				user.setStat();
				user.setScore("-");
				userManager.updateUser(user);// add

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
			modelAndView.addObject("users", listAllUsers());
			return modelAndView;
		}

	}

	@RequestMapping(value = "/cs480/score/{userId}/setScore", method = RequestMethod.POST)
	public @ResponseBody ModelAndView setScore(
			@PathVariable("userId") String id,
			@RequestParam(value = "score") String score) {

		User user = userManager.getUser(id);
		if (StringUtil.isNotBlank(score)) {
			user.setScore(score);
			System.out.println(user.getId());
			System.out.println(user.getScore());
			userManager.updateUser(user);
			ModelAndView model = new ModelAndView("/AdminHome");
			model.addObject("users", listAllUsers());
			return model;

		} else {
			System.out.println("no score");
			ModelAndView model = new ModelAndView("/AdminHome");
			model.addObject("users", listAllUsers());
			return model;
		}
	}

}