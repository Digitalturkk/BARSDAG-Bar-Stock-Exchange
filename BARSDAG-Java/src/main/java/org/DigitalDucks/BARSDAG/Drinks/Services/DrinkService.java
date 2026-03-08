package org.DigitalDucks.BARSDAG.Drinks.Services;

import org.DigitalDucks.BARSDAG.Drinks.Drink;
import org.DigitalDucks.BARSDAG.Drinks.DrinkDTO;

import java.util.List;

public interface DrinkService {
    //Create operations
    void saveDrink(Drink drink);

    // Read operations
    Drink getDrinkById(Long id);
    List<Drink> getAllDrinks();
    Drink getDrinkByName(String name);

    // Update operations
    void deleteDrink(Long id);

    // DTO conversion
    DrinkDTO convertToDTO(Drink drink);

    // Business logic
    String sellDrink(Long drinkId, Integer quantity);
}