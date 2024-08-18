package io.gupshup.cam_alert_engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertRequest {
    @JsonProperty("webhook_id")
    private String webhookId;
    @JsonProperty("alert_name")
    private String alertName;
    @JsonProperty("message")
    private String message;
}

