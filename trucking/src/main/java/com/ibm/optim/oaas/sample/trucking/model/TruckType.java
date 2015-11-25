package com.ibm.optim.oaas.sample.trucking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Class used to specify a truck type for the <code>truck.mod</code> shipment problem
 * model.
 * 
 * Instances of this class are mapped to entries of the <code>TruckTypes</code> tuple
 * set of the <code>truck.mod</code> model, which provides capacity, cost, and speed
 * information for each truck type. Properties are mapped to the corresponding
 * fields of the <code>truckType</code> tuple definition.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "truckType", scope = TruckType.class)
public class TruckType {

	String truckType;
	int capacity;
	int costPerMile;
	int milesPerHour; // Truck speed

	public TruckType(String truckType, int capacity, int costPerMile, int milesPerHour) {
		super();
		this.truckType = truckType;
		this.capacity = capacity;
		this.costPerMile = costPerMile;
		this.milesPerHour = milesPerHour;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(int costPerMile) {
		this.costPerMile = costPerMile;
	}

	public int getMilesPerHour() {
		return milesPerHour;
	}

	public void setMilesPerHour(int milesPerHour) {
		this.milesPerHour = milesPerHour;
	}
}
