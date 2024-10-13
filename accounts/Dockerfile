#start with base image for java runtime environment
FROM openjdk:17.0.1-jdk-slim-buster

#information about the maintainer of this image
MAINTAINER sanjeet-mybank.com

#copy the targeted jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the jar
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]