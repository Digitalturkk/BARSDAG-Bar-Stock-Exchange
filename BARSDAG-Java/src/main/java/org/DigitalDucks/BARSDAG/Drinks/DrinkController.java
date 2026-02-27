package org.DigitalDucks.BARSDAG.Drinks;

import org.DigitalDucks.BARSDAG.Drinks.Services.DrinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/drinks")
public class DrinkController {

    final private DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/all")
    public List<DrinkDTO> getAllDrinks() {
        List<Drink> drinks = drinkService.getAllDrinks();
        return drinks.stream().map(drinkService::convertToDTO).toList();
    }

    @GetMapping("/id={drinkId}")
    public DrinkDTO getDrinkById(@PathVariable Long drinkId) {
        Drink drink = drinkService.getDrinkById(drinkId);
        return drinkService.convertToDTO(drink);
    }

    @PostMapping("/sell-id={drinkId}-qnt={quantity}")
    public String sellDrink(@PathVariable Long drinkId, @PathVariable Integer quantity) {
        return drinkService.sellDrink(drinkId, quantity);
    }
}
