package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to report information about shipped quantities, detailed by (spoke, hub) 
 * routes in solutions to the <code>truck.mod</code> model.
 * 
 * Instances of this class are mapped to entries of the <code>InBoundAggregated</code> 
 * and <code>OutBoundAggregated</code> post-processed tuple sets of the <code>truck.mod</code> 
 * model, which provide quantities transported between spokes and hubs for each truck type. 
 * Properties are mapped to the corresponding fields of the <code>aggregatedReport</code> 
 * tuple definition.
 */
public class AggregatedReport {

	private Spoke spoke;
	private Hub hub;
	private TruckType truckType;
	private int quantity;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
