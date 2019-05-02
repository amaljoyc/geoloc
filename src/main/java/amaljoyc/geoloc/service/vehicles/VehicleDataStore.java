package amaljoyc.geoloc.service.vehicles;

import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.vehicles.external.ExternalVehicleData;
import amaljoyc.geoloc.service.vehicles.external.VehiclePosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Service
@Slf4j
public class VehicleDataStore {

    /*
        key   --> VIN
        value --> location as Point
     */
    private Map<String, Point> vehicleDataCache;

    /**
     * updates the vehicleDataCache with new vehicles location
     * @param externalData
     */
    public void updateStore(List<ExternalVehicleData> externalData) {
        Map<String, Point> vehicles = new HashMap<>();

        externalData.forEach(item -> {
            VehiclePosition position = item.getPosition();
            Point location = new Point(position.getLongitude(), position.getLatitude());
            vehicles.put(item.getVin(), location);
        });

        refreshCache(vehicles);
    }

    private void refreshCache(Map<String, Point> vehicles) {
        log.info("Refreshed vehicles count = {}", vehicles.size());
        vehicleDataCache = vehicles;
    }

    public Map<String, Point> getVehicles() {
        return vehicleDataCache;
    }
}
