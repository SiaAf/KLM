<h1>Demo For KLM</h1> <br>
<p>
The web application developed for end users to be able to search their flights and get the fare.<br>
The web application has these functionalities:
</p>
- Users can search their origin by putting characters in the input field. By putting each character user will see a dropdown list which is sorted by origin code
    to choose her/his origin easily (the main page http://localhost:8083) <br> 
- Users can search their destination by putting characters in the input field. By putting each character user will see a dropdown list which 
    is sorted by destination code to choose her/his destination easily(the main page) <br>
- By selection origin and destination and clicking on search button they will get the information about the origin, destination and Fare(the main page) <br>
- Users can go to airports page (http://localhost:8083/airports) to see the list of all airports with several pages (pagination). Each page is sorted based on airport code<br>
- Users will be able to search their airports in the  airports page <br>
- Developers will be able to see the metrics from actuator http://localhost:8083/actuator/metrics <br>
- Developers also could use Prometheus by running the command mentioned below in the metrics section<br>

<h1>Application</h1><br>
The application consists of two different parts naming frontend and backend. The front has been set up <br>
with Spring boot version 2.2.2 java 8 and front end is based on angular 4.The backend applications is tied <br>
with the backend server which should be run on port 8080 (can be modified from application.properties).<br> 

<h1>Running the application</h1>
The backend can be run on port 8083 by running _./gradlew bootRun_ in the root of the application.<br>
The and front end can be run port 4200 by running _npm install_ and  _ng serve_ on frontend root separately.<br> 

If you want to run the who project all together you can first build the jar file by _./gradlew bootJar_ <br>
and run the jar file by _java -jar build/libs/spring-angular-0.0.1-SNAPSHOT.jar_ the you should be able to <br>
see the Angular page on port 8083.<br>


<h1>Spring boot application</h1>
The application has the 3 main section:<br>
- Api controllers (resources)
- Services 
- Domains
<br>

<h3>Api controllers</h3>
The application has 2 controllers for getting airports and fares. and the end points are:<br>
- /airports/params (GET) with 4 none required parameters naming size,page,lang,term returns AirportDto<br>
- /fares/{OrigCode}/{desCode} (GET) for getting fares returns FareDto<br>

<h3>Services</h3>
The application consists of 5 different services to handle what is needed for back front end.
- Airport Service: this service gets the httpResponse from the HttpService and ultimately makes the airportDto for front end.<br>
- Fare Service: creates fare and fareDto for the front end<br>
- Http Service: gets data from the server<br>
- Oauth Service: takes care of getting fresh token from the serve<br>
- Task Service: runs the 3 threads (for getting data for fare, origin and destination) and aggregate the 3 parts to make a fare Dto for front end<br>
    whenever users requests for getting fare 3, request parallelly send to the backend to get data for origin, destination and the fare.



<h1>Metrics</h1>
As mentioned above developers could see the metrics here http://localhost:8083/actuator/metrics<br>  
To have a visual dashboard I have also added a yml file to the application for Prometheus.<br>
what should be done is:<br>
- docker pull prom/prometheus (pull the image)
- docker image ls if the image got downloaded
- docker run -d --name=prometheus -p 9090:9090 -v <path_to_yml_file>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
- prometheus should be visible at port 9090










