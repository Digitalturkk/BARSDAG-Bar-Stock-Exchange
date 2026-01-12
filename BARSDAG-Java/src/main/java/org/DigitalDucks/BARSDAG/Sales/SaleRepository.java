package org.DigitalDucks.BARSDAG.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM(s.quantity) FROM Sale s WHERE s.saleTime >= :start AND s.saleTime <= :end")
    Integer countTotalSoldInRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
