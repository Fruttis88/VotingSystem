package ru.grad.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.grad.model.Dish;
import ru.grad.service.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.grad.util.ValidationUtil.checkIdConsistent;
import static ru.grad.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    private static final Logger LOG = LoggerFactory.getLogger(DishController.class);

    static final String URL = "/api/v1/dishes";

    private final DishService service;

    @Autowired
    public DishController(DishService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}/{restaurantId}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        LOG.info("get dish {} for Restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }
    @GetMapping(value = "/{restaurantId}")
    public List<Dish> getMenu(@PathVariable("restaurantId") int restaurantId) {
        LOG.info("getMenu for Restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/{restaurantId}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        LOG.info("delete dish {} for Restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{restaurantId}")
    public void deleteMenu(@PathVariable("restaurantId") int restaurantId) {
        LOG.info("deleteMenu, restaurantId= " + restaurantId);
        service.deleteRestaurantMenu(restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        checkIdConsistent(dish, id);
        LOG.info("update {} for Restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        checkNew(dish);
        LOG.info("create {} for Restaurant {}", dish, restaurantId);
        Dish created = service.save(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}