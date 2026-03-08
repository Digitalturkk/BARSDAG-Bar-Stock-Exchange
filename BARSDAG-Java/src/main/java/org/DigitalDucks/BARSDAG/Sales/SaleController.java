package org.DigitalDucks.BARSDAG.Sales;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.DigitalDucks.BARSDAG.Sales.Serviсes.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Slf4j
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        log.info("Fetching all sales");
        List<SaleDTO> sales = saleService.getAllSales().stream()
                .map(saleService::convertToDTO)
                .toList();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/total-sold")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<Integer> getTotalSold() {
        log.info("Calculating total sold");
        Integer total = saleService.countTotalSold();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/sold-by-drink")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<Integer> getSoldByDrink(@RequestParam String drinkName) {
        log.info("Getting sales for drink: {}", drinkName);
        Integer count = saleService.countSoldByDrinkName(drinkName);
        return ResponseEntity.ok(count);
    }
}
