package org.DigitalDucks.BARSDAG.Drinks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.DigitalDucks.BARSDAG.Drinks.Services.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@RequiredArgsConstructor
@Slf4j
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<List<DrinkDTO>> getAllDrinks() {
        log.info("Fetching all drinks");
        List<DrinkDTO> drinks = drinkService.getAllDrinks().stream()
                .map(drinkService::convertToDTO)
                .toList();
        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{drinkId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<DrinkDTO> getDrinkById(@PathVariable Long drinkId) {
        log.info("Fetching drink with ID: {}", drinkId);
        Drink drink = drinkService.getDrinkById(drinkId);
        return ResponseEntity.ok(drinkService.convertToDTO(drink));
    }

    @PostMapping("/{drinkId}/sell")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<String> sellDrink(
            @PathVariable Long drinkId,
            @RequestParam Integer quantity) {
        log.info("Selling {} units of drink ID: {}", quantity, drinkId);
        String result = drinkService.sellDrink(drinkId, quantity);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody DrinkDTO drinkDTO) {
        log.info("Creating new drink: {}", drinkDTO.getName());
        Drink drink = new Drink();
        drink.setName(drinkDTO.getName());
        drink.setDescription(drinkDTO.getDescription());
        drink.setOpenPrice(drinkDTO.getOpenPrice());
        drink.setPriceRightNow(drinkDTO.getOpenPrice());
        drinkService.saveDrink(drink);
        return ResponseEntity.status(HttpStatus.CREATED).body(drinkService.convertToDTO(drink));
    }

    @DeleteMapping("/{drinkId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDrink(@PathVariable Long drinkId) {
        log.info("Deleting drink with ID: {}", drinkId);
        drinkService.deleteDrink(drinkId);
        return ResponseEntity.ok("Drink deleted successfully");
    }
}
