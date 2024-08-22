FROM maven:3.8.5-openjdk-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true -Dgib.enabled=false
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]`