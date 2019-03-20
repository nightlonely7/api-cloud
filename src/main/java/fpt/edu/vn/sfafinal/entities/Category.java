package fpt.edu.vn.sfafinal.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "category", schema = "prc391_simple_sale")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
