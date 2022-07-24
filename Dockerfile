FROM tomcat:9.0.31-jdk11-openjdk
ARG DB_USER
ARG DB_PASSWORD
ENV CATALINA_OPTS="-DDB_USER=${DB_USER} -DDB_PASSWORD=${DB_PASSWORD}"
RUN rm -fr /usr/local/tomcat/webapps/ROOT
RUN apt-get update
RUN apt-get install -yy build-essential \
						scons \
						zlib1g-dev \
						libcurl4-gnutls-dev \
						libxml2-dev \
						libncurses5-dev \
						libgsl0-dev \
						check
RUN curl -Lo hpg-methyl.tar.gz https://github.com/grev-uv/hpg-methyl/releases/download/v3.32/hpg-methyl.v3.32_x86_64.tar.gz
RUN mv hpg-methyl.tar.gz /opt
RUN tar xzvf /opt/hpg-methyl.tar.gz -C /opt
RUN rm /opt/hpg-methyl.tar.gz
COPY ./build/libs/hpgMethyl.war /usr/local/tomcat/webapps/ROOT.war