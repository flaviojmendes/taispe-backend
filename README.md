## Taispe Backend

How to run?

```
FOR DEV:

$ sudo docker run --network host --name taispe-mongo -e MONGODB_USER="user" -e MONGODB_PASS="user" -p 27017:27017 -v /home/taispe/mongo:/data/db -d mongo

$ ./mvnw install

$ sudo docker build -t taispe-be -f Dockerfile_dev .

$ sudo docker run -d --network host -p 8181:8181  --name taispe-be taispe-be 

```