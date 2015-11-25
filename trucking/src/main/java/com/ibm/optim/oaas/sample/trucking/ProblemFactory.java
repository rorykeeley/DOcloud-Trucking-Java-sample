package com.ibm.optim.oaas.sample.trucking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ibm.optim.oaas.sample.trucking.model.Hub;
import com.ibm.optim.oaas.sample.trucking.model.LoadTime;
import com.ibm.optim.oaas.sample.trucking.model.Problem;
import com.ibm.optim.oaas.sample.trucking.model.Route;
import com.ibm.optim.oaas.sample.trucking.model.Shipment;
import com.ibm.optim.oaas.sample.trucking.model.Spoke;
import com.ibm.optim.oaas.sample.trucking.model.TruckType;

/**
 * Factory class for creation of problem instances.
 */
public class ProblemFactory {
	private static final int MIN_QTY = 50;

  /**
   * Return a {@link Problem} instance using a fixed logistic network with randomly generated
   * {@link Shipment} orders.
   * 
   * @param seed Seed for the random value generator
   * @param meanQty Mean value of the Gaussian distribution for generating shipment quantities
   * @param standardDeviation Standard deviation value of the Gaussian distribution for generating 
   *          shipment quantities
   * @return The generated {@link Problem} instance
   */
	public Problem createProblemWithRandomShipments(long seed, double meanQty, double standardDeviation) {
		Random rand = new Random(seed);
		Map<String, Spoke> spokeByName = new HashMap<String, Spoke>();
		Problem pb = createProblemMasterData(spokeByName);

		for (Spoke source : spokeByName.values()) {
			for (Spoke destination : spokeByName.values()) {
				if (source != destination) {
					int quantity = (int) (rand.nextGaussian() * standardDeviation + meanQty);
					quantity = (quantity < MIN_QTY ? MIN_QTY : quantity);
					pb.getShipments().add(new Shipment(source, destination, quantity));
				}
			}
		}
		return pb;
	}

  /**
   * Return a {@link Problem} instance using a fixed logistic network and fixed {@link Shipment}
   * orders.
   * @return The generated {@link Problem} instance
   */
	public Problem createProblem1() {
		Map<String, Spoke> spokeByName = new HashMap<String, Spoke>();
		Problem pb = createProblemMasterData(spokeByName);

		Spoke A = spokeByName.get("A");
		Spoke B = spokeByName.get("B");
		Spoke C = spokeByName.get("C");
		Spoke D = spokeByName.get("D");
		Spoke E = spokeByName.get("E");
		Spoke F = spokeByName.get("F");

		pb.getShipments().add(new Shipment(A, B, 300));
		pb.getShipments().add(new Shipment(A, C, 250));
		pb.getShipments().add(new Shipment(A, D, 350));
		pb.getShipments().add(new Shipment(A, E, 145));
		pb.getShipments().add(new Shipment(A, F, 300));
		pb.getShipments().add(new Shipment(B, A, 185));
		pb.getShipments().add(new Shipment(B, C, 200));
		pb.getShipments().add(new Shipment(B, D, 221));
		pb.getShipments().add(new Shipment(B, E, 263));
		pb.getShipments().add(new Shipment(B, F, 197));
		pb.getShipments().add(new Shipment(C, A, 143));
		pb.getShipments().add(new Shipment(C, B, 178));
		pb.getShipments().add(new Shipment(C, D, 258));
		pb.getShipments().add(new Shipment(C, E, 221));
		pb.getShipments().add(new Shipment(C, F, 106));
		pb.getShipments().add(new Shipment(D, A, 75));
		pb.getShipments().add(new Shipment(D, B, 135));
		pb.getShipments().add(new Shipment(D, C, 245));
		pb.getShipments().add(new Shipment(D, E, 283));
		pb.getShipments().add(new Shipment(D, F, 155));
		pb.getShipments().add(new Shipment(E, A, 123));
		pb.getShipments().add(new Shipment(E, B, 234));
		pb.getShipments().add(new Shipment(E, C, 143));
		pb.getShipments().add(new Shipment(E, D, 78));
		pb.getShipments().add(new Shipment(E, F, 107));
		pb.getShipments().add(new Shipment(F, A, 201));
		pb.getShipments().add(new Shipment(F, B, 157));
		pb.getShipments().add(new Shipment(F, C, 169));
		pb.getShipments().add(new Shipment(F, D, 212));
		pb.getShipments().add(new Shipment(F, E, 104));
		return pb;
	}

  /**
   * Return a {@link Problem} instance populated with a fixed logistic network.
   * @param spokeByName Map containing all spokes in the created logistic network.
   * @return
   */
	private Problem createProblemMasterData(Map<String, Spoke> spokeByName) {
		Problem pb = new Problem();

		Spoke A = new Spoke("A", 360, 1080);
		Spoke B = new Spoke("B", 400, 1150);
		Spoke C = new Spoke("C", 380, 1200);
		Spoke D = new Spoke("D", 340, 900);
		Spoke E = new Spoke("E", 420, 800);
		Spoke F = new Spoke("F", 370, 1070);

		spokeByName.put(A.getName(), A);
		spokeByName.put(B.getName(), B);
		spokeByName.put(C.getName(), C);
		spokeByName.put(D.getName(), D);
		spokeByName.put(E.getName(), E);
		spokeByName.put(F.getName(), F);

		Hub G = new Hub("G");
		Hub H = new Hub("H");

		Spoke[] spokes = { A, B, C, D, E, F };
		pb.getSpokes().addAll(Arrays.asList(spokes));

		Hub[] hubs = { G, H };
		pb.getHubs().addAll(Arrays.asList(hubs));

		TruckType SmallTruck = new TruckType("SmallTruck", 400, 10, 55);
		TruckType BigTruck = new TruckType("BigTruck", 700, 15, 45);

		TruckType[] truckTypes = { SmallTruck, BigTruck };
		pb.getTruckTypes().addAll(Arrays.asList(truckTypes));

		LoadTime[] loadTimes = { new LoadTime(G, SmallTruck, 30),
				new LoadTime(G, BigTruck, 55), new LoadTime(H, SmallTruck, 35),
				new LoadTime(H, BigTruck, 50) };
		pb.getLoadTimes().addAll(Arrays.asList(loadTimes));

		pb.getRoutes().add(new Route(A, G, 200));
		pb.getRoutes().add(new Route(A, H, 50));
		pb.getRoutes().add(new Route(B, G, 120));
		pb.getRoutes().add(new Route(B, H, 100));
		pb.getRoutes().add(new Route(C, H, 110));
		pb.getRoutes().add(new Route(D, G, 70));
		pb.getRoutes().add(new Route(D, H, 100));
		pb.getRoutes().add(new Route(E, G, 120));
		pb.getRoutes().add(new Route(E, H, 100));
		pb.getRoutes().add(new Route(F, H, 105));

		return pb;
	}
}
