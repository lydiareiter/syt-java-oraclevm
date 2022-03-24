FROM openjdk:17.0.2

WORKDIR /app
COPY ./src .

RUN ls -lah
RUN javac ./Main.java && echo "java compiled"

CMD cd ./src
CMD ls -lahR
CMD pwd
CMD java Main

