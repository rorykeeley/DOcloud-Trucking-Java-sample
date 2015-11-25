package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify a route between a spoke and a hub in a logistic network
 * for the <code>truck.mod</code> shipment problem model.
 * 
 * Instances of this class are mapped to entries of the <code>Routes</code> tuple set
 * of the <code>truck.mod</code> model, which provides the list of all (spoke, hub)
 * routes in the logistic network with their associated distances. Properties
 * are mapped to the corresponding fields of the <code>routeInfo</code> tuple
 * definition.
 */
public class Route {

	private Location spoke;
	private Location hub;
	private int distance;

	public Route(Location source, Location target, int distance) {
		super();
		this.spoke = source;
		this.hub = target;
		this.distance = distance;
	}

	public Location getSpoke() {
		return spoke;
	}

	public void setSpoke(Location spoke) {
		this.spoke = spoke;
	}

	public Location getHub() {
		return hub;
	}

	public void setHub(Location hub) {
		this.hub = hub;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
