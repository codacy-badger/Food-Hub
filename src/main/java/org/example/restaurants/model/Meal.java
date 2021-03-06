package org.example.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


//represents a record in restaurant menu

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Meal extends AbstractBaseEntity {

    @Column(nullable = false, columnDefinition = "timestamp default now()")
    @JsonIgnore
    private Date published = new Date();

    @Range(min = 10, max = 1000)
    @Column(nullable = false)
    private int price;

    @NotEmpty(message = "description can not be empty")
    @Column(name = "title", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    @JsonIgnore
    private Restaurant restaurant;

    public Meal(Integer id, Date published, int price, String description, Restaurant restaurant) {
        super(id);
        this.published = published;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, int price, String description, Restaurant restaurant) {
        super(id);
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public Meal(int price, String description) {
        this(null, price, description, null);
    }
}
