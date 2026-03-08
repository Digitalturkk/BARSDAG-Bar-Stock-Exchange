# BARSDAG API Testing Guide

## Quick Start

After starting the application at `http://localhost:8081`, you can use the following curl commands to test the API.

## Testing with curl

### 1. Drinks

#### Get all beverages
```bash
curl -X GET http://localhost:8081/public/drinks/all
```

Expected response:
```json
[
  {
    "id": 1,
    "name": "Mojito",
    "description": "Mint and rum cocktail",
    "openPrice": 12.0,
    "priceRightNow": 12.0,
    "closedPrice": null
  }
]
```

#### Get beverage by ID
```bash
curl -X GET http://localhost:8081/public/drinks/id=1
```

#### Sell a beverage
```bash
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=5
```

Expected response:
```
Successfully sold 5 of Mojito.
Current price: 12.24
```

### 2. Sales

#### Get all sales
```bash
curl -X GET http://localhost:8081/public/sales/all
```

Expected response:
```json
[
  {
    "id": 1,
    "drinkId": 1,
    "quantity": 5,
    "saleTime": "2026-03-08T17:43:47.123456"
  }
]
```

#### Get total quantity sold
```bash
curl -X GET http://localhost:8081/public/sales/total-sold
```

Expected response:
```
5
```

#### Get quantity sold by beverage name
```bash
curl -X GET "http://localhost:8081/public/sales/sold-by-drink=Mojito"
```

Expected response:
```
5
```

### 3. Orders

#### Get all orders
```bash
curl -X GET http://localhost:8081/public/orders/all
```

#### Get order by ID
```bash
curl -X GET http://localhost:8081/public/orders/id=1
```

#### Create a new order
```bash
curl -X POST http://localhost:8081/public/orders/create \
  -H "Content-Type: application/json" \
  -d '{
    "orderNumber": "ORD001",
    "ordersDrinks": []
  }'
```

#### Delete an order
```bash
curl -X DELETE http://localhost:8081/public/orders/delete=1
```

## Using in Postman

### Import Collection

1. Open Postman
2. Click "Import"
3. Use the following collection:

```json
{
  "info": {
    "name": "BARSDAG API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Drinks",
      "item": [
        {
          "name": "Get All Drinks",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/drinks/all"
          }
        },
        {
          "name": "Get Drink by ID",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/drinks/id=1"
          }
        },
        {
          "name": "Sell Drink",
          "request": {
            "method": "POST",
            "url": "http://localhost:8081/public/drinks/sell-id=1-qnt=5"
          }
        }
      ]
    },
    {
      "name": "Sales",
      "item": [
        {
          "name": "Get All Sales",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/sales/all"
          }
        },
        {
          "name": "Get Total Sold",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/sales/total-sold"
          }
        },
        {
          "name": "Get Sold by Drink Name",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/sales/sold-by-drink=Mojito"
          }
        }
      ]
    },
    {
      "name": "Orders",
      "item": [
        {
          "name": "Get All Orders",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/orders/all"
          }
        },
        {
          "name": "Get Order by ID",
          "request": {
            "method": "GET",
            "url": "http://localhost:8081/public/orders/id=1"
          }
        },
        {
          "name": "Create Order",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\"orderNumber\": \"ORD001\", \"ordersDrinks\": []}"
            },
            "url": "http://localhost:8081/public/orders/create"
          }
        },
        {
          "name": "Delete Order",
          "request": {
            "method": "DELETE",
            "url": "http://localhost:8081/public/orders/delete=1"
          }
        }
      ]
    }
  ]
}
```

## Testing Scenarios

### Scenario 1: Basic Operations with Beverages

1. Get all beverages:
```bash
curl http://localhost:8081/public/drinks/all
```

2. Get information about a specific beverage:
```bash
curl http://localhost:8081/public/drinks/id=1
```

3. Sell a beverage and see the price change:
```bash
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=5
```

4. Check the new price:
```bash
curl http://localhost:8081/public/drinks/id=1
```

### Scenario 2: Tracking Sales

1. Perform multiple sales:
```bash
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=5
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=3
curl -X POST http://localhost:8081/public/drinks/sell-id=2-qnt=2
```

2. Get all sales:
```bash
curl http://localhost:8081/public/sales/all
```

3. Get statistics:
```bash
# Total quantity of beverages sold
curl http://localhost:8081/public/sales/total-sold

# Units of a specific beverage sold
curl "http://localhost:8081/public/sales/sold-by-drink=Mojito"
```

### Scenario 3: Order Management

1. Create an order:
```bash
curl -X POST http://localhost:8081/public/orders/create \
  -H "Content-Type: application/json" \
  -d '{"orderNumber": "ORD001", "ordersDrinks": []}'
```

2. Get all orders:
```bash
curl http://localhost:8081/public/orders/all
```

3. Get order by ID (replace 1 with the obtained ID):
```bash
curl http://localhost:8081/public/orders/id=1
```

4. Delete an order:
```bash
curl -X DELETE http://localhost:8081/public/orders/delete=1
```

## HTTP Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Success |
| 404 | Not Found - Resource not found |
| 400 | Bad Request - Invalid request |
| 500 | Internal Server Error - Server error |

### Example 404 Error:
```bash
curl http://localhost:8081/public/drinks/id=999
```

Response:
```json
{
  "message": "Drink with id 999 not found.",
  "cause": "java.util.NoSuchElementException",
  "httpStatusCode": "NOT_FOUND"
}
```

## Notes

- All dates and times are in ISO 8601 format
- Beverage and order IDs are auto-generated
- Prices are stored as Double
- All database operations are synchronous

## Testing Tips

1. **Use tools for convenience**: Postman, Insomnia, VS Code REST Client
2. **Save IDs**: After creating a resource, save its ID for subsequent operations
3. **Check logs**: View the application console for debugging
4. **Test edge cases**: Try selling 0 units, negative quantities, etc.

## Ready-to-Copy Examples

### In Windows PowerShell:

```powershell
# Get all beverages
(Invoke-WebRequest -Uri "http://localhost:8081/public/drinks/all" -Method Get).Content | ConvertFrom-Json | ConvertTo-Json

# Create an order
Invoke-WebRequest -Uri "http://localhost:8081/public/orders/create" -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"orderNumber":"ORD001","ordersDrinks":[]}'
```

### In CMD:

```batch
REM Get all beverages
curl http://localhost:8081/public/drinks/all

REM Sell a beverage
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=5
```

