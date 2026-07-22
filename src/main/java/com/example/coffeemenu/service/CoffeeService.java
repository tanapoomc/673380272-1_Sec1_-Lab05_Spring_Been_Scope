package com.example.coffeemenu.service;

import com.example.coffeemenu.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CoffeeService {

    private final List<Coffee> coffeeList = new CopyOnWriteArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public CoffeeService() {
        // Initial seed data
        addCoffeeInternal(new Coffee(null, "Espresso", 45.0));
        addCoffeeInternal(new Coffee(null, "Latte", 55.0));
    }

    private Coffee addCoffeeInternal(Coffee coffee) {
        long newId = idCounter.incrementAndGet();
        coffee.setId(newId);
        coffeeList.add(coffee);
        return coffee;
    }

    public List<Coffee> getAllCoffees() {
        return new ArrayList<>(coffeeList);
    }

    public Optional<Coffee> getCoffeeById(Long id) {
        return coffeeList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Coffee addCoffee(Coffee coffee) {
        long newId = idCounter.incrementAndGet();
        Coffee newCoffee = new Coffee(newId, coffee.getName(), coffee.getPrice());
        coffeeList.add(newCoffee);
        return newCoffee;
    }

    public Optional<Coffee> updateCoffee(Long id, Coffee coffeeDetails) {
        return getCoffeeById(id).map(existingCoffee -> {
            existingCoffee.setName(coffeeDetails.getName());
            existingCoffee.setPrice(coffeeDetails.getPrice());
            return existingCoffee;
        });
    }

    public boolean deleteCoffee(Long id) {
        return coffeeList.removeIf(c -> c.getId().equals(id));
    }

    public List<Coffee> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllCoffees();
        }
        String query = name.trim().toLowerCase();
        return coffeeList.stream()
                .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }
}
