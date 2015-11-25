package com.ibm.optim.oaas.sample.trucking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class used as a container to collect all required information for a problem instance 
 * to be processed by the <code>truck.mod</code> model.
 * 
 * All properties of this class are mapped to corresponding tuple and tuple sets in the
 * input data section of the <code>truck.mod</code> model.
 */
public class Problem {
  @JsonIgnore
  private String problemId;

	@JsonProperty("Parameters")
	private Parameters parameters = new Parameters(100, 5000);

	@JsonProperty("Hubs")
	private List<Hub> hubs = new ArrayList<Hub>();

	@JsonProperty("Spokes")
	private List<Spoke> spokes = new ArrayList<Spoke>();

	@JsonProperty("TruckTypes")
	private List<TruckType> truckTypes = new ArrayList<TruckType>();

	@JsonProperty("LoadTimes")
	private List<LoadTime> loadTimes = new ArrayList<LoadTime>();

	@JsonProperty("Routes")
	private List<Route> routes = new ArrayList<Route>();

	@JsonProperty("Shipments")
	private List<Shipment> shipments = new ArrayList<Shipment>();

	public List<Hub> getHubs() {
		return hubs;
	}

	public List<Spoke> getSpokes() {
		return spokes;
	}

	public List<TruckType> getTruckTypes() {
		return truckTypes;
	}

	public List<LoadTime> getLoadTimes() {
		return loadTimes;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public Parameters getParameters() {
		return parameters;
	}

  public String getProblemId() {
    return problemId;
  }

  public void setProblemId(String problemId) {
    this.problemId = problemId;
  }
}
