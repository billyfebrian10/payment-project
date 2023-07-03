package com.billyfebrian.payment.project.assignment.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  @Column(name = "item_name")
  private String itemName;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private Double price;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Inventory inventory = (Inventory) o;
    return id.equals(inventory.id) && itemName.equals(inventory.itemName) && quantity.equals(
        inventory.quantity) && price.equals(inventory.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemName, quantity, price);
  }
}
