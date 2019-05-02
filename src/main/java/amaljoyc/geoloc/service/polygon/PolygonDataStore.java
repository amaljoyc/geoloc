package amaljoyc.geoloc.service.polygon;

import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.core.Polygon;
import amaljoyc.geoloc.service.polygon.dump.Geometry;
import amaljoyc.geoloc.service.polygon.dump.PolygonDataDump;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Service
@Slf4j
public class PolygonDataStore {

    private static final String POLYGON_TYPE = "Polygon";

    /*
        key   --> polygonId
        value --> Polygon
     */
    private Map<String, Polygon> polygonDataCache;

    @Value("${polygon.dataDump.location}")
    Resource polygonDumpFile;

    /**
     * fills up the polygonDataCache from a json dump file upon application startup
     * @throws IOException
     */
    @PostConstruct
    private void init() throws IOException {
        log.info("Loading the polygon data dump: " + polygonDumpFile.getFilename());
        ObjectMapper mapper = new ObjectMapper();
        CollectionType dataType = mapper.getTypeFactory().constructCollectionType(List.class, PolygonDataDump.class);
        List<PolygonDataDump> polygonDataDump = mapper.readValue(polygonDumpFile.getFile(), dataType);

        transformAndStoreData(polygonDataDump);
        log.info("Finished loading! total polygons = " + polygonDataCache.size());
    }

    private void transformAndStoreData(List<PolygonDataDump> dataDump) {
        polygonDataCache = new HashMap<>();

        dataDump.forEach(data -> {
            Geometry geometry = data.getGeometry();
            if (geometry.getType().equals(POLYGON_TYPE)) {
                List<Point> vertices = new ArrayList<>();

                for (double[] coordinate : geometry.getCoordinates()[0]) {
                    vertices.add(new Point(coordinate[0], coordinate[1]));
                }

                polygonDataCache.put(data.getId(), Polygon.create(vertices));
            }
        });
    }

    public Map<String, Polygon> getPolygons() {
        return polygonDataCache;
    }
}
