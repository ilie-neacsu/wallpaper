package com.epam.wallpaper.domain;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
public class Room {
    private static final BigDecimal UNSET_SURF = new BigDecimal(-1);

    private final BigDecimal length;
    private final BigDecimal width;
    private final BigDecimal height;
    private BigDecimal surface = UNSET_SURF;
    private BigDecimal extra = UNSET_SURF;
    private BigDecimal total = UNSET_SURF;

    private BigDecimal min(BigDecimal a, BigDecimal b, BigDecimal c) {
        BigDecimal min = a;
        if(min.compareTo(b) > 0) min = b;
        if(min.compareTo(c) > 0) min = c;
        return min;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getSurface() {

        surface = surface.compareTo(UNSET_SURF) == 0 ?
                (new BigDecimal(2)
                        .multiply(
                                (width.multiply(length))
                                        .add(height.multiply(length))
                                        .add(height.multiply(width)))) :
                surface;

        return surface;
    }

    public BigDecimal getExtra() {
        // cache extra value
        extra = extra.compareTo(UNSET_SURF) == 0 ?
                min(length.multiply(width), width.multiply(height), height.multiply(length)) :
                extra;

        return extra;
    }

    public BigDecimal getTotal() {
        // cache total value
        total = total.compareTo(UNSET_SURF) == 0 ? getSurface().add(getExtra()) : total;
        return total;
    }

    public boolean isCubic() {
        return length.compareTo(width) == 0 &&
                width.compareTo(height) == 0;
    }

    public Room(BigDecimal length, BigDecimal width, BigDecimal height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height;
    }

}
