package com.example.projecttest.model.unidirection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="product")
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;

  public Product(String name) {
    this.name = name;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Category category;

  @ManyToMany
  @JoinTable(name = "product_tag" ,joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
  private List<Tag> tags;

}


