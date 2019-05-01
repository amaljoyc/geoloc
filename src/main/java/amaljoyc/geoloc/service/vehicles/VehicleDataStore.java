package amaljoyc.geoloc.service.vehicles;

import amaljoyc.geoloc.service.vehicles.external.ExternalVehicleData;
import amaljoyc.geoloc.service.vehicles.external.VehiclePosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Service
@Slf4j
public class VehicleDataStore {

    private List<VehicleData> vehicleDataCache;

    public void updateStore(List<ExternalVehicleData> externalData) {
        List<VehicleData> vehicles = new ArrayList<>();

        externalData.forEach(item -> {
            VehiclePosition position = item.getPosition();
            VehicleData vehicleData = new VehicleData(item.getVin(), position.getLongitude(), position.getLatitude());
            vehicles.add(vehicleData);
        });

        refreshCache(vehicles);
    }

    private synchronized void refreshCache(List<VehicleData> vehicles) {
        log.info("Refreshed vehicles count = {}", vehicles.size());
        vehicleDataCache = vehicles;
    }

    public List<VehicleData> getVehicles() {
        return vehicleDataCache;
    }
}
