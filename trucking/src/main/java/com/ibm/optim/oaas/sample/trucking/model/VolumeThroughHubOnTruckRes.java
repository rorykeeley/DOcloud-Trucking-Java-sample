package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to report information about shipped quantities, detailed by (origin spoke, 
 * hub, destination spoke) routes in solutions to the <code>truck.mod</code> model.
 * 
 * Instances of this class are mapped to entries of the <code>OutVolumeThroughHubOnTruckRes</code> 
 * and <code>InVolumeThroughHubOnTruckRes</code> post-processed tuple sets of the 
 * <code>truck.mod</code> model, which provide quantities transported between spokes and 
 * hubs for each truck type, drilling down by final shipment destination. Properties are 
 * mapped to the corresponding fields of the <code>volumeThroughHubOnTruckRes</code> tuple 
 * definition.
 */
public class VolumeThroughHubOnTruckRes {

	private Spoke origin;
	private Hub hub;
	private Spoke destination;
	private TruckType truckType;
	private int quantity;

	public Spoke getOrigin() {
		return origin;
	}

	public void setOrigin(Spoke origin) {
		this.origin = origin;
	}

	public Hub getHub() {
		return hub;
	}

	public void setHub(Hub hub) {
		this.hub = hub;
	}

	public Spoke getDestination() {
		return destination;
	}

	public void setDestination(Spoke destination) {
		this.destination = destination;
	}

	public TruckType getTruckType() {
		return truckType;
	}

	public void setTruckType(TruckType truckType) {
		this.truckType = truckType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
