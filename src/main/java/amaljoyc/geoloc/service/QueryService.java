package amaljoyc.geoloc.service;

import amaljoyc.geoloc.api.dto.overview.OverviewResponse;

import java.util.List;

/**
 * Created by amaljoyc on 01.05.19.
 */
public interface QueryService {

    /**
     * @param polygonId
     * @return a List of VINs corresponding to the vehicles present inside the Polygon with given polygonId
     */
    List<String> getCars(String polygonId);

    /**
     * @param vin
     * @return the polygonId of the Polygon that contains the vehicle with given vin
     */
    String getPolygon(String vin);

    /**
     * @return a complete overview of the current Polygons & Vehicles and their relationships.
     */
    OverviewResponse getCurrentOverview();
}
