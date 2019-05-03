# GeoLoc
Calculates which cars are in which polygons!

### Bootstrap Details
The app / service by default runs under the port `7200` (unless specified otherwise using the env variable - PORT)

A utility bash script is provided for supporting various operations, some of which are listed below,

##### 1. Build: does maven build and creates a docker image of the app (using the Dockerfile)
```$xslt
./bootstrap.sh build
```

##### 2. Run: runs both services (challenge API as well as given external API) as separate Docker Containers using Docker Compose 
```$xslt
./bootstrap.sh run
```

##### 3. Status: prints status of both running services
```$xslt
./bootstrap.sh status
```

##### 4. Tail: tails the logs of each service
```$xslt
./bootstrap.sh tail <service_name>

eg. ./bootstrap.sh tail geoloc
```

##### 5. Stop: stops and removes both containers corresponding to each service
```$xslt
./bootstrap.sh stop
```

### For Manual Docker run
Although it is an extra effort and hence not advisable, but still you can run the GeoLoc app manually with following command
```$xslt
docker run --network="host" -d -p 7200:7200 geoloc:latest
```
The only precondition is that the external vehicles API is expected to run before the GeoLoc app for proper working functionality.

### Swagger
Swagger UI is accessible under the url http://localhost:7200/swagger-ui.html

There are three endpoints implemented,
- http://localhost:7200/v1/cars/{polygonId}
- http://localhost:7200/v1/polygon/{vin}
- http://localhost:7200/v1/overview (additional endpoint for an overview of all poylgons & cars)

All 3 API endpoints has an average response time of 3 ms to 8 ms.

### Service Details
The service can be divided into 4 different sections,

##### 1. core:
Core consits of geometric figures;
A Polygon is composed of several edges (or LineSegments) and/or several vertices (or Points).
A LineSegment is made up of two Points - startPoint & endPoint.
A BoundingBox defines the maximum outward area of the Polygon - none of the Polygon vertices or edges will go out of BoundingBox.
(important decisions/logic used in the core package are commented in line or as javadocs along with the code).

##### 2. polygon:
This package has an in memory cache or dataStore for the polygonData collected from the json dump.
The dump is imported during the app startup and the minimum required data is stored in the cache for improved performance.

##### 3. vehicle:
This package has again another in memory cache for storing the vehicleData - which is collected and refreshed under regular intervals.
The refresh happens by calling the external (given) vehicles API and getting the latest location of each vehicles.
The refresh delay is configured for now as 1 min.

##### 4. query:
This is the agregation or query service that uses the data from both stores (polygon & vehicle) and processes it further to serve the requesting APIs

### Algorithm Used
Implements the Ray casting algorithm to find about points inside polygon (Read more: https://en.wikipedia.org/wiki/Point_in_polygon)

- To find if a Point (x, y) is inside a polygon, a LineSegment is drawn to the right starting from P (x, y) until the bounding box of the Polygon.
- Then counts the number of times the LineSegment intersects with Polygon edges.
- The Point (x, y) is inside the polygon if either count of intersections is odd or point lies on an edge of polygon.  If none of the conditions is true, then point lies outside.

### Math Used

The equation of a non-vertical line passing through two points A(x1, y1) and B(x2, y2) is given by
    `(x − x1) / (x2 − x1) = (y − y1) / (y2 − y1)`
where a point P = (x, y) falls on this line.
(Read more: https://www.emathzone.com/tutorials/geometry/two-points-form-equation-of-a-line.html#ixzz5mb4SvOFc)

ie, `value = (x − x1)(y2 − y1) − (y − y1)(x2 − x1)` and if `value = 0`, then the point lies exactly on the line.

---

Any straight line (except vertical) on a plane can be defined by the linear function:
`y = mx + b`
where m is the slope and b is the y-intercept.
(Read more: https://www.mathsisfun.com/equation_of_line.html)

At the point of intersection they will both have the same y-coordinate value

To calculate the slope of a line you need only two points from that line, (x1, y1) and (x2, y2).
`m or slope = (y2 - y1) / (x2 - x1)`
(Read more: http://cls.syr.edu/mathtuneup/grapha/Unit4/Unit4a.html)

### Test Coverage
on service package

- Class % = 100%
- Method % = 89%
- Line % = 93%

### Tech Stack
- Java 8
- Spring Boot 2
- Maven
- Docker and Docker Compose
- Bash Scripting