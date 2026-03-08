package org.DigitalDucks.BARSDAG.Sales.Serviсes;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Sales.Sale;
import org.DigitalDucks.BARSDAG.Sales.SaleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    // Create operations
    void createSale(Drink drink, Integer quantity);
    void saveSale(Sale sale);
    // Read operations
    Sale getSaleById(Long id);
    List<Sale> getAllSales();
    List<Sale> getSalesByDrinkId(Long drinkId);
    // Update operations
        // Not implemented yet
    // Delete operations
    void deleteSale(Long id);

    // DTO conversion
    SaleDTO convertToDTO(Sale sale);

    // Additional methods
    Integer countTotalSoldInRange(LocalDateTime start, LocalDateTime end);
    Integer countTotalSold();
    Integer countSoldByDrinkName(String drinkName);
}
