FROM openjdk:11
VOLUME /tmp
ADD ./target/forum.jar forum.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/forum.jar"]