package org.DigitalDucks.BARSDAG.Drinks.Services;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Drinks.DrinkDTO;

import java.util.List;

public interface DrinkService {
    void saveDrink(Drink drink);
    void deleteDrink(Long id);
    Drink getDrinkById(Long id);
    List<Drink> getAllDrinks();

    Drink getDrinkByName(String name);

    DrinkDTO convertToDTO(Drink drink);

    String sellDrink(Long drinkId, Integer quantity);
}
