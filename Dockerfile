FROM douglasffilho/compliance-alpine
MAINTAINER douglasf.filho@gmail.com
COPY build/libs/HelpMeToFood-user-services-1.1.2.jar /tmp/HelpMeToFood-user-services.jar
EXPOSE 8080/tcp