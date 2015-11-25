package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify a hub in a logistic network for the <code>truck.mod</code>
 * shipment problem model.
 * 
 * Instances of this class are mapped to entries of the <code>Hubs</code> tuple set of
 * the <code>truck.mod</code> model, which provides the list of all hubs in the
 * logistic network. Properties are mapped to the corresponding fields of the
 * <code>location</code> tuple definition.
 */
public class Hub extends Location {

	public Hub(String name) {
		super(name);
	}
}
