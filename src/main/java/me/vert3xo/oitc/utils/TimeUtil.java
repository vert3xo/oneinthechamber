package me.vert3xo.oitc.utils;

public class TimeUtil {
    public static String makeReadable(int seconds) {
        return String.format("%d:%02d", seconds / 60, (seconds % 60));
    }
}
