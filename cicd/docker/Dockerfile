##frontend
FROM nginx:latest
ADD ./dist/css /usr/share/nginx/html/css
ADD ./dist/js /usr/share/nginx/html/js
ADD ./dist/icon /usr/share/nginx/html/icon
ADD ./dist/img /usr/share/nginx/html/img
RUN rm -rf /usr/share/nginx/html/index.html
ADD ./dist/index.html /usr/share/nginx/html/index.html
RUN rm -rf /etc/nginx/conf.d/default.conf

##frontend-admin
FROM nginx:latest
ADD ./dist/css /usr/share/nginx/html/css
ADD ./dist/js /usr/share/nginx/html/js
ADD ./dist/assets /usr/share/nginx/html/assets
RUN rm -rf /usr/share/nginx/html/index.html
ADD ./dist/index.html /usr/share/nginx/html/index.html
RUN rm -rf /etc/nginx/conf.d/default.conf

##backend
FROM openjdk:11-jdk-slim-stretch
ADD target/backend-0.0.1-SNAPSHOT.jar /backend-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/backend-0.0.1-SNAPSHOT.jar"]

##backend-admin
FROM openjdk:11-jdk-slim-stretch
ADD target/backend-0.0.1-SNAPSHOT.jar /backend-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/backend-0.0.1-SNAPSHOT.jar"]