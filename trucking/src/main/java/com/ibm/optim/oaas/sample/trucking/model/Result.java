package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to report information about the objective value for solutions to the
 * <code>truck.mod</code> model.
 * 
 * Instances of this class are mapped to entries of the <code>Result</code> post-processed
 * singleton tuple of the <code>truck.mod</code> model. Properties are mapped to the 
 * corresponding fields of the <code>result</code> tuple definition.
 */
public class Result {

	private int totalCost;

	public Result() {
		super();
	}

	public int getTotalCost() {
		return totalCost;
	}
}
