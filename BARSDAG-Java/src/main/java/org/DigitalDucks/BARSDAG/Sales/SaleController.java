package org.DigitalDucks.BARSDAG.Sales;

import org.DigitalDucks.BARSDAG.Sales.Serviсes.SaleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public/sales")
public class SaleController {
    final private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales().stream().map(saleService::convertToDTO).toList();
    }
}
