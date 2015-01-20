package edu.csupomona.cs480.data;

import java.util.HashMap;

/**
 * This class is a HashMap, but we extend the HashMap
 * class so that we can rename it to something meaningful.
 * <p>
 * Basically, the key of the map is the unique Problem ID, 
 * (although I would like to change this to be Session)
 * and the value is the actual Problem object.
 * <p>
 * Using a HashMap allows us to quickly query the Problem
 * object.
 *
 * @author khamilleas
 *
 */
@SuppressWarnings("serial")
public class ProblemMap extends HashMap<String, Problem> {

}
