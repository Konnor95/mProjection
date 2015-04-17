package com.mprojection.util;

import java.util.Random;

public class GeoUtil {

    public static Point generateRandomPointWithinArea(Random random, int areaRadius, int minRadius, double lat, double lng) {
        double radius = (random.nextInt((areaRadius - minRadius) + 1) + minRadius) / 111300.0;
        int angle = random.nextInt(91);
        double dx = radius * Math.cos(angle);
        double dy = radius * Math.sin(angle);
        dx = random.nextBoolean() ? dx : -dx;
        dy = random.nextBoolean() ? dy : -dy;
        return new Point(lat + dx, lng + dy);
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
