############################################################################
# Graal Native Image Early-Access Build (40,3 MB)
#
# Make sure you configured Docker to use at least 6gb ram!
# build from project root dir with: docker build -t example-html5-landingpage-buddah:1.0.0-SNAPSHOT .
# to clean up builder images you can run: docker image prune -f --filter label=stage=builder
# run with: docker run --env-file .env -p 8080:8080 -d example-html5-landingpage-buddah:1.0.0-SNAPSHOT
############################################################################
#####
# The builder image to build the native app
#####
FROM findepi/graalvm:native as builder
LABEL stage=builder

WORKDIR /builder
COPY ./build/libs/example-html5-landingpage-buddah-1.0.0-SNAPSHOT-all.jar ./app.jar
RUN native-image \
    --no-fallback \
    --static \
    -H:IncludeResources="(.*.properties)|(static/.*)|(META-INF/.*)" \
    -jar app.jar

#####
# The actual image to run
#####
FROM alpine:3.9
LABEL maintainer="tait1337"
RUN apk --no-cache add ca-certificates

# App
WORKDIR /app
COPY ./src/main/resources/static/album ./static/album
COPY --from=builder /builder/app .
EXPOSE $PORT
ENTRYPOINT ["./app"]