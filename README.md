# Note To Do List

## Prerequisites

- Java 21
- JDK 21
- Spring Boot 3.5.3
- Apache Maven 3.9.9
- No Database Requirement

## How to Run

```shell
git clone https://github.com/dominikuswilly/project250630.git
```

```shell
cd project250630
```

```shell
mvn package
```

```shell
cd target
```

```shell
java -jar project250630-0.0.1-SNAPSHOT.jar
```

## CURL

`[POST] /v1/public/notes`

```shell
curl --location 'http://localhost:8080/v1/public/notes' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=68066365EDB9186488FC16E5EFCC4B70' \
--data '{
    "title":"a",
    "content":"a"
}'
```

`[GET] /v1/public/notes`

```shell
curl --location 'http://localhost:8080/v1/public/notes' \
--header 'Cookie: JSESSIONID=68066365EDB9186488FC16E5EFCC4B70'
```

`[GET] /v1/public/notes/1`

```shell
curl --location 'http://localhost:8080/v1/public/notes/1' \
--header 'Cookie: JSESSIONID=68066365EDB9186488FC16E5EFCC4B70'
```

`[GET] /v1/public/notes?search=a`

```shell
curl --location 'http://localhost:8080/v1/public/notes?search=a' \
--header 'Cookie: JSESSIONID=68066365EDB9186488FC16E5EFCC4B70'
```

`[DELETE] /v1/public/notes`

```shell
curl --location --request DELETE 'http://localhost:8080/v1/public/notes/1' \
--header 'Cookie: JSESSIONID=68066365EDB9186488FC16E5EFCC4B70'
```

## Swagger

https://imgur.com/spIRlYB

## Donation

https://saweria.co/dominikuswilly