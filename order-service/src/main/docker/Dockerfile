FROM bellsoft/liberica-openjdk-alpine-musl:17.0.6

ENV JAVA_OPTS ""
ENV RAM_PCT "75.0"

EXPOSE 8080 8081

COPY maven/order-service.jar /

ENTRYPOINT exec java \
    -XX:MaxRAMPercentage=$RAM_PCT \
    $JAVA_OPTS \
    -jar /order-service.jar

HEALTHCHECK --interval=15s --timeout=30s \
  CMD wget -q --spider http://localhost:8080/actuator/health; exit $?