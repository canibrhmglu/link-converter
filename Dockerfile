FROM openjdk:8
VOLUME /tmp
ADD target/link-converter-0.0.1-SNAPSHOT.jar link-converter.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/link-converter.jar"]