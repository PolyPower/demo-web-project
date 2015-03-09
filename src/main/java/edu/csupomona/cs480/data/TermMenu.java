package edu.csupomona.cs480.data;

import java.util.HashMap;
import java.util.Map;

// This Term Menu acts as a factory and cache for Term flyweight objects
public class TermMenu {
	
	private Map<String, Term> terms = new HashMap<String, Term>();
	
	private static TermMenu instance = null;

	private TermMenu() { }
	
	public static TermMenu getInstance() {
		if(instance == null) {
			instance = new TermMenu();
		}
		return instance;
	}
	
	// Future vision: what way can I pass less parameters but still create the new object?
	public Term lookup(String problemID, int weekNo, String quarter, int year) {
		if(!terms.containsKey(problemID)) {
			terms.put(problemID, new Term(weekNo, quarter, year));
		}
		return terms.get(problemID);
	}
	
	public Term lookup(String problemID) {
		return terms.get(problemID);
	}
	
	public int totalTermsInDatabase() {
		return terms.size();
	}

}