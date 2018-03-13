# How to build project
This document helps people to compile and build the project in your maven and IDE.

## Build in maven
1. Prepare JDK8 and maven3
1. `git clone https://github.com/apache/incubator-skywalking.git`
1. `git submodule init`
1. `git submodule update`
1. Run `mvn clean package`
1. All packages are in `/dist`, which includes and two collector files(.tar.gz for Linux and .zip for Windows)

## Setup your IntelliJ IDEA
1. Import the project as a maven project
1. Run `mvn compile -Dmaven.test.skip=true` to compile project and generate source codes. Because we use gRPC and protobuf.
1. Set **Generated Source Codes** folders.
    * `grpc-java` and `java` folders in **apm-protocol/apm-network/target/generated-sources/protobuf**
    * `grpc-java` and `java` folders in **apm-collector/apm-collector-remote/apm-remote-grpc-provider/target/protobuf**

## Building Resin-3, Resin-4, and OJDBC sdk plugins
Due to license incompatibilities/restrictions these plugins under `apm-sniffer/apm-sdk-plugin/` are not built by default.
Download them manually into the `ci-dependencies/` directory and the plugins will be included in the maven reactor and built.
The names of the artifacts downloaded and placed in the `ci-dependencies/` directory must be exact:
* resin-3.0.9.jar
* resin-4.0.41.jar
* ojdbc14-10.2.0.4.0.jar
