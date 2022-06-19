#!/usr/bin/env sh

echo "deploy app"
echo
oc new-app  https://github.com/visually-impered-hackaton-2022/tandem-quarkus.git#develop  \
 \
-e POSTGRESQL_USER=niko \
-e POSTGRESQL_PASSWORD=niko \
-e POSTGRESQL_DATABASE=sampledb \
-e POSTGRESQL_SERVICE_NAME=postgresql \
    --image-stream=java:openjdk-11-el7


oc expose service/tandem-quarkus
