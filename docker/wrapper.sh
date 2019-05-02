#!/bin/bash -e

# Set memory limit
MEMORY_LIMIT=${MEMORY_LIMIT:-''}

function launchMicroservice {
  javaParams=''

  # listen on all the interfaces
  javaParams+=" -Dserver.address=0.0.0.0"

  # set memory limit
  if [ ! -z "$MEMORY_LIMIT" ];then
    javaParams+=" -Xmx${MEMORY_LIMIT}"
  fi

  # set encoding by default to UTF-8
  javaParams+=" -Dfile.encoding=UTF-8"

  echo "COMMAND: java ${javaParams} -jar $JAR_FILE"
  java ${javaParams} -jar $JAR_FILE
}

launchMicroservice
