
FROM gradle:8-jdk22-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test


FROM openjdk:22-jdk

RUN mkdir /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/BookingService.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/app/BookingService.jar"]
