package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to report the number of required trucks for each truck type in
 * solutions to the <code>truck.mod</code> problem.
 * 
 * Instances of this class are mapped to entries of the <code>NbTrucksOnRouteRes</code> 
 * post-processed tuple set of the <code>truck.mod</code> model. Properties are 
 * mapped to the corresponding fields of the <code>nbTrucksOnRouteRes</code> tuple 
 * definition.
 */
public class NbTrucksOnRouteRes {

	private Spoke spoke;

	private Hub hub;

	private TruckType truckType;

	private int nbTruck;

	public Spoke getSpoke() {
		return spoke;
	}

	public void setSpoke(Spoke spoke) {
		this.spoke = spoke;
	}

	public Hub getHub() {
		return hub;
	}

	public void setHub(Hub hub) {
		this.hub = hub;
	}

	public TruckType getTruckType() {
		return truckType;
	}

	public void setTruckType(TruckType truckType) {
		this.truckType = truckType;
	}

	public int getNbTruck() {
		return nbTruck;
	}

	public void setNbTruck(int nbTruck) {
		this.nbTruck = nbTruck;
	}
}
