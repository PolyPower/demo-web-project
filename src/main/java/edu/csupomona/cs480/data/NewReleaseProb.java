package edu.csupomona.cs480.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * The basic user object.
 */

public class NewReleaseProb {

	private String filePath;
	private String problemId;
	private int problemNo;
	private String fileName;
	private int week;

	@JsonCreator
	public NewReleaseProb(@JsonProperty("filePath") String filePath,
			@JsonProperty("problemId") String problemId,
			@JsonProperty("problemNo") int problemNo,
			@JsonProperty("fileName") String fileName,
			@JsonProperty("weekNo") int week) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.problemId = problemId;
		this.problemNo = problemNo;
		this.week = week;
	}

	public NewReleaseProb(NewReleaseProblemBuilder newReleaseProbBuilder) {

		this.fileName = newReleaseProbBuilder.fileName;
		this.filePath = newReleaseProbBuilder.filePath;
		this.problemId = newReleaseProbBuilder.problemId;
		this.problemNo = newReleaseProbBuilder.problemNo;
		this.week = newReleaseProbBuilder.week;

	}

	public String getFilePath() {
		return filePath;
	}

	public String getproblemId() {
		return problemId;
	}

	public String getFileName() {
		return fileName;
	}

	public int getweek() {
		return week;
	}

	public int getproblemNo() {

		return problemNo;
	}

	public static class NewReleaseProblemBuilder {
		private String filePath;
		private String problemId;
		private int problemNo;
		private String fileName;
		private int week;

		public NewReleaseProblemBuilder withproblemId(String problemId) {
			this.problemId = problemId;
			return this;
		}

		public NewReleaseProblemBuilder withfilePath(String filePath) {
			this.filePath = filePath;
			return this;
		}

		public NewReleaseProblemBuilder withfileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public NewReleaseProblemBuilder withweekNo(int week) {
			this.week = week;
			return this;
		}

		public NewReleaseProblemBuilder withproblemNo(int problemNo) {
			this.problemNo = problemNo;
			return this;
		}

		// public String getterm() {
		//
		// return term;
		// }
		//
		// public String getproblemDescription() {
		//
		// return problemDescription;
		// }
		//
		// public NewReleaseProblemBuilder withterm(String term) {
		// this.term = term;
		// return this;
		// }

		// public NewReleaseProblemBuilder withproblemDes(String
		// problemDescription) {
		// this.problemDescription = problemDescription;
		// return this;
		// }
		// public NewReleaseProblemBuilder withproblemNo(int problemNo) {
		// this.problemNo = problemNo;
		// return this;
		// }

		public NewReleaseProb build() {
			return new NewReleaseProb(this);
		}

	}
}
