package amaljoyc.geoloc.service;

/**
 * Created by amaljoyc on 03.05.19.
 */

import amaljoyc.geoloc.service.vehicles.VehicleDataRefresher;
import amaljoyc.geoloc.service.vehicles.VehicleDataStore;
import amaljoyc.geoloc.service.vehicles.external.ExternalVehicleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleDataRefresherTest {

    private static final String VEHICLE_MOCK_URL = "mock_url";

    @Mock
    private ResponseEntity<List<ExternalVehicleData>> responseEntity;

    @Mock
    private List<ExternalVehicleData> externalData;

    @Mock
    private VehicleDataStore vehicleDataStore;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VehicleDataRefresher vehicleDataRefresher = new VehicleDataRefresher();

    @Test
    public void testDataRefresher() throws Exception {
        when(
                restTemplate.exchange(
                        VEHICLE_MOCK_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ExternalVehicleData>>() {
                        })
        ).thenReturn(responseEntity);

        when(
                responseEntity.getBody()
        ).thenReturn(externalData);

        Field vehicleUrl = VehicleDataRefresher.class.getDeclaredField("vehicleUrl");
        vehicleUrl.setAccessible(true);
        vehicleUrl.set(vehicleDataRefresher, VEHICLE_MOCK_URL);

        Method refreshMethod = VehicleDataRefresher.class.getDeclaredMethod("refresh");
        refreshMethod.setAccessible(true);
        refreshMethod.invoke(vehicleDataRefresher);

        verify(restTemplate)
                .exchange(
                        VEHICLE_MOCK_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ExternalVehicleData>>() {
                        }
                );
        verify(vehicleDataStore).updateStore(anyList());
    }
}
