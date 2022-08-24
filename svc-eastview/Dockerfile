ARG SVC_NAME=svc-eastview

FROM openjdk:18-jdk-alpine as builder

ARG SVC_NAME

WORKDIR /app/$SVC_NAME

COPY ./pom.xml /app
COPY ./$SVC_NAME/.mvn ./.mvn
COPY ./$SVC_NAME/mvnw .
COPY ./$SVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./$SVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:18-jdk-alpine

ARG SVC_NAME

WORKDIR /app

RUN mkdir ./logs


ARG TARGET_FOLDER=/app/$SVC_NAME/target

COPY --from=builder $TARGET_FOLDER/svc-eastview-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8004

ENV PORT $PORT_APP

EXPOSE $PORT

#CMD sleep 20 && java -jar msvc-usuarios-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "svc-eastview-0.0.1-SNAPSHOT.jar"]