package org.DigitalDucks.BARSDAG.Drinks.Services;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Drinks.DrinkDTO;
import org.DigitalDucks.BARSDAG.Drinks.DrinkRepository;
import org.DigitalDucks.BARSDAG.Exceptions.GlobalNotFoundException;
import org.DigitalDucks.BARSDAG.Sales.SaleRepository;
import org.DigitalDucks.BARSDAG.Sales.Serviсes.SaleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    // Basic CRUD operations

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
        return drinkRepository.findById(id).orElseThrow(
                () -> new GlobalNotFoundException("Drink with id " + id + " not found.", new NoSuchElementException()));
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    @Override
    public Drink getDrinkByName(String name) {
        Drink drink = drinkRepository.findByName(name);
        if (drink == null) {
            throw new GlobalNotFoundException("Drink with name " + name + " not found.", new NoSuchElementException());
        }
        return drink;
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

    // Business logic

    @Override
    public String sellDrink(Long drinkId, Integer quantity) {
        Drink drink = getDrinkById(drinkId);

        adjustDrinkPriceAfterPurchase(drinkId);
        saleService.createSale(drink, quantity);

        return "Successfully sold " + quantity + " of " + drink.getName() + "."
                +"\nCurrent price: " + drink.getPriceRightNow();
    }

    // Additional methods

        // Price adjustments
    private void adjustDrinkPriceAfterPurchase(Long drinkId) {
        Drink drink = getDrinkById(drinkId);
        List<Drink> allDrinks = getAllDrinks();
        allDrinks.remove(drink);
        for(Drink otherDrink : allDrinks) {
            otherDrink.setPriceRightNow(otherDrink.getPriceRightNow() * 0.98);
            saveDrink(otherDrink);
        }
        drink.setPriceRightNow(drink.getPriceRightNow() * 1.02);
        saveDrink(drink);
    }

    @Scheduled(fixedRate = 600000) // Every 10 minutes
    private void adjustDrinkPriceAfterEvery10Minutes() {
        LocalTime time = LocalTime.now();

        if(time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(23, 0))) {

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime tenMinutesAgo = now.minusMinutes(10);

            List<Drink> allDrinks = getAllDrinks();
            List<Drink> drinksToAdjust = new ArrayList<>();

            for(Drink drink : allDrinks) {
                Integer salesInLast10Minutes = getDrinkSalesInPeriod(drink.getName(), tenMinutesAgo, now);

                Double currentPrice = drink.getPriceRightNow();
                Double openPrice = drink.getOpenPrice();
                Double minPrice = openPrice * 0.7;
                Double maxPrice = openPrice * 1.3;

                boolean adjustmentMade = false;

                if(salesInLast10Minutes < 3 && currentPrice > minPrice) {
                    if (currentPrice >= openPrice) {
                        drink.setPriceRightNow(drink.getPriceRightNow() * 0.99);
                        adjustmentMade = true;
                    }
                }
                else if (salesInLast10Minutes > 7 && currentPrice < maxPrice) {
                    drink.setPriceRightNow(drink.getPriceRightNow() * 1.01);
                    adjustmentMade = true;
                }
                if (adjustmentMade) {
                    drinksToAdjust.add(drink);
                }
            }
            if (!drinksToAdjust.isEmpty()){
                drinkRepository.saveAll(drinksToAdjust);
            }
        }
    }

        // Sales tracking within a specific period
    private Integer getDrinkSalesInPeriod(String drinkName, LocalDateTime start, LocalDateTime end) {
        Integer count = saleRepository.countSaleByDrinkName(drinkName, start, end);
        return count != null ? count : 0;
    }
}