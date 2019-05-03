package amaljoyc.geoloc.service;

import amaljoyc.geoloc.api.dto.overview.OverviewResponse;
import amaljoyc.geoloc.api.dto.overview.PolygonVehiclesMap;
import amaljoyc.geoloc.exception.PolygonNotFoundException;
import amaljoyc.geoloc.exception.VehicleNotFoundException;
import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.core.Polygon;
import amaljoyc.geoloc.service.polygon.PolygonDataStore;
import amaljoyc.geoloc.service.vehicles.VehicleDataStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by amaljoyc on 03.05.19.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeoLocQueryServiceTest {

    private static final String VIN_INSIDE = "1GC0KXCG3BSKCC7K8";
    private static final String VIN_OUTSIDE = "2D8GP253333MZ5Z3C";
    private static final String POLYGON_ID = "58a58bd1766d51540f77933d";

    @Mock
    private VehicleDataStore vehicleDataStore;

    @Mock
    private PolygonDataStore polygonDataStore;

    @InjectMocks
    QueryService queryService = new GeoLocQueryService();

    @Test(expected = PolygonNotFoundException.class)
    public void testExceptionDuringGetCars() {
        when(polygonDataStore.getPolygons()).thenReturn(new HashMap<>());
        queryService.getCars("abc");
    }

    @Test(expected = VehicleNotFoundException.class)
    public void testExceptionDuringGetPolygon() {
        when(vehicleDataStore.getVehicles()).thenReturn(new HashMap<>());
        queryService.getPolygon("123");
    }

    @Test
    public void testGetCars() {
        when(polygonDataStore.getPolygons()).thenReturn(getPolygons());
        when(vehicleDataStore.getVehicles()).thenReturn(getVehicles());
        List<String> vins = queryService.getCars(POLYGON_ID);
        assertEquals(VIN_INSIDE, vins.get(0));
    }

    @Test
    public void testGetPolygon() {
        when(polygonDataStore.getPolygons()).thenReturn(getPolygons());
        when(vehicleDataStore.getVehicles()).thenReturn(getVehicles());
        String polygonId = queryService.getPolygon(VIN_INSIDE);
        assertEquals(POLYGON_ID, polygonId);
    }

    @Test
    public void testGetCurrentOverview() {
        when(polygonDataStore.getPolygons()).thenReturn(getPolygons());
        when(vehicleDataStore.getVehicles()).thenReturn(getVehicles());
        OverviewResponse overviewResponse = queryService.getCurrentOverview();
        assertEquals(getExpectedOverviewResponse(), overviewResponse);
    }

    private static Map<String, Point> getVehicles() {
        Map<String, Point> vehicles = new HashMap<>();
        vehicles.put(VIN_INSIDE, new Point(4, 3));
        vehicles.put(VIN_OUTSIDE, new Point(2, 5));
        return vehicles;
    }

    private static Map<String, Polygon> getPolygons() {
        Map<String, Polygon> polygons = new HashMap<>();
        polygons.put(POLYGON_ID, Polygon.create(getVertices()));
        return polygons;
    }

    private static List<Point> getVertices() {
        return Arrays.asList(
                new Point(1.5, 1.5),
                new Point(9.5, 1.5),
                new Point(4, 6),
                new Point(1.5, 1.5)
        );
    }

    private OverviewResponse getExpectedOverviewResponse() {
        return new OverviewResponse(
                Arrays.asList(new PolygonVehiclesMap(POLYGON_ID, Arrays.asList(VIN_INSIDE))),
                Arrays.asList(VIN_OUTSIDE),
                Collections.emptyList()
        );
    }
}
