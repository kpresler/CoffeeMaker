package edu.ncsu.csc.CoffeeMaker.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * TestRunner class for the Cucumber tests. Adjust the "features" parameter
 * above as necessary to run just a subset of the tests. The body of the class
 * should be blank -- the annotations are all that is required
 *
 * @author Kai Presler-Marshall
 * @author Sarah Elder
 *
 */
@RunWith ( Cucumber.class )
@CucumberOptions ( features = "src/test/resources/basic/" )
public class TestRunner {

}
