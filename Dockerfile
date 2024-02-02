FROM tomcat

COPY /target/root.war /usr/local/tomcat/webapps/
COPY src/main/resources/hibernate.cfg.xml /path/in/container/hibernate.cfg.xml
