package com.example.coffeemenu.controller;

import com.example.coffeemenu.model.Coffee;
import com.example.coffeemenu.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    // Dependency Injection via constructor
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // 1. GET /coffees - Get all coffees
    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    // Bonus: GET /coffees/search?name=... - Search coffees by name
    @GetMapping("/search")
    public List<Coffee> searchCoffees(@RequestParam(name = "name", required = false) String name) {
        return coffeeService.searchByName(name);
    }

    // 2. GET /coffees/{id} - Get coffee by ID (returns 404 if not found)
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        return coffeeService.getCoffeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. POST /coffees - Add new coffee (returns 201 Created)
    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee createdCoffee = coffeeService.addCoffee(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffee);
    }

    // 4. PUT /coffees/{id} - Update coffee by ID (returns 404 if not found)
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffeeDetails) {
        return coffeeService.updateCoffee(id, coffeeDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE /coffees/{id} - Delete coffee by ID (returns 200 OK or 404 if not found)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        boolean deleted = coffeeService.deleteCoffee(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
