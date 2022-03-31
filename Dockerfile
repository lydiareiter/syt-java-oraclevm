FROM openjdk:11 as BUILDER

WORKDIR /app
COPY ./src .

RUN javac ./Main.java

FROM openjdk:11-jre

COPY --from=BUILDER /app /app

WORKDIR /app

EXPOSE 9999

CMD cd ./src
CMD java Main

