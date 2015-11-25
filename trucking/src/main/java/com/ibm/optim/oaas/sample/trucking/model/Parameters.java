package com.ibm.optim.oaas.sample.trucking.model;

/**
 * Class used to specify global parameters for the <code>truck.mod</code> shipment 
 * problem model.
 * 
 * Instances of this class are mapped to entries of the <code>Parameters</code> 
 * singleton tuple of the <code>truck.mod</code> model. Properties are mapped to the 
 * corresponding fields of the <code>parameters</code> tuple definition.
 */
public class Parameters {

	// Maximum number of trucks for each type on a route
	private int maxTrucks;

	// Maximum volume of goods that can be handled on each path for each type of truck
	private int maxVolume;

	public Parameters(int maxTrucks, int maxVolume) {
		super();
		this.maxTrucks = maxTrucks;
		this.maxVolume = maxVolume;
	}

	public int getMaxTrucks() {
		return maxTrucks;
	}

	public void setMaxTrucks(int maxTrucks) {
		this.maxTrucks = maxTrucks;
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}
}
