package amaljoyc.geoloc.service;

import amaljoyc.geoloc.exception.PolygonNotFoundException;
import amaljoyc.geoloc.exception.VehicleNotFoundException;
import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.core.Polygon;
import amaljoyc.geoloc.service.polygon.PolygonDataStore;
import amaljoyc.geoloc.service.vehicles.VehicleDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by amaljoyc on 02.05.19.
 */
@Service
public class GeoLocQueryService implements QueryService {

    @Autowired
    private VehicleDataStore vehicleDataStore;

    @Autowired
    private PolygonDataStore polygonDataStore;

    @Override
    public List<String> getCars(String polygonId) {
        Map<String, Polygon> polygons = polygonDataStore.getPolygons();
        Polygon polygon = polygons.get(polygonId);
        if (polygon == null) {
            throw new PolygonNotFoundException(polygonId);
        }

        List<String> vins = new ArrayList<>();
        Map<String, Point> vehicles = vehicleDataStore.getVehicles();
        vehicles.forEach((vin, location) -> {
            if (polygon.contains(location)) {
                vins.add(vin);
            }
        });

        return vins;
    }

    @Override
    public String getPolygon(String vin) {
        Map<String, Point> vehicles = vehicleDataStore.getVehicles();
        Point location = vehicles.get(vin);
        if (location == null) {
            throw new VehicleNotFoundException(vin);
        }

        Map<String, Polygon> polygons = polygonDataStore.getPolygons();
        Optional<Map.Entry<String, Polygon>> entry = polygons.entrySet().stream()
                .filter(map -> map.getValue().contains(location))
                .findFirst();

        if (entry.isPresent()) {
            return entry.get().getKey(); // key is the polygonId
        } else {
            return null;
        }
    }
}
