package com.example.projecttest.model.unidirection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="category")
@Table(name="category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;

  public Category(String name) {
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
  @JoinColumn(name = "category_id")
  private List<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    product.setCategory(this);
    products.add(product);
  }

  public void removeProduct(Product product) {
    product.setCategory(null);
    products.remove(product);
  }

}
