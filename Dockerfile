FROM eclipse-temurin:17 AS builder
#OpenJDK base image for the first stage
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
#Copies the applciation jar file from the local machine to the container
COPY ${JAR_FILE} catalog-service.jar
#Extracts the layers from the archive applting the layered-JAR mode
RUN java -Djarmode=layertools -jar catalog-service.jar extract

#open JDK base image for the second stage
FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR workspace
#Copies each JAR layer from the first stage to the second stage
#inside the "workspace foler"
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
#Uses the Spring Boot Launcher to start the application from the layers
#rather than an uber-JAR
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
