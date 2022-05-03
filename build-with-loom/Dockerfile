############################################################################
# Loom JDK 19 Early-Access Build
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-loom/Dockerfile .
# run with: docker run --env-file .env -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM debian:11-slim
LABEL maintainer="tait1337"

# Install Loom EA Build https://jdk.java.net/loom/
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN curl https://download.java.net/java/early_access/loom/6/openjdk-19-loom+6-625_linux-x64_bin.tar.gz | tar xz -C /
ENV JAVA_HOME /jdk-19
ENV PATH $JAVA_HOME/bin:$PATH

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
COPY ./src/main/resources/static/album ./static/album
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]
