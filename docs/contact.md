# Contact API Spec

## Create Contact

- Endpoint : POST /api/contact

Request Header :

- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
  "firstName": "wahyu",
  "lastName": "wahyu",
  "email": "asd@asd.com",
  "phone": "12312"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "asdasd",
    "firstName": "wahyu",
    "lastName": "wahyu",
    "email": "asd@asd.com",
    "phone": "12312"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "email format invalid"
}
```

## Update Contact

- Endpoint : PUT /api/contact/{idContact}

Request Header :

- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
  "firstName": "wahyu",
  "lastName": "wahyu",
  "email": "asd@asd.com",
  "phone": "12312"
}
```

Response Body (Success) :

```json
{
  "data": {
    "firstName": "wahyu",
    "lastName": "wahyu",
    "email": "asd@asd.com",
    "phone": "12312"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "email format invalid"
}
```

## Get Contact

- Endpoint : GET /api/contact/{idContact}

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": {
    "id": "asdasd",
    "firstName": "wahyu",
    "lastName": "wahyu",
    "email": "asd@asd.com",
    "phone": "12312"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "email format invalid"
}
```

## Remove Contact

- Endpoint : DELETE /api/contact/{idContact}

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": "OK"
}
```

Response Body (Failure) :

```json
{
  "errors": "email format invalid"
}
```

## Search Contact

- Endpoint : GET /api/contacts

Query Param :

- name : string, contact first name or last name, using like query, (optional)

- phone : string, contact phone, using like query, (optional)

- email : string, contact email, using like query, (optional)

- page : Integer, start from 0, default o

- size : Integer, default 10

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Failure) :

```json
{
  "data": [
    {
      "id": "asdasd",
      "firstName": "wahyu",
      "lastName": "wahyu",
      "email": "asd@asd.com",
      "phone": "12312"
    }
  ],
  "paging": {
    "totalPage": 10,
    "currentPage": 0,
    "size": 0
  }
}
```
