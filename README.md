# JAVAX-SPR - Haladó Spring keretrendszer eszközök

```shell
docker run --name employees-postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres
```

```plain
create employee: 201:
	@ResponseStatus(HttpStatus.CREATED)

deleteEmployee: 204
	@ResponseStatus(HttpStatus.NO_CONTENT)

<dependency>
  <groupId>org.zalando</groupId>
  <artifactId>problem-spring-web-starter</artifactId>
  <version>0.26.2</version>
</dependency>
	
NotFoundException extends AbstractThrowableProblem
	IllegalArgumentException helyett a service-ben
	
HTTP/1.1 404 
Content-Type: application/problem+json

{
  "type": "not-found",
  "title": "Not found",
  "status": 404,
  "detail": "Employee not found with id: 300"
}
```

## Swagger

```
http://localhost:8080/swagger-ui.html
```