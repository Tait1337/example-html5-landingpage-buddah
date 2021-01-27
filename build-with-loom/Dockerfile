############################################################################
# Loom JDK 17 Early-Access Build (455 MB)
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-loom/Dockerfile .
# run with: docker run --env-file .env -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM debian:10-slim
LABEL maintainer="tait1337"

# Install Loom EA Build https://jdk.java.net/loom/
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN curl https://download.java.net/java/early_access/loom/2/openjdk-17-loom+2-42_linux-x64_bin.tar.gz | tar xz -C /
ENV JAVA_HOME /jdk-17
ENV PATH $JAVA_HOME/bin:$PATH

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
COPY ./src/main/resources/static/album ./static/album
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]
