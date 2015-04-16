package com.mprojection.util;

import java.util.Random;

public class GeoUtil {

    public static Point generateRandomPointWithinArea(Random random, double areaRadius, double lat, double lng) {
        double w = areaRadius / 111300 * Math.sqrt(random.nextDouble());
        double t = 2 * Math.PI * random.nextDouble();
        double x = w * Math.cos(t);
        x = x / Math.cos(lng);
        double y = w * Math.sin(t);
        return new Point(x + lat, y + lng);
    }

    public static class Point {

        private double x;
        private double y;

        private Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

    }

}
