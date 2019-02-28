package fpt.edu.vn.sfafinal.models;

import fpt.edu.vn.sfafinal.entities.Product;
import lombok.Data;

@Data
public class Cart {
    private Product product;
    private Integer quantity;
}
