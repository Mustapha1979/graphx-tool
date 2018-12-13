 #!/usr/bin/env bash

gradle clean build scoreJar

pathToJar="build/libs/graphx-tool-fat-1.0-SNAPSHOT.jar"

java -jar $pathToJar $@
