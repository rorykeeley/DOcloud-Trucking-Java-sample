package com.ibm.optim.oaas.sample.trucking;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.optim.oaas.client.OperationException;
import com.ibm.optim.oaas.client.job.JobClient;
import com.ibm.optim.oaas.client.job.JobClientFactory;
import com.ibm.optim.oaas.client.job.JobException;
import com.ibm.optim.oaas.client.job.JobExecutor;
import com.ibm.optim.oaas.client.job.JobExecutorFactory;
import com.ibm.optim.oaas.client.job.JobOutput;
import com.ibm.optim.oaas.client.job.JobResponse;
import com.ibm.optim.oaas.sample.trucking.model.Problem;
import com.ibm.optim.oaas.sample.trucking.model.ResultDeserializerContext;
import com.ibm.optim.oaas.sample.trucking.model.Solution;

/**
 * This controller creates a single job request for a problem instance to be
 * processed by the <code>truck.mod</code> shipment problem model. Once the 
 * problem is solved, the results are mapped to an instance of a {@link Solution} 
 * class and displayed in the console.
 */
public class Controller {

	public static Logger LOG = Logger.getLogger(Controller.class.getName()); 
	
	JobExecutor executor;
	JobClient jobclient;
	URL modFile;
	
	// Create mapper instance for Java --> JSON serialization
	ObjectMapper mapper = new ObjectMapper(); 

  /**
   * Constructor.
	 * @param baseURL
	 * @param apiKeyClientId
   */
	public Controller(String baseURL, String apiKeyClientId) {

		// Create a client
		jobclient = JobClientFactory.createDefault(baseURL, apiKeyClientId);

		// Create executor
		executor = JobExecutorFactory.createDefault();
				
		// Cet the OPL model file
		modFile = Controller.class.getResource("opl/truck.mod");
		if (modFile == null) {
			throw new RuntimeException("The OPL model file cannot be found");
		}
	}

	public Solution optimize(Problem pb) {

		// Create a mapper for JSON --> Java de-serialization, that uses the
		// problem instance as context for resolving references to input data
		ObjectMapper deserialize = new ObjectMapper(null, null,
				new ResultDeserializerContext(pb));

		try {
			// Simple submit
			JobResponse response = jobclient.newRequest()
					.input("model.mod", modFile)
					.input("model.json", mapper, pb)
					.log(new File("results.log"))
					.output(deserialize, Solution.class)
          .deleteOnCompletion(true)
					.timeout(5, TimeUnit.MINUTES).execute(executor).get();

			switch (response.getJob().getExecutionStatus()) {
			case PROCESSED:
				List<? extends JobOutput> output = response.getOutput();
				Solution solution = (Solution) output.get(0).getContent();
				return solution;
			case FAILED:
				// Get the failure message if one has been defined
				String message = "";
				if (response.getJob().getFailureInfo() != null) {
					message = response.getJob().getFailureInfo().getMessage();
				}
				LOG.info("Failed " + message);
				break;
			default:
				break;
			}

		} catch (OperationException | InterruptedException | ExecutionException
				| IOException |  JobException  e) {
			LOG.log(Level.WARNING, "Error while executing job",e);
		}

		return null;
	}

	public void shutdown(){
	   executor.shutdown();
	}
	
  /**
   * Main.
   * @param args
   */
	public static void main(String[] args) {
		if (args.length<2){
			System.out.println("The base URL and API key are missing");
			System.exit(1);
		}
		
		// Get base URL and API key from the given arguments
		String baseURL = args[0];
		String apiKeyClientId = args[1];

		// Create the controller
		Controller ctrl = new Controller(baseURL, apiKeyClientId);

		// Create and populate instance of Problem to be submitted as input data
		// to solve the job
		ProblemFactory pbFactory = new ProblemFactory();
		Problem pb = pbFactory.createProblem1();

		// Optimize and display the solution
		Solution solution = ctrl.optimize(pb);
		if (solution != null) {
		  solution.displaySolution();
		}

		// Shut down the controller
		ctrl.shutdown();
	}
}
