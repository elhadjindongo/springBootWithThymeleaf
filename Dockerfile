#THE BASIC DOCKER FILE
#LABEL Maintainer El Hadji M. NDONGO
FROM openjdk:11
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]