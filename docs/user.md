# User API Spec

## Register User

- Endpoint : POST /api/auth/register

Request Body :

```json
{
  "username": "wahyu",
  "password": "12345",
  "name": "wahyu"
}
```

Response Body (Success) :

```json
{
  "data": "OK"
}
```

Response Body (Failure) :

```json
{
  "errors": "User not found"
}
```

## Login User

- Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username": "wahyu",
  "password": "12345"
}
```

Response Body (Success) :

```json
{
  "data": {
    "token": "TOKEN",
    "expireAt": 1231232131 // milliseconds
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "User not found"
}
```

## Get User

- Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": {
    "username": "wahyu",
    "name": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "Unauthorized"
}
```

## Update User

- Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
  "name": "wahyu", // put if only want to update name
  "password": "123312" // put if only want to update password
}
```

Response Body (Success) :

```json
{
  "data": {
    "username": "wahyu",
    "name": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "Unauthorized"
}
```

## Logout User

- Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": {}
}
```

Response Body (Failure) :

```json
{
  "errors": ""
}
```
