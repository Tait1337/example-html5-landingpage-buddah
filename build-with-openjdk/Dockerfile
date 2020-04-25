############################################################################
# Adopt Open JDK 14 Build (203 MB)
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-openjdk/Dockerfile .
# run with: docker run -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM adoptopenjdk/openjdk14:alpine-jre

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]