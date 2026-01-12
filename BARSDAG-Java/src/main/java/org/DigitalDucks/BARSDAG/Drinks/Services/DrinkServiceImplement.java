package org.DigitalDucks.BARSDAG.Drinks.Services;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Drinks.DrinkDTO;
import org.DigitalDucks.BARSDAG.Drinks.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImplement implements DrinkService {

    final private DrinkRepository drinkRepository;

    public DrinkServiceImplement(DrinkRepository drinkRepository) {
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
}
