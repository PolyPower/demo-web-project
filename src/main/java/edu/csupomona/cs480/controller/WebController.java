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
import edu.csupomona.cs480.data.SubmissionMap;
import edu.csupomona.cs480.data.Term;
import edu.csupomona.cs480.data.TermMenu;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserScore;
import edu.csupomona.cs480.data.provider.AdminiManager;
import edu.csupomona.cs480.data.provider.NewReleaseProbManager;
import edu.csupomona.cs480.data.provider.SubmissionManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map each HTTP API Path to the
 * correspondent method.
 * 
 */

@RestController
public class WebController {

	@Autowired
	private NewReleaseProbManager newReleaseProbManager;

	@Autowired
	private SubmissionManager submissionManager;
	
	@Autowired
	private AdminiManager adminiManager;
	
	TermMenu termMenu = TermMenu.getInstance();

	

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

	@RequestMapping(value = "/list/user", method = RequestMethod.POST)
	ModelAndView getUsersFromSearch(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/problems/list", method = RequestMethod.GET)
	List<NewReleaseProb> listAllProblems() {
		return newReleaseProbManager.listAllProblems();
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

	//@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@RequestMapping(value = "cs480/codeSubmit", method = RequestMethod.POST)
	ModelAndView updateSubmission(
			@RequestParam("UserID") String userId,
			@RequestParam("ProblemID") String probID,
			@RequestParam("file") MultipartFile file) {

		String name = null;
		String dir = System.getProperty("user.home") + "/cs480/";
		if (!file.isEmpty()) {
			try {
				Submission submission = new Submission();
				name = file.getOriginalFilename();
				submission.setUserId(userId);
				Term term = termMenu.lookup(probID);
				submission.setProblemId(probID);
				submission.setTerm(term);
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

				ModelAndView modelAndView = new ModelAndView("codeSubmit");
				return modelAndView;

			} catch (Exception e) {

				ModelAndView modelAndView = new ModelAndView("codeSubmit");
				return modelAndView;
			}

		} else {

			ModelAndView modelAndView = new ModelAndView("codeSubmit");
			return modelAndView;
		}

	}

	@RequestMapping(value = "/cs480/codeSubmitHome", method = RequestMethod.GET)
	ModelAndView getcodeSubmitHome() {
		ModelAndView modelAndView = new ModelAndView("codeSubmitHome");
		//modelAndView.addObject("submissions", listSubmissionForAll());
		modelAndView.addObject("problems", listAllProblems());
		return modelAndView;
	}
	
	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.GET)
	ModelAndView getUsercodeSubmit() {
		ModelAndView modelAndView = new ModelAndView("codeSubmit");

		modelAndView.addObject("submissions", listSubmissionForAll());


		// modelAndView.addObject("submissions", listAllUsers());
		modelAndView.addObject("problems", listAllProblems());

		return modelAndView;
	}

	@RequestMapping(value = "/cs480/codeSubmit/login", method = RequestMethod.GET)
	ModelAndView getlogin() {
		ModelAndView modelAndView = new ModelAndView("loginHome");
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome", method = RequestMethod.GET)
	ModelAndView getAdmin() {
		ModelAndView modelAndView = new ModelAndView("AdminHome");
		modelAndView.addObject("submissions", listSubmissionForAll());

		modelAndView.addObject("problems", listAllProblems());

		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/page", method = RequestMethod.GET)
	ModelAndView getFirstPage() {
		ModelAndView modelAndView = new ModelAndView("AdminPage");
		modelAndView.addObject("userscores", listUser());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/list/user", method = RequestMethod.POST)
	ModelAndView getUsersInAdmin(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("/AdminSecond");
		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		if (list != null) {
			modelAndView.addObject("submissions",
					listSubmissionsForUser(userId));
			return modelAndView;
		}
		System.out.println("user is not exist");
		modelAndView = new ModelAndView("/AdminHome");
		modelAndView.addObject("submissions", new ArrayList<Submission>());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminHome/list/{user}/listing", method = RequestMethod.GET)
	ModelAndView getUsersbyClick(@PathVariable("user") String userId) {
		ModelAndView modelAndView = new ModelAndView("AdminSecond");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
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

	@RequestMapping(value = "/user/{user}/{week}/download", method = RequestMethod.GET)
	public void getFile(@PathVariable("user") String userId,
			@PathVariable("week") String week, HttpServletResponse response)
			throws IOException {

		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		Submission user = list.get(termMenu.lookup(week).getWeekNo() - 1);
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
			@PathVariable("problem") String problemId,
			HttpServletResponse response) 
			throws IOException {


		NewReleaseProb problem = newReleaseProbManager.getProbId(problemId);
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
			@RequestParam("ProblemID") String problemId, 
			@RequestParam("Year") int year,
			@RequestParam("Quarter") String quarter,
			@RequestParam("Weeks") int weekNo,
			@RequestParam("file") MultipartFile file){

		String name = null;
		String dir = System.getProperty("user.home") + "\\cs480\\";
		if (!file.isEmpty()) {
			try {

				System.out.println(problemId);
				System.out.println(weekNo);
				System.out.println(quarter);
				System.out.println(year);
				name = file.getOriginalFilename();
				
				// This is unhealthy coding because if the problem already 
				// exists there is no need for the last three parameters
				Term term = termMenu.lookup(problemId, weekNo, quarter, year);
				
				
				
				NewReleaseProb newProb = new NewReleaseProb.NewReleaseProblemBuilder()
						.withfileName(name).withfilePath(dir+name)
						.withproblemId(problemId).withterm(term).build();


				adminiManager.updateObserver();
				
				newReleaseProbManager.updateNewProblem(newProb);// add
				adminiManager.changed(newProb);
				adminiManager.deleteObservers();

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				ModelAndView modelAndView = new ModelAndView("/AdminHome");
				modelAndView.addObject("problems", listAllProblems());

				// modelAndView.addObject("users", listAllUsers());

				modelAndView.addObject("submissions", listSubmissionForAll());

				return modelAndView;
			} catch (Exception e) {
				ModelAndView modelAndView = new ModelAndView("/AdminHome");
				modelAndView.addObject("problems", listAllProblems());
				modelAndView.addObject("submissions", listSubmissionForAll());

				// modelAndView.addObject("users", listAllUsers());
				return modelAndView;
			}
		} else {

			ModelAndView modelAndView = new ModelAndView("/AdminHome");
			return modelAndView;
		}

	}

	@RequestMapping(value = "/cs480/AdminHome/{userId}/{week}/setScore", method = RequestMethod.POST)
	public @ResponseBody ModelAndView setScore(
			@PathVariable("userId") String id, @PathVariable("week") int week,
			@RequestParam(value = "score") int score) {

		submissionManager.setScore(id, week, score);

		ModelAndView modelAndView = new ModelAndView("/AdminSecond");
		modelAndView.addObject("submissions", listSubmissionsForUser(id));
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/codeSubmit/signUp", method = RequestMethod.POST)
	public ModelAndView signUpByUser(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("/SignUp");
		User user = new User();
		user.setUserId(userId);
		adminiManager.updateUser(user);
		return modelAndView;
	}



	@RequestMapping(value = "/cs480/codeSubmit/signUp", method = RequestMethod.GET)
	public ModelAndView signUpByUser() {
		ModelAndView modelAndView = new ModelAndView("/SignUp");
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/codeSubmit/login", method = RequestMethod.POST)
	public ModelAndView logininByUser(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("loginHome");
		User user = adminiManager.getUser(userId);
		
		if (user != null) {
			modelAndView = new ModelAndView("codeSubmit");
			List<Submission> submission = submissionManager.getSubmissions(userId);
			if(submission != null){
				modelAndView.addObject("submissions", submission);
			}
			modelAndView.addObject("submissions", new ArrayList<Submission>());
			return modelAndView;
		}
		System.out.println(userId + " does not exist");
		return modelAndView;


	}


	@RequestMapping(value = "/list/userDatabase", method = RequestMethod.GET)
	public List<User> getUserId() {
		return adminiManager.getList();
	}

	@RequestMapping(value = "/cs480/list/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = adminiManager.getUser(userId);
		return user;
	}
}
