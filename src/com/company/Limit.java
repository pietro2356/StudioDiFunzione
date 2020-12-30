package com.company;

///NOTA: NON FUNZIONA!!!!!!!
/*
public final class Limit {
    private Limit() {

    }

    public static final double limit(Function function, double approach) {
        double below = Limit.limitFromBelow(function, approach);
        double above = Limit.limitFromAbove(function, approach);
        return below == above ? below : Double.NaN;
    }

    public static final double limitFromBelow(Function function, double approach) {
        for (double d = approach - 10; d <= approach; d = approach
                - ((approach - d) / 10)) {
            if (function.apply(d) == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            } else if (function.apply(d) == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            } else if (Double.isNaN(function.apply(d))) {
                return function.apply(approach + ((approach - d) * 10));
            } else {
                if (d == approach) {
                    return function.apply(d);
                } else if (approach - d < 0.00000000001) {
                    d = approach;
                }

            }
        }
        return Double.NaN;
    }

    public static final double limitFromAbove(Function function, double approach) {
        for (double d = approach + 10; d >= approach; d = approach
                - ((approach - d) / 10)) {
            if (function.apply(d) == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            } else if (function.apply(d) == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            } else if (Double.isNaN(function.apply(d))) {
                return function.apply(approach + ((approach - d) * 10));
            } else {
                if (d == approach) {
                    return function.apply(d);
                } else if (d - approach < 0.00000000001) {
                    d = approach;
                }

            }
        }
        return Double.NaN;
    }
}*/
