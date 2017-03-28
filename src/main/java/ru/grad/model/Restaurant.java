package ru.grad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    @SafeHtml
    private String name;

    @Column(name = "votescount", nullable = false)
    @NotNull
    private Integer votescount;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    protected List<Vote> votes;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    @OrderBy("name")
    protected List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getVotescount());
    }

    public Restaurant(Integer id, String name, Integer votescount) {
        super(id);
        this.name = name;
        this.votescount = votescount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotescount() {
        return votescount;
    }

    public void setVotescount(Integer votescount) {
        this.votescount = votescount;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = CollectionUtils.isEmpty(dishes) ? Collections.emptyList() : dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + name +
                ", votescount=" + votescount +
                '}';
    }
}
