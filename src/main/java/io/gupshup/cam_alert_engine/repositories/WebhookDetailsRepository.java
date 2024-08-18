package io.gupshup.cam_alert_engine.repositories;

import io.gupshup.cam_alert_engine.models.WebhookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebhookDetailsRepository extends JpaRepository<WebhookDetails, String> {
    Optional<WebhookDetails> findByWebhookId(String webhookId);
    // Custom query methods can be defined here if needed
}

