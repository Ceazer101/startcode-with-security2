FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . /app
RUN apt-get update && apt-get install dos2unix
RUN dos2unix mvnw
RUN ls
RUN test -f ./mvnw && echo "It exists."
RUN ./mvnw dependency:resolve

CMD ["./mvnw", "spring-boot:run"]
