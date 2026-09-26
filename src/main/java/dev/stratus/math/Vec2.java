package dev.stratus.math;

public final class Vec2 {

    public final double x;
    public final double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 o) {
        return new Vec2(x + o.x, y + o.y);
    }

    public Vec2 sub(Vec2 o) {
        return new Vec2(x - o.x, y - o.y);
    }

    public Vec2 scale(double s) {
        return new Vec2(x * s, y * s);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 normalize() {
        double len = length();
        return len == 0 ? new Vec2(0, 0) : new Vec2(x / len, y / len);
    }

    public double dot(Vec2 o) {
        return x * o.x + y * o.y;
    }

    public double distanceTo(Vec2 o) {
        return sub(o).length();
    }
}
