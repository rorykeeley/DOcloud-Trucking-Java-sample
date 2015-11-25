package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify shipments to be planned by the <code>truck.mod</code> problem
 * model.
 * 
 * Instances of this class are mapped to entries of the <code>Shipments</code> tuple
 * set of the <code>truck.mod</code> model, which provides the list of quantities to
 * be shipped between an origin and a destination spoke. Properties are mapped
 * to the corresponding fields of the <code>shipment</code> tuple definition.
 */
public class Shipment {

	private Location origin;
	private Location destination;
	private int totalVolume;

	public Shipment(Location origin, Location destination, int totalVolume) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.totalVolume = totalVolume;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public int getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(int totalVolume) {
		this.totalVolume = totalVolume;
	}
}
