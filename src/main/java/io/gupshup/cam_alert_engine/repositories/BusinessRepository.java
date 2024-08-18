package io.gupshup.cam_alert_engine.repositories;

import io.gupshup.cam_alert_engine.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {
    // Custom query methods can be defined here if needed
}

