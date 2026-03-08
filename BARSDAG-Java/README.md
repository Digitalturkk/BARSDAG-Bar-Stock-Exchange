# BARSDAG - Bar's Stock Exchange

A Spring Boot-based bar beverage exchange application where drinks are traded like stocks on a stock exchange.

## Project Description

BARSDAG is a REST API application for managing beverage sales with a dynamic pricing system based on supply and demand. The system tracks beverage sales and automatically adjusts prices based on purchase activity.

### Project Origin

This project originated as a task during the probationary period at Rakuten. It was subsequently continued and further developed by the maintainer to implement additional features and improvements.

## Key Features

### 1. Drink Management (Drinks)
- Get a list of all beverages with current prices
- View information about a specific beverage
- Track opening, current, and closing prices for each beverage
- Sell beverages with automatic price adjustment

### 2. Sales Tracking (Sales)
- Record each beverage sale
- Count total number of beverages sold
- Count number of beverages sold by name
- View all sales with timestamps

### 3. Order Management (Orders)
- Create new orders with beverages
- Get order information by ID
- View all orders
- Delete orders

### 4. Order Cart (OrderCart)
- Structure for managing temporary orders

## Architecture

```
BARSDAG/
├── Drinks/                    # Drink management
│   ├── Drink.java
│   ├── DrinkDTO.java
│   ├── DrinkRepository.java
│   ├── DrinkController.java
│   └── Services/
│       ├── DrinkService.java
│       └── DrinkServiceImplement.java
├── Sales/                     # Sales management
│   ├── Sale.java
│   ├── SaleDTO.java
│   ├── SaleRepository.java
│   ├── SaleController.java
│   └── Services/
│       ├── SaleService.java
│       └── SaleServiceImplement.java
├── Orders/                    # Order management
│   ├── Order.java
│   ├── OrderDTO.java
│   ├── OrderRepository.java
│   ├── OrderController.java
│   └── Services/
│       ├── OrderService.java
│       └── OrderServiceImplement.java
├── OrderCarts/               # Order cart
│   ├── OrderCart.java
│   ├── OrderCartRepository.java
│   └── Services/
│       └── OrderCartService.java
├── Exceptions/               # Exception handling
│   ├── GlobalNotFoundException.java
│   ├── GlobalNotFound.java
│   └── GlobalNotFoundHandler.java
└── SecondaryDTOs/           # Additional DTOs
    └── CheckDTO.java
```

## API Endpoints

### Drinks
```
GET  /public/drinks/all                           # Get all beverages
GET  /public/drinks/id={drinkId}                  # Get beverage by ID
POST /public/drinks/sell-id={drinkId}-qnt={qty}   # Sell a beverage
```

### Sales
```
GET /public/sales/all                             # Get all sales
GET /public/sales/total-sold                      # Get total quantity sold
GET /public/sales/sold-by-drink={drinkName}       # Get sales by drink name
```

### Orders
```
GET  /public/orders/all                           # Get all orders
GET  /public/orders/id={orderId}                  # Get order by ID
POST /public/orders/create                        # Create a new order
DELETE /public/orders/delete={orderId}            # Delete an order
```

## Technology Stack

- **Java 21** - Programming language
- **Spring Boot 4.0.0** - Application framework
- **Spring Data JPA** - ORM for database operations
- **Spring Web** - REST controllers
- **PostgreSQL** - Database (for production)
- **H2** - Embedded database (for development)
- **Hibernate** - JPA implementation
- **Maven** - Dependency management

## Installation and Running

### Requirements
- JDK 21 or higher
- Maven 3.9+
- PostgreSQL 15+ (for production)

### Local Development with H2

1. Clone the repository:
```bash
git clone <repository-url>
cd BARSDAG-Java
```

2. Build the project:
```bash
mvnw clean package -DskipTests
```

3. Run the application:
```bash
mvnw spring-boot:run
```

The application will be available at: `http://localhost:8081`

### Production with PostgreSQL

1. Create a database:
```sql
CREATE DATABASE barsdag;
```

2. Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/barsdag
spring.datasource.username=postgres
spring.datasource.password=your_password
```

3. Run the application:
```bash
mvnw spring-boot:run
```

## Pricing Logic

### Automatic Price Change on Sale
When a beverage is sold:
1. The sold beverage price increases by 2% (`× 1.02`)
2. Prices of all other beverages decrease by 2% (`× 0.98`)

### Periodic Price Adjustment
Every 10 minutes (12:00 to 23:00), the system analyzes sales from the last 10 minutes:
- If sales < 3 and price > (opening × 0.7): price decreases by 1% (`× 0.99`)
- If sales > 7 and price < (opening × 1.3): price increases by 1% (`× 1.01`)

## Usage Examples

### Get all beverages
```bash
curl http://localhost:8081/public/drinks/all
```

### Sell a beverage
```bash
curl -X POST http://localhost:8081/public/drinks/sell-id=1-qnt=5
```

### Get sales statistics
```bash
curl http://localhost:8081/public/sales/total-sold
curl http://localhost:8081/public/sales/sold-by-drink=Pina%20Colada
```

### Create an order
```bash
curl -X POST http://localhost:8081/public/orders/create \
  -H "Content-Type: application/json" \
  -d '{
    "orderNumber": "ORD001",
    "ordersDrinks": [1, 2, 3]
  }'
```

## Data Structure

### Drink (Beverage)
```json
{
  "id": 1,
  "name": "Pina Colada",
  "description": "Tropical cocktail",
  "openPrice": 10.0,
  "priceRightNow": 10.5,
  "closedPrice": null
}
```

### Sale
```json
{
  "id": 1,
  "drinkId": 1,
  "quantity": 5,
  "saleTime": "2026-03-08T17:43:47"
}
```

### Order
```json
{
  "id": 1,
  "orderNumber": "ORD001",
  "ordersDrinks": [...]
}
```

## Error Handling

The application uses a global exception handler for all errors:

```json
{
  "message": "Drink with id 999 not found.",
  "cause": "java.util.NoSuchElementException",
  "httpStatusCode": "NOT_FOUND"
}
```

## Future Plans

- [ ] Authentication and authorization
- [ ] Price history for each beverage
- [ ] More complex pricing system
- [ ] Beverage popularity rating
- [ ] WebSocket for real-time price updates
- [ ] Payment system integration
- [ ] Mobile application

## Testing

To run tests:
```bash
mvnw test
```

## License

MIT License

## Contacts

- Development: DigitalDucks
- Email: support@digitalducks.com

## Changelog

### v0.0.1 (2026-03-08)
- Initial project release
- Implemented basic CRUD operations
- Beverage and sales management system
- Automatic price adjustment
- REST API for order management

