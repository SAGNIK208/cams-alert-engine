package io.gupshup.cam_alert_engine.models;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "webhook_details")
@Data 
public class WebhookDetails {
    @Id
    @Column(name = "webhook_id")
    private String webhookId; 
    @Column(name = "webhook_url")
    private String webhookUrl;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "config", columnDefinition = "jsonb")
    private String config;
    @Column(name = "source")
    private String source;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "business_id")
    private String businessId;
}
