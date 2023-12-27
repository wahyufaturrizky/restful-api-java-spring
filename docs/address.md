# Address API Spec

## Create Address

- Endpoint : POST /api/contact/{idContact}/address

Request Header :

- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
  "street": "wahyu",
  "city": "wahyu",
  "province": "wahyu",
  "postalCode": "wahyu",
  "country": "wahyu"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "wahyu",
    "street": "wahyu",
    "city": "wahyu",
    "province": "wahyu",
    "postalCode": "wahyu",
    "country": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "error contact is not found"
}
```

## Update Address

- Endpoint : PUT /api/contact/{idContact}/address/{idAddress}

Request Header :

- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
  "street": "wahyu",
  "city": "wahyu",
  "province": "wahyu",
  "postalCode": "wahyu",
  "country": "wahyu"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "wahyu",
    "street": "wahyu",
    "city": "wahyu",
    "province": "wahyu",
    "postalCode": "wahyu",
    "country": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "error contact is not found"
}
```

## Get Address

- Endpoint : GET /api/contact/{idContact}/address/{idAddress}

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": {
    "id": "wahyu",
    "street": "wahyu",
    "city": "wahyu",
    "province": "wahyu",
    "postalCode": "wahyu",
    "country": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "error contact is not found"
}
```

## Remove Address

- Endpoint : DELETE /api/contact/{idContact}/address/{idAddress}

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
  "errors": "error contact is not found"
}
```

## List Address

- Endpoint : GET /api/contact/{idContact}/address

Request Header :

- X-API-TOKEN : Token (mandatory)

Response Body (Success) :

```json
{
  "data": {
    "id": "wahyu",
    "street": "wahyu",
    "city": "wahyu",
    "province": "wahyu",
    "postalCode": "wahyu",
    "country": "wahyu"
  }
}
```

Response Body (Failure) :

```json
{
  "errors": "error contact is not found"
}
```
