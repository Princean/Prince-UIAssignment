# Prince-UIAssignment

## Technologies

| Name | version |
| ----------- | ----------- |
| Java | 17 |
| Spring Boot | 3.4.2 |
| Maven | 3.9.9 |

## Authentication

The service is using spring security with **basic auth** configuration (admin/admin) and uses default port 8080 with dedicated path configured for CRUD operations associated with each table.

## High Level design

- The service follows [ **controller <-> service <-> repository** ] flow with logging enabled at necessary places.
- The database tables are represented using entity models inside **model** package.
- All payloads are handled with the separate **request** and **responce** pojos with validations applied for each field.
- Events are configured in separate **event** package and uses async event for rewards calculation implemented as a separate util.
- All the configuration related to authentication, asynchronous calls and any others are maintained in **config** package.

## Database design

The service uses in-memory database to keep it simple and uses 3 tables ( **CUSTOMER, CUSTOMER_TRANSACTION, REWARD_POINTS** ). All tables has a **primary key** and **sequence** associated with it. Here the rewards are calculated and saved for every customer transactions.

## Usage

The service uses **HTTP** protocol with the endpoints exposed. A postman collection is included for convenience inside the **"resources/postman"** of this application.