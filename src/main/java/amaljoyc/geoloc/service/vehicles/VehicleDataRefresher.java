package amaljoyc.geoloc.service.vehicles;

import amaljoyc.geoloc.service.vehicles.external.ExternalVehicleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Service
@Slf4j
public class VehicleDataRefresher {

    @Value("${vehicle.dataRefresh.url}")
    private String vehicleUrl;

    @Autowired
    private VehicleDataStore vehicleDataStore;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * scheduled to run with a configured delay and calls the external vehicles api to get latest vehicles location
     */
    @Scheduled(fixedDelayString = "${vehicle.dataRefresh.delayInMillis}")
    private void refresh() {
        log.info("Starting vehicle data refresh...");

        try {
            ResponseEntity<List<ExternalVehicleData>> response = restTemplate.exchange(
                    vehicleUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ExternalVehicleData>>() {
                    });
            List<ExternalVehicleData> externalData = response.getBody();
            vehicleDataStore.updateStore(externalData);
        } catch (Exception e) {
            log.error("Failed to get a response from external vehicle API due to {}", e.getMessage());
        }

        log.info("Vehicle data refresh is finished!");
    }
}
