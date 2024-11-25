# EnocaEcommerceProject
Temel e-commerce işlemlerini yapar,kullanılan teknolojiler: Java,Springboot,Hibernate,PostgreSQL,Swagger UI. Kullanılan tool'lar: intellij IDEA,pgadmin,Postman

### Endpoints

#### Customer

```
POST /customers
Host: localhost:8080

{
    "name":"customer",
    "surname":"customer",
    "address":"customer",
    "phoneNumber":"customer"
}
```

#### Product

```
GET /products/{id}
Host: localhost:8080

{
    "id": -,
    "createdTime": "-",
    "updatedTime": "-",
    "code": "-",
    "name": "-",
    "price": -,
    "stock": -
}

POST /products
Host: localhost:8080

{
    "name":"-",
    "price":-,
    "stock":-,
    "code":"-"
}

POST /products/{id}
Host: localhost:8080

{
    "name":"-",
    "price":-,
    "stock":-,
    "code":"-"
}

DELETE /products/{id}
Host: localhost:8080

```

### Cart

```
GET /carts/{id}
Host: localhost:8080

POST /carts/add
Host: localhost:8080

{
    "cartId":-,
    "productId":-,
    "amount":-
}

POST /carts/remove
Host: localhost:8080

{
    "cartId":-,
    "productId":-,
    "amount":-
}

POST /carts/update
Host: localhost:8080

{
    "cartId":-,
    "productId":-,
    "amount":-
}

PUT /carts/empty/{id}
Host: localhost:8080

```

### Order

```
POST /orders
Host: localhost:8080

{
  "customerId": -,
  "products": [
    {
      "productId": -,
      "amount": -
    },
  ],
  "code": "-"
}

GET /orders/customer/{id}
Host: localhost:8080

GET /orders/orders/{code}
Host: localhost:8080
```

### Purchase-History

```
GET /purchase_history/{code}
Host: localhost:8080

```
