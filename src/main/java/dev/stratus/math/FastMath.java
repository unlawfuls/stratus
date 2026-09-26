package dev.stratus.math;

public final class FastMath {

    private FastMath() {
    }

    public static double clamp(double v, double min, double max) {
        return v < min ? min : (v > max ? max : v);
    }

    public static int clamp(int v, int min, int max) {
        return v < min ? min : (v > max ? max : v);
    }

    public static double lerp(double a, double b, double t) {
        return a + (b - a) * clamp(t, 0.0, 1.0);
    }

    public static double roundTo(double v, int places) {
        double f = Math.pow(10, places);
        return Math.round(v * f) / f;
    }

    public static long floorMod(long a, long b) {
        long r = a % b;
        return r < 0 ? r + b : r;
    }
}
