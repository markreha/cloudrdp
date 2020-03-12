# GCU Cloud Platform - Research Project 2018-2020

Research Question: Can we design and build a fully functioning Private Cloud Platform using a cluster of Raspberry PI’s using current Cloud Technologies?

This repository contains the code to support the Cloud Portal Application.

The following steps can be used to import and build the Portal Application using EclipseEE, Java 8, and deployed onto Tomcat 8.5 server.
1) Start EclipseEE
2) Select the File Import->Projects from Git (with smart import) menu options
3) Click Next
4) Select Clone URI
5) In the URI enter https://github.com/markreha/cloudrdp 
6) Click Next 
7) Only check the master option
8) Browse to the path where you want the local GIT repository to reside
9) Click Next
10) Only check the cloudrdp/portal-app project
11) Click Finish

A new Dynamic Web Application Project named portal-app should now be shown in your EclipseEE workspace.
Make sure you add a Tomcat 8.5 Server to EclipseEE.

=================================

Resolve the following dependencies in the project:
1) Right click on the project and select the Build Path->Configure Build Path menu options
2) Click the Libraries tab
3) Click the Add Library button
4) Select Server Runtime from the list
5) Select Apache Tomcat v8.5
6) Click Finish
7) Click Apply and Close
8) Right click on the project and select the Maven->Update Project menu options
9) You will need to build the java-api project with standard Eclipse using Java 8 and export as a JAR file
10) Add the java-api.JAR file to your project by:
	a. Right click on the project
	b. Select the Build Path->Configure Build Path menu options
	c. Select the Libraries Tab
	d. Click the Add External JARs button and select the JAR file you created in step 9
	e. Click Apply and Close 
	
Your project should now be displayed in your workspace with no compiler errors and can be added to your Tomcat 8.5 server.

