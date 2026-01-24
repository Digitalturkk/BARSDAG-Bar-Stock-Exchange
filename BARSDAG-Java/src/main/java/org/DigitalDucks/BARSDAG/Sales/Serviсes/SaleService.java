package org.DigitalDucks.BARSDAG.Sales.Serviсes;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Sales.Sale;
import org.DigitalDucks.BARSDAG.Sales.SaleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    void saveSale(Sale sale);
    void deleteSale(Long id);
    Sale getSaleById(Long id);
    List<Sale> getAllSales();
    List<Sale> getSalesByDrinkId(Long drinkId);
    void createSale(Drink drink, Integer quantity);

    SaleDTO convertToDTO(Sale sale);

    Integer countTotalSoldInRange(LocalDateTime start, LocalDateTime end);
}
