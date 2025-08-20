# Tariff Calculation System

This is a Spring Boot app that calculates import tariffs based on the item, the country it's coming from, and the materials it's made of. You can also choose how the tariff is calculated using different policy options like additive, maximum, or override.

##  Features

- Calculates tariffs at both item-level and component-level
- Supports different types of tariff calculation rules using the **Strategy Design Pattern** (like additive, maximum, override)
- You can choose which tariff policy to apply just by passing it in the API request
- Uses a Bill of Materials (BoM) to figure out what components an item is made of and how much each one costs
- The API response gives a clear breakdown: item tariff, component tariff, total tariff, and final cost
- Clean, modular code with separate layers for DTOs, services, strategy logic, and validation

## Tariff Policies (Strategy Options)

**additive** - Adds both the item-level and component-level tariffs together                                   
**maximum**  - Picks whichever is higher: the item tariff or the total component tariff                  
**override** - First adds the component tariff to the item cost, then applies the item tariff on top of that (like tax on tax)

## API Usage

### Endpoint


### Request Body (Additive Policy)

```json
{
  "itemId": "ITEM123",
  "country": "CHN",
  "itemCost": 100,
  "policy": "additive"
}

```
### Response
```json
{
  "itemTariff": 10.0,
  "componentTariff": 19.5,
  "totalTariff": 29.5,
  "totalCost": 129.5,
  "componentBreakdown": {
    "STEEL_A": 4.5,
    "ALUMINUM_B": 15.0
  }
}

```

### Request Body (Maximum Policy)

```json
{
  "itemId": "ITEM123",
  "country": "CHN",
  "itemCost": 100,
  "policy": "maximum"
}

```
### Response
```json
{
  "itemTariff": 10.0,
  "componentTariff": 19.5,
  "totalTariff": 19.5,
  "totalCost": 119.5,
  "componentBreakdown": {
    "STEEL_A": 4.5,
    "ALUMINUM_B": 15.0
  }
}

```

### Request Body (Override Policy)

```json
{
  "itemId": "ITEM123",
  "country": "CHN",
  "itemCost": 100,
  "policy": "override"
}

```
### Response
```json
{
  "itemTariff": 11.950000000000003,
  "componentTariff": 19.5,
  "totalTariff": 31.450000000000003,
  "totalCost": 131.45,
  "componentBreakdown": {
    "STEEL_A": 4.5,
    "ALUMINUM_B": 15.0
  }
}

```