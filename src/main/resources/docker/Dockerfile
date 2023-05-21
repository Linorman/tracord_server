FROM openjdk:8
MAINTAINER linorman
ADD backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL maintainer="linorman" \
    name="tracord_server" \
    version="1.0"