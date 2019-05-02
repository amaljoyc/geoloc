FROM anapsix/alpine-java:8

ARG VERSION=1.0.0
ARG APPLICATION_NAME=geoloc
ARG PORT=7200

ENV JAR_FILE="${APPLICATION_NAME}-${VERSION}.jar" \
    PORT=$PORT \
    MEMORY_LIMIT=256m

# Run as app user with uid 9999.
USER 9999

ADD --chown=9999:9999 . "/home/"

WORKDIR "/home"

EXPOSE $PORT

CMD ["bash", "-c", "./wrapper.sh"]
