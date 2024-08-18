package io.gupshup.cam_alert_engine.routes;

import io.gupshup.cam_alert_engine.dto.AlertRequest;
import io.gupshup.cam_alert_engine.models.WebhookDetails;
import io.gupshup.cam_alert_engine.repositories.WebhookDetailsRepository;
import io.gupshup.cam_alert_engine.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private WebhookDetailsRepository webhookDetailsRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> receiveAlert(@RequestBody AlertRequest alertRequest) {
        String webhookId = alertRequest.getWebhookId();
        Optional<WebhookDetails> webhookDetailsOpt = webhookDetailsRepository.findByWebhookId(webhookId);

        if (webhookDetailsOpt.isPresent()) {
            String email = webhookDetailsOpt.get().getEmail();
            String alertMessage = createAlertMessage(alertRequest); // Create the alert message
            emailService.sendSimpleMessage(email, "Alert Notification", alertMessage);
            return ResponseEntity.ok("Alert sent to " + email);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Webhook ID not found");
        }
    }

    private String createAlertMessage(AlertRequest alertRequest) {
        return "Alert: " + alertRequest.getAlertName() + "\n" +
                "Details: " + alertRequest.getMessage();
    }
}

