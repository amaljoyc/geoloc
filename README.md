# GeoLoc
Calculates which cars are in which polygons!

### Basic Algorithm
Implements the Ray casting algorithm to find about points inside polygon (Read more: https://en.wikipedia.org/wiki/Point_in_polygon)

- To find if a Point (x, y) is inside a polygon, a LineSegment is drawn to the right starting from P (x, y) until the bounding box of the Polygon.
- Then counts the number of times the LineSegment intersects with Polygon edges.
- The Point (x, y) is inside the polygon if either count of intersections is odd or point lies on an edge of polygon.  If none of the conditions is true, then point lies outside.

### Sub2
