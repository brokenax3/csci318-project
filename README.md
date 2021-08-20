# csci318-project
Repo for project for CSCI318 Online Ordering Application

# Dependencies
- Spring Boot 2.5.3
- Java 8
- Spring Web
- Spring Data JPA
- H2

# Examples of Input and Output

## Rest Requests

### Request 1: Creating a new customer entry.
- Input:
```bash
curl -X POST -H "Content-Type:application/json" -d \
    '{
        "companyName":"Microsoft",
        "address" : "Redmond",
        "country" : "USA"
    }' http://localhost:8080/customer
```
- Output:
```bash
"id":1,"companyName":"Microsoft","address":"Redmond","country":"USA","contact":null}
```


### Request 2: Creating a new contact entry.
- Input:
```bash
curl -X POST -H "Content-Type:application/json" -d \
    '{
        "name" : "Bill Gates",
        "phone" : "04555555555",
        "email" : "thisisgates@gmail.com",
        "position" : "CEO"
    }' http://localhost:8080/contact
```
- Output:
```bash
{"id":2,"name":"Bill Gates","phone":"04555555555","email":"thisisgates@gmail.com",
"position":"CEO"}
```

### Request 3: Linking a Customer and a Contact.
- Input:
```bash
curl -i -X PUT http://localhost:8080/customer/1/contact/2
```
- Output:
```bash
{"id":1,"companyName":"Microsoft","address":"Redmond","country":"USA",
"contact":{"id":2,"name":"Bill Gates","phone":"04555555555","email":"thisisgates@gmail.com",
"position":"CEO"}}
```

### Request 4: Updating a specific contact by ID.
- Input:
```bash
curl -i -X PUT -H "Content-Type:application/json" -d \
    '{
        "name" : "Tim Cook",
        "phone" : "04555555557",
        "email" : "thisistim@hotmail.com",
        "position" : "CEO",
        "customer" : "Apple"
    }' http://localhost:8080/contact/2
```
- Output:
```bash
{"id":2,"name":"Tim Cook","phone":"04555555557","email":"thisistim@hotmail.com",
"position":"CEO"}
```

### Request 5: Get all customers in the database.
- Input:
```bash
curl -X POST -H "Content-Type:application/json" -d \
    '{
        "companyName":"Apple",
        "address" : "California",
        "country" : "USA"
    }' http://localhost:8080/customer

curl -X GET http://localhost:8080/customer
```
- Output:
```bash
{"id":3,"companyName":"Apple","address":"California","country":"USA","contact":null}
[{"id":1,"companyName":"Microsoft","address":"Redmond","country":"USA",
"contact":{"id":2,"name":"Tim Cook","phone":"04555555557","email":"thisistim@hotmail.com",
"position":"CEO"}},{"id":3,"companyName":"Apple","address":"California",
"country":"USA","contact":null}]
```

### Request 6: Get a specific customer in the database.
- Input:
```bash
curl -X GET http://localhost:8080/customer/3
```
- Output:
```bash
{"id":3,"companyName":"Apple","address":"California","country":"USA","contact":null}
```

### Request 7: Search for a customer by company name.
- Input:
```bash
curl -X GET http://localhost:8080/customer/company?name=Apple
```
- Output:
```bash
[{"id":3,"companyName":"Apple","address":"California","country":"USA","contact":null}]
```

### Request 8: Get all contacts in the database.
- Input:
```bash
curl -X POST -H "Content-Type:application/json" -d \
    '{
        "name" : "Bill Gates",
        "phone" : "04555555555",
        "email" : "thisisgates@gmail.com",
        "position" : "CEO"
    }' http://localhost:8080/contact

curl -X GET http://localhost:8080/contact
```
- Output:
```bash
{"id":4,"name":"Bill Gates","phone":"04555555555","email":"thisisgates@gmail.com",
"position":"CEO"}[{"id":2,"name":"Tim Cook","phone":"04555555557",
"email":"thisistim@hotmail.com","position":"CEO"},{"id":4,"name":"Bill Gates",
"phone":"04555555555","email":"thisisgates@gmail.com","position":"CEO"}]
```

### Request 9: Get a specific contact in the database.
- Input:
```bash
curl -X GET http://localhost:8080/contact/4
```
- Output:
```bash
{"id":4,"name":"Bill Gates","phone":"04555555555","email":"thisisgates@gmail.com",
"position":"CEO"}
```
