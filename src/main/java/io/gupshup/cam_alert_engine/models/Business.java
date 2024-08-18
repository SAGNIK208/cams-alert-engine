package io.gupshup.cam_alert_engine.models;

import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "business")
@Data
public class Business {
    @Id
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "email")
    private String email;
    @Column(name = "api_key")
    private String apiKey;
}
