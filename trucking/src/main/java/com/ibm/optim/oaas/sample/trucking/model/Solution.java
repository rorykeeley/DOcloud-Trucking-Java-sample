package com.ibm.optim.oaas.sample.trucking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class used as a container to collect all output information for a solution
 * calculated by solving the <code>truck.mod</code> problem.
 * 
 * All properties of this class are mapped to corresponding tuple and tuple sets in the
 * post-processing section of the <i>truck.mod</i> model.
 */
public class Solution {

	@JsonProperty("Result")
	private Result result;

	@JsonProperty("NbTrucksOnRouteRes")
	private List<NbTrucksOnRouteRes> nbTrucksOnRouteRes = new ArrayList<NbTrucksOnRouteRes>();

	@JsonProperty("InVolumeThroughHubOnTruckRes")
	private List<VolumeThroughHubOnTruckRes> inVolumeThroughHubOnTruckRes = new ArrayList<VolumeThroughHubOnTruckRes>();

	@JsonProperty("OutVolumeThroughHubOnTruckRes")
	private List<VolumeThroughHubOnTruckRes> outVolumeThroughHubOnTruckRes = new ArrayList<VolumeThroughHubOnTruckRes>();

	@JsonProperty("InBoundAggregated")
	private List<AggregatedReport> inBoundAggregated = new ArrayList<AggregatedReport>();

	@JsonProperty("OutBoundAggregated")
	private List<AggregatedReport> outBoundAggregated = new ArrayList<AggregatedReport>();

	public Result getResult() {
		return result;
	}

	public List<NbTrucksOnRouteRes> getNbTrucksOnRouteRes() {
		return nbTrucksOnRouteRes;
	}

	public List<VolumeThroughHubOnTruckRes> getInVolumeThroughHubOnTruckRes() {
		return inVolumeThroughHubOnTruckRes;
	}

	public List<VolumeThroughHubOnTruckRes> getOutVolumeThroughHubOnTruckRes() {
		return outVolumeThroughHubOnTruckRes;
	}

	public List<AggregatedReport> getInBoundAggregated() {
		return inBoundAggregated;
	}

	public List<AggregatedReport> getOutBoundAggregated() {
		return outBoundAggregated;
	}

	public void displaySolution() {
		System.out.println("Total cost = " + getResult().getTotalCost());
		displaySeparatorLine();
		for (VolumeThroughHubOnTruckRes ivthot : getInVolumeThroughHubOnTruckRes()) {
			System.out.println("Using: " + ivthot.getTruckType().getTruckType()
					+ " \t--> from: " + ivthot.getOrigin().getName()
					+ " to Hub: " + ivthot.getHub().getName()
					+ " (shipment destination: "
					+ ivthot.getDestination().getName()
					+ ") --> shipped quantity = " + ivthot.getQuantity());
		}
		displaySeparatorLine();
		for (VolumeThroughHubOnTruckRes ovthot : getOutVolumeThroughHubOnTruckRes()) {
			System.out.println("Using: " + ovthot.getTruckType().getTruckType()
					+ " \t--> from Hub: " + ovthot.getHub().getName() + " to: "
					+ ovthot.getDestination().getName() + " (shipment source: "
					+ ovthot.getOrigin().getName()
					+ ") --> shipped quantity = " + ovthot.getQuantity());
		}
		displaySeparatorLine();
		for (NbTrucksOnRouteRes nbTrucksOnRoute : getNbTrucksOnRouteRes()) {
			System.out.println(nbTrucksOnRoute.getNbTruck()
					+ " truck(s) of type: "
					+ nbTrucksOnRoute.getTruckType().getTruckType()
					+ "\t are assigned to route: Spoke "
					+ nbTrucksOnRoute.getSpoke().getName() + " <--> Hub "
					+ nbTrucksOnRoute.getHub().getName()
					+ " (transport capacity = " + nbTrucksOnRoute.getNbTruck()
					* nbTrucksOnRoute.getTruckType().getCapacity() + " units)");
		}
		displaySeparatorLine();
		for (AggregatedReport iba : getInBoundAggregated()) {
			System.out.println("Aggregated quantity transported from Spoke: "
					+ iba.getSpoke().getName() + " to Hub: "
					+ iba.getHub().getName() + " using truck type: "
					+ iba.getTruckType().getTruckType() + " \t= "
					+ iba.getQuantity());
		}
		displaySeparatorLine();
		for (AggregatedReport oba : getOutBoundAggregated()) {
			System.out.println("Aggregated quantity transported from Hub: "
					+ oba.getHub().getName() + " to Spoke: "
					+ oba.getSpoke().getName() + " using truck type: "
					+ oba.getTruckType().getTruckType() + " \t= "
					+ oba.getQuantity());
		}
		displaySeparatorLine();
	}

	private void displaySeparatorLine() {
		System.out.println("------------------------------------------------");
	}
}
