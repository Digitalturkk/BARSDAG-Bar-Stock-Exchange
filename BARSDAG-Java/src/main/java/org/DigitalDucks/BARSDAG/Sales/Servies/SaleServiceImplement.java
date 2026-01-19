package org.DigitalDucks.BARSDAG.Sales.Servies;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Sales.Sale;
import org.DigitalDucks.BARSDAG.Sales.SaleDTO;
import org.DigitalDucks.BARSDAG.Sales.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImplement implements SaleService {

    final private SaleRepository saleRepository;

    public SaleServiceImplement(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public SaleDTO convertToDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setId(sale.getId());
        dto.setDrinkId(sale.getDrink().getId());
        dto.setQuantity(sale.getQuantity());
        dto.setSaleTime(sale.getSaleTime());
        return dto;
    }

    @Override
    public void createSale(Drink drink, Integer quantity) {
        Sale sale = new Sale();
        sale.setDrink(drink);
        sale.setQuantity(quantity);
        sale.setSaleTime(LocalDateTime.now());
        saveSale(sale);
    }
}
