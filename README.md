# DEMO APP

RUN APPLICATION


You can pull the image here from docker hub : long2904/demo_doctor_anywhere

or download the project as .zip file, extract it and from the root folder, you run this command with cmd :

./mvnw spring-boot:run

to run the project



#### APIS

You can test end-points at http://localhost:8080/swagger-ui/index.html#/

#### Authenticate

Demo test account : admin / 123456

```http
  POST /api/v1/account_manage/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. |
| `password` | `string` | **Required**. |


Response demo :

{
  "expiresIn": 1699377689321,
  "roles": [
    {
      "name": "ROLE_ADMIN",
      "id": 1
    }
  ],
  "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5OTI5MTI4OSwiZXhwIjoxNjk5Mzc3Njg5fQ.Hdk-gRTMHgQue2K3rqxZyCw4aPdZrTo2gradAR9QPoQ"
}


#### Create a new account 

To execute APIs , you need to add Bearer JWT Token in the header of the request

Ex: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5OTI5MDM2MCwiZXhwIjoxNjk5Mzc2NzYwfQ.psdABEGWWg2NyZ6-mfS_cU9gcaHNq-gDhXdIZ8VXZ9Q

In the swagger : you just need to add jwtToken without "Bearer" word at the green Authorization button at the top 

Des: Only ROLE_ADMIN can register account, an account can have many roles


```http
  POST /api/v1/account_manage/add
```

Header : Bearer + JWT token

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. |
| `username` | `string` | **Required**. |
| `password` | `string` | **Required**. |
| `roles` | `arraylist` | **Required**. |


#### Post a new task : 

```http
  POST /api/v1/tasks
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `title`      | `string` | **Required**. |
| `description`      | `string` | **Not required**.  |


#### Delete the task

Demo test account : admin / 123456

```http
  DELETE /api/v1/tasks/{id}
```

Assumption: Only account with ROLE_ADMIN can delete the task, the account with role ROLE_DOCTOR and
ROLE_NURSE cannot delete the task

#### Delete the task

```http
  PUT /api/v1/tasks/{id}
```

Assumption : Only the account with ROLE_ADMIN and ROLE_DOCTOR can update the task








