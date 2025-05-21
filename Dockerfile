FROM openjdk:17
EXPOSE 8080
ADD target/customer-storage-0.0.1-SNAPSHOT.jar storage.jar
ENTRYPOINT ["java","-jar","/storage.jar"]