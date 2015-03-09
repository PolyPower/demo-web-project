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
	private String fileName;
	private Term term;
	

	@JsonCreator
	public NewReleaseProb(@JsonProperty("filePath") String filePath,
			@JsonProperty("problemId") String problemId,
			@JsonProperty("fileName") String fileName,
			@JsonProperty("term") Term term) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.problemId = problemId;
		this.term = term;
	}

	public NewReleaseProb(NewReleaseProblemBuilder newReleaseProbBuilder) {

		this.fileName = newReleaseProbBuilder.fileName;
		this.filePath = newReleaseProbBuilder.filePath;
		this.problemId = newReleaseProbBuilder.problemId;
		this.term = newReleaseProbBuilder.term;

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
	
	public Term getTerm() {
		return term;
	}

	public static class NewReleaseProblemBuilder {
		private String filePath;
		private String problemId;
		private String fileName;
		private Term term;

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

		public NewReleaseProblemBuilder withterm(Term term) {
			this.term = term;
			return this;
		}

		public NewReleaseProb build() {
			return new NewReleaseProb(this);
		}

	}
}
