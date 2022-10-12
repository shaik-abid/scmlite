FROM openjdk:8-jdk-alpine as build
RUN apk add maven
COPY . .

RUN mvn clean install -q

FROM openjdk:8-jdk-alpine as prod
WORKDIR /app
COPY --from=build /target /app/target
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/demo-0.0.1-SNAPSHOT.jar"]