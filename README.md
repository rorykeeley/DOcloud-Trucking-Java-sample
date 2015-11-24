# IBM Decision Optimization on Cloud Trucking Java Client sample

IBM Decision Optimization on Cloud (DOcloud) allows you to solve optimization
problems on the cloud without installing or configuring a solver. 

This sample shows you how you can build a simple Java application that integrates 
with the IBM Decision Optimization on Cloud service, streaming data and results 
between the application and DOcloud. You can then solve the model on DOcloud and 
return the results to your local data model or application. The sample shows you 
the necessary Java libraries and the API that you need to create and send job requests.

In this sample, the optimization problem is a dispatching system for scheduling a fleet
of trucks using a hub and spoke system. The same optimization problem is also used in the 
[Green Truck Sample](https://github.com/IBMDecisionOptimization/DOcloud-GreenTruck-sample). 
The problem details are described [later in the Optimization Problem section](#optimization-problem).

## Sample contents

The sample consists of a Java project which contains:

* Java files: `trucking/src/main/java/com/ibm/optim/oaas/sample/trucking`
* The optimization (OPL .mod, .dat and project) files: `trucking/src/main/resources/com/ibm/optim/oaas/sample/trucking/opl` 
* Maven build file: `trucking/pom.xml`
* Eclipse project file: `trucking/.project`

## Installation

### Prerequisites

1- Install [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html). 
Maven will check the version based on either your path or your `JAVA_HOME` environment 
variable. If Maven cannot find the correct version, it will notify you with an error 
message. The version of Java from your path can be checked using this command:
```
java -version
```

2- Install [Maven](https://Maven.apache.org/download.cgi).
Once installed, you can check that it is accessible using this command:
```
mvn --version
```

3- Get the IBM DOcloud base URL and an API key, which are accessible on the 
[Get API Key page](https://dropsolve-oaas.docloud.ibmcloud.com/dropsolve/api) once you 
have registered and logged in to DOcloud. Copy the base URL and the API key 
to the maven properties in your `~/.m2/settings.xml` settings file, where
* `yourKey` is the API key (clientID) that you generate after registering.
* `yourURL` is the base URL that you access after registering.
```xml
<profile>
  <id>docloud</id>
  <activation>
    <activeByDefault>true</activeByDefault>
  </activation>
  <properties>
      <docloud.baseurl>yourURL</docloud.baseurl>
      <docloud.apikey.clientid>yourKey</docloud.apikey.clientid>
  </properties>
</profile>
```

4- Download and install the IBM Docloud API for Java client libraries.
You can download the library from the [developer center](https://developer.ibm.com/docloud/docs/java-client-api/java-client-library/).
Extract the jar file starting with `docloud_api_java_client` from the zip file (do not take the javadoc jar file).
Then add the jar file to your Maven local repository like this:
```
mvn install:install-file -Dfile=<path-to-file> -DgroupId=com.ibm.optim.oaas -DartifactId=api_java_client -Dversion=1.0-R1-SNAPSHOT -Dpackaging=jar
```

### Build with Maven

1- From the `trucking` subdirectory, compile with Maven. 
```
mvn install
```

### Run the sample with a single job request

The application can be run with either a single job request or with multiple job requests 
from the same model, but with different data for each. The default pom.xml is set up to run
as a single request using the main class `com.ibm.optim.oaas.sample.trucking.Controller`.

1- From the `trucking` subdirectory, execute with Maven.
```
mvn exec:java
```

### Run the sample with multiple job requests from the same model with different data

To run the sample application with multiple job requests from the same model with different data,
modify the pom.xml file to set the main class to `com.ibm.optim.oaas.sample.trucking.ControllerMultiJobs`:
```xml
<mainClass>com.ibm.optim.oaas.sample.trucking.ControllerMultiJobs</mainClass>
```

1- After updating the pom.xml file, execute with Maven from the `trucking` subdirectory.
```
mvn exec:java
```

## Dependencies
    
### Server side runtime dependencies loaded by Maven (see the pom.xml file)

* com.ibm.optim.org.slf4j:slf4j-jdk14
* om.ibm.optim.oaas:api_java_client
* com.ibm.icu:icu4j
* org.apache.httpcomponents:httpclient
* com.fasterxml.jackson.core:jackson-databind

## Optimization Problem

A shipping company uses a dispatching system to schedule its truck fleet. The dispatching 
system collects orders from the order management system, assigns the orders to trucks, and 
schedules the departures and deliveries. Its functions include generating bills of loading, 
loading tables, route maps for the drivers’ GPS, and departure time-tables. It also updates 
the order management system with projected delivery windows, which in turn informs the 
recipients. Currently, the assignment of orders to trucks, which defines the truck routes, 
is done heuristically using a set of business rules that the company has found to be 
effective in the past. However, the VP of Operations believes that substantial cost 
savings and on-time performance improvements might be achievable with a more systematic 
routing algorithm. The company’s Operations Research department has created such an 
algorithm using IBM Decision Optimization on Cloud. The IT department now has been tasked 
to deploy this routing algorithm integrated in the dispatching system.

The shipping company has a hub and spoke system. In describing this problem,
a single spoke is associated with a single node, and we therefore simplify 
by discussing spokes only. 

The shipments to be delivered are specified by an originating spoke, a destination spoke, 
and a shipment volume. The trucks have different types defined by a maximum capacity, 
a speed, and a cost per mile. The model is to assign the right number of trucks to each 
route in order to minimize the cost of transshipment and meet the volume requirements. 
There is a minimum departure time and a maximum return time for trucks at a spoke, and a 
load and unload time at the hub. Trucks of different types travel at different speeds. 
Therefore, shipments are available at each hub in a timely manner. Volume availability 
constraints are considered, that is, the shipments that will be carried back from a hub 
to a spoke by a truck must be available for loading before the truck leaves.

The assumptions are:
* Exactly the same number of trucks that go from spoke to hub return from hub to spoke
* Each truck arrives at a hub as early as possible and leaves as late as possible
* The shipments can be broken arbitrarily into smaller packages and shipped through different paths

## Sample Results

Once the job is executed by `Controller.java`, the resulting data received as a JSON stream is 
deserialized into Java objects. The `Solution` Java class is the container for the result data 
and the `displaySolution()` method is then invoked on this instance to pretty print in the console,
showing the total cost associated with the schedule and the actual scheduling decisions, along with other 
higher level reports. Here is an extract of the sample results:

```
Total cost = 29750
------------------------------------------------
Using: BigTruck    --> from: A to Hub: H (shipment destination: B) --> shipped quantity = 300
Using: BigTruck    --> from: A to Hub: H (shipment destination: C) --> shipped quantity = 250
Using: BigTruck    --> from: A to Hub: H (shipment destination: D) --> shipped quantity = 350
Using: BigTruck    --> from: A to Hub: H (shipment destination: E) --> shipped quantity = 145
Using: BigTruck    --> from: A to Hub: H (shipment destination: F) --> shipped quantity = 300
Using: BigTruck    --> from: B to Hub: H (shipment destination: A) --> shipped quantity = 185
Using: SmallTruck  --> from: B to Hub: H (shipment destination: C) --> shipped quantity = 145
Using: BigTruck    --> from: B to Hub: H (shipment destination: C) --> shipped quantity = 55
Using: SmallTruck  --> from: B to Hub: H (shipment destination: D) --> shipped quantity = 221
Using: BigTruck    --> from: B to Hub: H (shipment destination: E) --> shipped quantity = 263
.
.
.
------------------------------------------------
2 truck(s) of type: BigTruck  are assigned to route: Spoke A <--> Hub H (transport capacity = 1400 units)
1 truck(s) of type: SmallTruck are assigned to route: Spoke B <--> Hub H (transport capacity = 400 units)
1 truck(s) of type: BigTruck   are assigned to route: Spoke B <--> Hub H (transport capacity = 700 units)
1 truck(s) of type: SmallTruck are assigned to route: Spoke C <--> Hub H (transport capacity = 400 units)
.
.
.
Aggregated quantity transported from Hub: H to Spoke: E using truck type: BigTruck 	= 616
Aggregated quantity transported from Hub: H to Spoke: F using truck type: SmallTruck 	= 166
Aggregated quantity transported from Hub: H to Spoke: F using truck type: BigTruck 	= 699
------------------------------------------------
```
For an operational solution, this data can be either:
* Streamed directly to a noSQL database such as MongoDB
* Stored in a relational database (since the data structures in the results map directly to tables)
* Streamed into a transactional system in order to create actual routes and timetables for drivers

## License

This sample is delivered under the Apache License Version 2.0, January 2004 (see `LICENSE.txt`).
