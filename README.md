# GeoLoc
Calculates which cars are in which polygons!

### Bootstrap Details

### Service Details

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

