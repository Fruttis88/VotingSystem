package ru.grad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    @SafeHtml
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    @Min(0)
    private Integer price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish d){
        this(d.getId(), d.getName(), d.getPrice());
    }

    public Dish(Integer id, String name, Integer price){
        super(id);
        this.name = name;
        this.price = price;
    }

    public Dish(String name, Integer price) {
        this(null, name, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
