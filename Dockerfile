FROM openjdk:11.0.2-oracle

COPY build/libs/recipe-api-0.0.1-SNAPSHOT.war /app/recipe-api.war

CMD ["java", "-jar", "/app/recipe-api.war"]

