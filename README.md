# Getting Started

### Reference Documentation
The application has 3 ends point:
* Register user POST http://localhost:8080/nisum/user/register 
* Update user PUT http://localhost:8080/nisum/user/register
* Login POST http://localhost:8080/nisum/user/login
* SWAGGER http://localhost:8080/swagger-ui/index.html

### Guides
### Register user /nisum/user/register
Request :
```json
{
  "userName": "jrodriguez",
  "name": "Juan Rodriguez",
  "email": "jrodriguez@nisum.cl",
  "password": "Jj170192",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}

```
| FIELD    | IS REQUIRED | TYPE                             |
|----------|-------------|----------------------------------|
| userName | true        | String                           |
| name     | true        | String                           |
| email    | true        | String                           |
| password | true        | String                           |
| phones   | false       | Map Complex (dynamic attributes) |

Response :
```json
{
  "id": "0b1381fc-c405-4949-9f31-3f6ca83d05f5",
  "userName": "jrodriguez",
  "created": "2023-06-30T07:39:29.9585282",
  "modified": null,
  "lastLogin": "2023-06-30T07:39:29.9585282",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoianJvZHJpZ3VlekBuaXN1bS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODgxMjg3NjksImV4cCI6MTY4ODEyOTM2OX0.LUkStGPtewQRQJiI5VMrAva1mh9tbZaR5EyIX0mLdbvsceY_S53ONV46fXKWJc_bhpHlJkm-Zp43OQqNUA-Xig",
  "isActive": "ACTIVE"
}

```
* Sha256 key encryption
* UUID primary key 
* Minimum eight characters, at least one letter and one number:

### Update user /nisum/user/register
Request :
```json
{
  "email": "jrodriguez@nisum.cl",
  "name": "Juan Rodriguez",
  "password": "Jj170192",
  "phones": [
    {
      "number": "1234232323232",
      "citycode": "1",
      "contrycode": "57"
    }
  ],
  "status": "INACTIVE"
}

```
| FIELD    | IS REQUIRED | TYPE                   |
|----------|-------------|------------------------|
| email    | true        | String                 |
| name     | true        | String                 |
| password | true        | String                 |
| phones   | false       | Complex                |
| status   | true        | Enum (ACTIVE/INACTIVE) |

Response :
```json
{
  "id": "98aea0f1-f85d-4bb2-bd45-ce386c5d9225",
  "userName": "jrodriguez",
  "created": "2023-06-30T00:00:00",
  "modified": "2023-06-30T08:09:27.2963082",
  "lastLogin": "2023-06-30T00:00:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoianJvZHJpZ3VlekBuaXN1bS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODgxMzA1NTQsImV4cCI6MTY4ODEzMTE1NH0.hqj-DT_QRKptdleygW59GkDtsGGAHdSBw6D6cfFuQMzaFrxGDkRRypz6-aa5BIe3ipr7A7SXBfCzIpglrunFvA",
  "isActive": "INACTIVE"
}

```
### Login user /nisum/user/login
Request :
```json
{
  "email": "jrodriguez@nisum.cl",
  "password": "Jj170192"
}

```
| FIELD    | IS REQUIRED | TYPE                   |
|----------|-------------|------------------------|
| email    | true        | String                 |
| password | true        | String                 |

Response:
```json
{
  "id": "98aea0f1-f85d-4bb2-bd45-ce386c5d9225",
  "userName": "jrodriguez",
  "created": "2023-06-30T00:00:00",
  "modified": "2023-06-30T00:00:00",
  "lastLogin": "2023-06-30T08:11:39.9814058",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoianJvZHJpZ3VlekBuaXN1bS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODgxMzA2OTksImV4cCI6MTY4ODEzMTI5OX0.bcKCkwfa1ht49HQrzEeiHU7x_N0qiSjqsdtKwIOQkgNInBlniVt_9zmCgpckP95hgqCKiFOVixEkRg1PGOZliQ",
  "isActive": "INACTIVE"
}

```

### DFD REGISTER USER
![REGISTER DFD.png](REGISTER%20DFD.png)
### DFD UPDATE USER
![UPDATE DFD.png](UPDATE%20DFD.png)