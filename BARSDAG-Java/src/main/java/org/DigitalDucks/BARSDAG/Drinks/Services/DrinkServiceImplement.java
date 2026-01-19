package org.DigitalDucks.BARSDAG.Drinks.Services;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Drinks.DrinkDTO;
import org.DigitalDucks.BARSDAG.Drinks.DrinkRepository;
import org.DigitalDucks.BARSDAG.Sales.SaleRepository;
import org.DigitalDucks.BARSDAG.Sales.Servies.SaleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrinkServiceImplement implements DrinkService {

    final private DrinkRepository drinkRepository;
    final private SaleService saleService;
    final private SaleRepository saleRepository;

    public DrinkServiceImplement(DrinkRepository drinkRepository, SaleService saleService, SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.saleService = saleService;
        this.drinkRepository = drinkRepository;
    }

    @Override
    public void saveDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    @Override
    public void deleteDrink(Long id) {
        drinkRepository.deleteById(id);
    }

    @Override
    public Drink getDrinkById(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    @Override
    public Drink getDrinkByName(String name) {
        return drinkRepository.findByName(name);
    }

    @Override
    public DrinkDTO convertToDTO(Drink drink) {
        DrinkDTO dto = new DrinkDTO();
        dto.setId(drink.getId());
        dto.setName(drink.getName());
        dto.setDescription(drink.getDescription());
        dto.setOpenPrice(drink.getOpenPrice());
        dto.setPriceRightNow(drink.getPriceRightNow());
        dto.setClosedPrice(drink.getClosedPrice());
        return dto;
    }

    @Override
    public String sellDrink(Long drinkId, int quantity) {
        Drink drink = getDrinkById(drinkId);
        if (drink == null) {
            return "Drink not found.";
        }
        if (quantity <= 0) {
            return "Quantity must be greater than zero.";
        }
        saleService.createSale(drink, quantity);
        return "Successfully sold " + quantity + " of " + drink.getName() + ".";
    }

    private Integer getDrinkSalesInPeriod(String drinkName, LocalDateTime start, LocalDateTime end) {
        return saleRepository.countSaleByDrinkName(drinkName, start, end);
    }
}