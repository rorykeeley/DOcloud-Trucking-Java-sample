package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify load time information at a hub for a truck type
 * in the <code>truck.mod</code> shipment problem model.
 * 
 * Instances of this class are mapped to entries of the <code>LoadTimes</code>
 * tuple set of the <code>truck.mod</code> model, which provides the load 
 * time information for all pairs (hub, truck type). Properties are mapped to
 * the corresponding fields of the <code>loadTimeInfo</code> tuple definition.
 */
public class LoadTime {

	private Hub hub;
	private TruckType truckType;
	private int loadTime;

	public LoadTime(Hub hub, TruckType truckType, int loadTime) {
		super();
		this.hub = hub;
		this.truckType = truckType;
		this.loadTime = loadTime;
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

	public int getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(int loadTime) {
		this.loadTime = loadTime;
	}
}
