FROM java:8
MAINTAINER Timothy Breitenfeldt
VOLUME /tmp
ADD target/librarianService-0.0.1-SNAPSHOT.jar librarian.jar
expose 8081
ENTRYPOINT ["java", "-jar", "librarian.jar"]
