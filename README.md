# ClusteredDataWarehouse

## Project Overview

The `ClusteredDataWarehouse` project is a part of a data warehouse solution developed for Bloomberg to analyze FX (Foreign Exchange) deals. The primary function of this project is to accept deal details and persist them into a database.

## Features

- Accepts deal details through a REST API.
- Validates the structure of the deal data.
- Ensures unique deal entries to avoid duplication.
- Persists validated deals into a MySQL database.
- Proper error handling and logging.
- Includes unit tests with high coverage.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Docker
- Maven


## How to Run

### Using Docker

1. **Build and run the Docker containers**: ( http://localhost:8080 )

   ```sh
   docker-compose up --build
   ```


### Using Makefile

1. **Build**:

   ```sh
   make build
   ```
2. **Run**:

   ```sh
   make run
   ```


### Using Makefile:

1. **Run Tests :**:

   ```sh
   mvn clean test
   ```
2. **Generate reports :**:

   ```sh
   mvn clean test jacoco:report
   ```


### Curl Examples :

```curl --location 'http://localhost:8080/deals' \
--header 'Content-Type: application/json' \
--data '[
    {
        "dealId": "99376",
        "fromCurrencyIsoCode": "GBP",
        "toCurrencyIsoCode": "USD",
        "dealTimestamp": "2023-01-02T12:00:00",
        "dealAmount": 2000.00
    }
]'```


```curl --location 'http://localhost:8080/deals' \
--header 'Content-Type: application/json' \
--data '[
    {
        "dealId": "99376",
        "toCurrencyIsoCode": "USD",
        "dealTimestamp": "2023-01-02T12:00:00",
        "dealAmount": 2000.00
    }
]'```


```curl --location 'http://localhost:8080/deals' \
--header 'Content-Type: application/json' \
--data '[
    {
        "dealId": "99376",
        "fromCurrencyIsoCode": "",
        "toCurrencyIsoCode": "USD",
        "dealTimestamp": "2023-01-02T12:00:00",
        "dealAmount": 2000.00
    }
]'```


