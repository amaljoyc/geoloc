#!/bin/bash

PROJECT_NAME=geoloc
DOCKER_IMAGE=$PROJECT_NAME:latest

DIR=$(pwd)
DOCKER_DIR=$DIR/docker
TARGET_DIR=$DIR/target

JAR_VERSION=1.0.0
JAR_FILE=$PROJECT_NAME-$JAR_VERSION.jar
COMPOSE_FILE=$DIR/compose/$PROJECT_NAME.yml

run(){
    $DIR/./mvnw clean install

    echo "removing old image '$DOCKER_IMAGE' inorder to build latest again"
    docker rmi $DOCKER_IMAGE

    echo "building new docker image for $PROJECT_NAME..."
    /bin/cp -f $TARGET_DIR/$JAR_FILE $DOCKER_DIR/$JAR_FILE
    touch $DOCKER_DIR/$PROJECT_NAME.log
    docker build -t $DOCKER_IMAGE $DOCKER_DIR

	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE up -d
	echo "Sleepng for 3 seconds ..."
    sleep 3
}

stop(){
	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE down
}

top(){
	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE top
}

tail(){
	NAME=$1
	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE logs -f $NAME
}

list(){
	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE config --services
}

status(){
	docker-compose -p $PROJECT_NAME -f $COMPOSE_FILE ps
}


OPTION=$1

case $OPTION in
run)
    echo "Running containers for ${PROJECT_NAME} in detached mode"
    run
    ;;
stop)
    echo "Stopping all containers for ${PROJECT_NAME}"
    stop
    ;;
display)
	echo "Containers in ${PROJECT_NAME}"
	top
	;;
tail)
	tail $2
	;;
list)
	list
	;;
status)
	status
	;;
*)
    echo "Did you forget something!! [ run | stop | list | tail | status | display ]"
    echo "-- Options --"
    echo "run : Runs the complete container in detached mode"
    echo "stop : Stops and remove the complete container"
    echo "list : List all the services in the container"
    echo "status : Status of all the services in the container"
    echo "tail <service_name> : Tail the logs of the service"
    echo "display : Shows the top output for all the services"
esac