FROM tomcat:9.0.31-jdk11-openjdk
ARG DB_USER
ARG DB_PASSWORD
ENV CATALINA_OPTS="-DDB_USER=${DB_USER} -DDB_PASSWORD=${DB_PASSWORD}"
RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./build/libs/hpgMethyl.war /usr/local/tomcat/webapps/ROOT.war