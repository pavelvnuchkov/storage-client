FROM openjdk:17
EXPOSE 8085
ADD target/customer-storage-0.0.1-SNAPSHOT.jar storage.jar
ENTRYPOINT ["java","-jar","/storage.jar"]