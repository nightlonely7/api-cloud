package fpt.edu.vn.sfafinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Payment")
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "createdTime")
    private LocalDateTime createdTime;

    @Transient
    private List<PaymentDetail> paymentDetails;

    @PrePersist
    private void onCreate() {
        this.setCreatedTime(LocalDateTime.now());
    }

}