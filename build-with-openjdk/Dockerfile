############################################################################
# Eclipse Temurin Open JRE 17 Build
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-openjdk/Dockerfile .
# run with: docker run --env-file .env -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="tait1337"

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
COPY ./src/main/resources/static/album ./static/album
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]