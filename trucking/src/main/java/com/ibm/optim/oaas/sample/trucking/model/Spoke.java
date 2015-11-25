package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify a spoke in a logistic network for the <code>truck.mod</code>
 * shipment problem model.
 * 
 * Instances of this class are mapped to entries of the <code>Spokes</code> tuple set
 * of the <code>truck.mod</code> model. Properties are mapped to the corresponding
 * fields of the <code>spoke</code> tuple definition.
 */
public class Spoke extends Location {

	int minDepTime; // Earliest departure time from spoke
	int maxArrTime; // Latest arrival time to spoke

	public Spoke(String name, int minDepTime, int maxArrTime) {
		super(name);
		this.minDepTime = minDepTime;
		this.maxArrTime = maxArrTime;
	}

	public int getMinDepTime() {
		return minDepTime;
	}

	public void setMinDepTime(int minDepTime) {
		this.minDepTime = minDepTime;
	}

	public int getMaxArrTime() {
		return maxArrTime;
	}

	public void setMaxArrTime(int maxArrTime) {
		this.maxArrTime = maxArrTime;
	}
}
