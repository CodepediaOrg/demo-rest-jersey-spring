# demo-rest-jersey-spring @ [![Codingpedia.org 100%](http://img.shields.io/badge/Codingpedia.org-100%25-blue.svg)](http://www.codingpedia.org)

## Prerequisites:
* MySQL 5.5 or 5.6 
* IDE ( preffered Eclipse 4.3+) 
* JDK 1.7 (if you want to use Jetty 9 with the jetty-maven-plugin from project)
* Maven 3.*

## Install and run the project 
1. download/clone the project 
2. prepare the database
  * import in MySQL the self-contained file that comes with the project - [demo-rest-jersey-spring / src / main / resources / input_data / DumpRESTdemoDB.sql](https://github.com/Codingpedia/demo-rest-jersey-spring/blob/master/src/main/resources/input_data/DumpRESTdemoDB.sql)
  * username/password - `rest_demo`/`rest_demo`
3. change to the root folder of the project and excute the following maven command 
  * `mvn clean install jetty:run  -Djetty.port=8888 -DskipTests=true`
  * now the REST api is up and running with Jetty on `localhost:8888` 
  
> **Note:** you could run a similar configuration from Eclipse if you have the m2e plugin installed - see pic below

> **Note:** after you `mvn install` the application, you can deploy the generated __.war__ file in any web container like Tomcat for example. 

## Testing the project 

### From Maven (failsafe plugin)
Run the following maven command on the console in the root directory of the project 
  
  ```mvn clean install verify -Djetty.port=8888```

OR
  
  the same in Eclipse 
![Eclipse run configuration](http://www.codingpedia.org/wp-content/uploads/2014/01/run-integration-tests-eclipse.png "Run configuration in Eclipse")
### SoapUI (recommended)
- [download and install SoapUI](http://sourceforge.net/projects/soapui/files/)
- import the REST project in SoapUI - [demo-rest-jersey-spring / src / main / resources / soapui / Test-Demo-REST-Jersey-with-Spring-soapui-project.xml](https://github.com/Codingpedia/demo-rest-jersey-spring/blob/master/src/main/resources/soapui/Test-Demo-REST-Jersey-with-Spring-soapui-project.xml)
- check out our [How to test REST API with SoapUI](http://youtu.be/XV7WW0bDy9c) video tutorial on YouTube

## Go to blog post
[Tutorial – REST API design and implementation in Java with Jersey and Spring](http://www.codingpedia.org/ama/tutorial-rest-api-design-and-implementation-in-java-with-jersey-and-spring/) - complete explanation of this implementation. 
## Changelog

## License

[MIT](https://github.com/Codingpedia/demo-rest-jersey-spring/blob/master/LICENSE) &copy; [Codingpedia.org](http://www.codingpedia.org)

