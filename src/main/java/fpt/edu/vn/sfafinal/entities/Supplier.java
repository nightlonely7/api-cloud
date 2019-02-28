package fpt.edu.vn.sfafinal.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Supplier")
@Table(name = "supplier")
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}
