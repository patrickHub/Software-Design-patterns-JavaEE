# Build
mvn clean package && docker build -t com.patrickhub/facade-design-pattern .

# RUN

docker rm -f facade-design-pattern || true && docker run -d -p 8080:8080 -p 4848:4848 --name facade-design-pattern com.patrickhub/facade-design-pattern 