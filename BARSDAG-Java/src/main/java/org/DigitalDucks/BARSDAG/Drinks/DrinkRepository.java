package org.DigitalDucks.BARSDAG.Drinks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findByName(String name);
}
