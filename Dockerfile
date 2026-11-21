FROM eclipse-temurin:17

WORKDIR /aouth

COPY ./target/aouth-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/aouth/app.jar"]