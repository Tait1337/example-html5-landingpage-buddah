############################################################################
# Adopt Open JDK 15 Build (244 MB)
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-openjdk/Dockerfile .
# run with: docker run --env-file .env -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM adoptopenjdk/openjdk15:x86_64-alpine-jre-15_36
LABEL maintainer="tait1337"

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
COPY ./src/main/resources/static/album ./static/album
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]