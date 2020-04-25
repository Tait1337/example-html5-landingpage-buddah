############################################################################
# Loom JDK 15 Early-Access Build (432 MB)
#
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT -f build-with-loom/Dockerfile .
# run with: docker run -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
FROM debian:10-slim

# Install Loom EA Build https://jdk.java.net/loom/
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN curl https://download.java.net/java/early_access/loom/5/openjdk-15-loom+5-125_linux-x64_bin.tar.gz | tar xz -C /
ENV JAVA_HOME /jdk-15
ENV PATH $JAVA_HOME/bin:$PATH

# App
WORKDIR /app
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]