```
docker build -t artemismq .

docker run -d -p 8161:8161 -p 61616:61616   --name employees-mq artemismq
```