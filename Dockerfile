FROM openjdk:11

ADD target/partner-portal-authentication.jar partner-portal-authentication.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","partner-portal-authentication.jar"]