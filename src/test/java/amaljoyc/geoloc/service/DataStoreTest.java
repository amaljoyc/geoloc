package amaljoyc.geoloc.service;

import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.core.Polygon;
import amaljoyc.geoloc.service.polygon.PolygonDataStore;
import amaljoyc.geoloc.service.vehicles.VehicleDataStore;
import amaljoyc.geoloc.service.vehicles.external.ExternalVehicleData;
import amaljoyc.geoloc.service.vehicles.external.VehiclePosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by amaljoyc on 03.05.19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataStoreTest {

    @Autowired
    private PolygonDataStore polygonDataStore;

    @Autowired
    private VehicleDataStore vehicleDataStore;

    @Test
    public void testPolygonDataStore() {
        Map<String, Polygon> polygons = polygonDataStore.getPolygons();
        assertEquals(154, polygons.size());
    }

    @Test
    public void testVehicleDataStore() throws InterruptedException {
        vehicleDataStore.updateStore(getSomeExternalVehicleData());
        Map<String, Point> vehicles = vehicleDataStore.getVehicles();
        assertEquals(2, vehicles.size());
    }

    private static List<ExternalVehicleData> getSomeExternalVehicleData() {
        List<ExternalVehicleData> dataList = new ArrayList<>();
        dataList.add(getExternalVehicleData(1, 2, "abc"));
        dataList.add(getExternalVehicleData(5, 10, "xyz"));
        return dataList;
    }

    private static ExternalVehicleData getExternalVehicleData(double longitude, double latitude, String vin) {
        VehiclePosition position = new VehiclePosition();
        position.setLongitude(longitude);
        position.setLatitude(latitude);

        ExternalVehicleData data = new ExternalVehicleData();
        data.setVin(vin);
        data.setPosition(position);
        return data;
    }
}
