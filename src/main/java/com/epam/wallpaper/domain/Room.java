package com.epam.wallpaper.domain;

import lombok.ToString;

public class Room {
    private static final double UNSET_SURF = -1.0;

    private final double length;
    private final double width;
    private final double height;
    private double surface = UNSET_SURF;
    private double extra = UNSET_SURF;
    private double total = UNSET_SURF;

    private double min(double a, double b, double c) {
        double min = a;
        if(Double.compare(min, b) > 0) min = b;
        if(Double.compare(min, b) > 0) min = c;
        return min;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSurface() {
        // cache surface value
        surface = Double.compare(surface, UNSET_SURF) == 0 ? 2 * (length + width + height) : surface;
        return surface;
    }

    public double getExtra() {
        // cache extra value
        extra = Double.compare(extra, UNSET_SURF) == 0 ? min(length, width, height) : extra;
        return extra;
    }

    public double getTotal() {
        // cache total value
        total = Double.compare(total, UNSET_SURF) == 0 ? getSurface() + getExtra() : total;
        return total;
    }

    public boolean isCubic() {
        return Double.compare(length, width) == 0 &&
                Double.compare(width, height) == 0;
    }

    public Room(Double length, Double width, Double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }

        if (null == other || this.getClass() != other.getClass()) {
            return false;
        }

        return Double.compare(length, ((Room) other).getLength()) == 0 &&
                Double.compare(width, ((Room) other).getWidth()) == 0 &&
                Double.compare(height, ((Room) other).getHeight()) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long lengthBits = Double.doubleToLongBits(length);
        long widthBits = Double.doubleToLongBits(width);
        long heightBits = Double.doubleToLongBits(height);

        result = 31 * result + (int) (lengthBits ^ (lengthBits >>> 32));
        result = 31 * result + (int) (widthBits ^ (widthBits >>> 32));
        result = 31 * result + (int) (heightBits ^ (heightBits >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height;
    }

}
