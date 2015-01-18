# SaksInterviewProject

To create this project I followed the directions at the following location. 

https://gist.github.com/sirmes/ce46e4ed5fe122d4fce1

One of the Junit test classes (saks.interviewproject.service.UserServiceLiveTest) looks for the file src\test\resources\test.properties for your server information to do end to end tests. 

If it cannot find the server referenced in this property file (server not reachable), it will skip the end to end test, and conitnue on with any remaining Unit tests. 
Because of this last point, when you initially do the build, and haven't deployed the war yet, make sure the server referenced in test.properties is not running as it will 
try to do live server testing even though the application hasn't been deployed yet, and the tests will fail. 

During development I used the Eclipse Tomcat server plugin and did hot deploys of the m2eclipse generated project with "Dynamic Web Application" facet into Tomcat without having to run
a build via maven. I also used  the Eclipse IDE's JUnit integration to engage the tests instead of relying on maven goals. 

I had to use Mockito to be able to unit test just the Jersey (JAX-RS) classes without engaging the servlet container. 

Both testing and war packaging can be invoked from the single maven goal "install". 

If you either issue the following command:

	mvn install

Or run the provided script:

	testandpackage.sh 

It will invoke the Java compilier, then go through the Junit classes and finally build the .war file at the target/sample.war location. 

If you deploy this file in your Tomcat installation's webapps folder it should wire up the two endpoints you provided:

http://localhost:[port]/sample/user/add?[pass a username]

http://localhost:[port]/sample/user/show

I used jdk1.7.0 to build this, you should have it installed and set as your active JDK, you should also have maven installed and correctly configured. 

I want to thank everyone for the opportunity you have given me to represent my work, and I am hopeful we can continue our discussion on my potential employment at your establishment. 

Ted