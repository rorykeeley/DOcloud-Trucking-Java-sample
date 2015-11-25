package com.ibm.optim.oaas.sample.trucking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Parent class used to specify a location (hub or spoke) in a logistic network
 * for the <code>truck.mod</code> shipment problem model.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name", scope = Location.class)
public class Location {

	private String name;

	public Location(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
