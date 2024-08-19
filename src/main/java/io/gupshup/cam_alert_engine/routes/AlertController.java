package io.gupshup.cam_alert_engine.routes;

import io.gupshup.cam_alert_engine.dto.AlertRequest;
import io.gupshup.cam_alert_engine.models.Business;
import io.gupshup.cam_alert_engine.models.WebhookDetails;
import io.gupshup.cam_alert_engine.repositories.BusinessRepository;
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
import java.util.UUID;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private WebhookDetailsRepository webhookDetailsRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> receiveAlert(@RequestBody AlertRequest alertRequest) {
        String webhookId = alertRequest.getWebhookId();
        Optional<WebhookDetails> webhookDetailsOpt = webhookDetailsRepository.findByWebhookId(webhookId);

        if (webhookDetailsOpt.isPresent()) {
            String email = webhookDetailsOpt.get().getEmail();
            UUID businessId = UUID.fromString(webhookDetailsOpt.get().getBusinessId());
            Optional<Business> businessDetailsOpt = businessRepository.findById(businessId);
            if (businessDetailsOpt.isPresent()) {
                Business businessDetails = businessDetailsOpt.get();
                String businessEmail = businessDetails.getEmail();
                String alertMessage = createAlertMessage(alertRequest);
                emailService.sendSimpleMessage(email, "Alert Notification", alertMessage);
                emailService.sendSimpleMessage(businessEmail, "Business Alert Notification", alertMessage);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business not found");
            }
            String alertMessage = createAlertMessage(alertRequest);
            emailService.sendSimpleMessage(email, "Alert Notification", alertMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Webhook not found");
        }
        return ResponseEntity.ok().build();
    }

    private String createAlertMessage(AlertRequest alertRequest) {
        return "Alert: " + alertRequest.getAlertName() + "\n" +
                "Details: " + alertRequest.getMessage();
    }
}

