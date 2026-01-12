package org.DigitalDucks.BARSDAG.Sales.Servies;

import org.DigitalDucks.BARSDAG.Sales.Sale;
import org.DigitalDucks.BARSDAG.Sales.SaleDTO;

import java.util.List;

public interface SaleService {
    void saveSale(Sale sale);
    void deleteSale(Long id);
    Sale getSaleById(Long id);
    List<Sale> getAllSales();

    SaleDTO convertToDTO(Sale sale);
}
