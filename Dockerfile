FROM tomcat:9.0.31-jdk11-openjdk

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./build/libs/hpgMethyl.war /usr/local/tomcat/webapps/ROOT.war