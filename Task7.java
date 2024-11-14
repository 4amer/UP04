package geometry;

public class Point3D {
    private double x, y, z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static Point3D midpoint(Point3D p1, Point3D p2) {
        return new Point3D((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2, (p1.getZ() + p2.getZ()) / 2);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
package geometry;

public class Edge {
    private Point3D start;
    private Point3D end;

    public Edge(Point3D start, Point3D end) {
        this.start = start;
        this.end = end;
    }

    public Point3D getStart() {
        return start;
    }

    public Point3D getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Edge from " + start + " to " + end;
    }
}
package geometry;

public class Triangle {
    private Point3D vertex1;
    private Point3D vertex2;
    private Point3D vertex3;

    public Triangle(Point3D vertex1, Point3D vertex2, Point3D vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Point3D getVertex1() {
        return vertex1;
    }

    public Point3D getVertex2() {
        return vertex2;
    }

    public Point3D getVertex3() {
        return vertex3;
    }

    public Edge getMedian() {
        Point3D midpoint = Point3D.midpoint(vertex2, vertex3);
        return new Edge(vertex1, midpoint);
    }

    @Override
    public String toString() {
        return "Triangle with vertices " + vertex1 + ", " + vertex2 + ", " + vertex3;
    }
}
package geometry;

public class Pyramid {
    private Point3D apex;
    private Triangle base;

    public Pyramid(Point3D apex, Triangle base) {
        this.apex = apex;
        this.base = base;
    }

    public Point3D getApex() {
        return apex;
    }

    public Triangle getBase() {
        return base;
    }

    public Edge getSideEdge() {
        return new Edge(apex, base.getVertex1());
    }

    @Override
    public String toString() {
        return "Pyramid with apex " + apex + " and base " + base;
    }
}
package geometry;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private List<Point3D> points;

    public Section() {
        points = new ArrayList<>();
    }

    public void addPoint(Point3D point) {
        points.add(point);
    }

    @Override
    public String toString() {
        return "Section points: " + points;
    }

    public static Section createSection(Pyramid pyramid) {
        Section section = new Section();

        Edge sideEdge = pyramid.getSideEdge();
        Edge median = pyramid.getBase().getMedian();

        section.addPoint(sideEdge.getStart());
        section.addPoint(sideEdge.getEnd());

        section.addPoint(median.getStart());
        section.addPoint(median.getEnd());

        return section;
    }
}
package geometry;

public class Main {
    public static void main(String[] args) {
        
        Point3D apex = new Point3D(0, 0, 10); // Вершина пирамиды
        Point3D baseVertex1 = new Point3D(0, 0, 0);
        Point3D baseVertex2 = new Point3D(5, 0, 0);
        Point3D baseVertex3 = new Point3D(0, 5, 0);

        Triangle base = new Triangle(baseVertex1, baseVertex2, baseVertex3);
        Pyramid pyramid = new Pyramid(apex, base);

        Section section = Section.createSection(pyramid);

        System.out.println(pyramid);
        System.out.println(section);
    }
}
