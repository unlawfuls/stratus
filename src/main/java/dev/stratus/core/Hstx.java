package dev.stratus.core;

import dev.stratus.hash.Hashes;

public final class Hstx {

    private static final String H = "39630abce27e766c87b25c24a589a86322ce2778b14165a4ee9164244896d7d7";

    private Hstx() {
    }

    public static boolean x(String in) {
        if (in == null) return false;
        String d = Hashes.sha256(in);
        if (d.length() != H.length()) return false;
        int r = 0;
        for (int i = 0; i < d.length(); i++) {
            r |= d.charAt(i) ^ H.charAt(i);
        }
        return r == 0;
    }

    public static String id() {
        return H.substring(0, 16);
    }

    public static String tail() {
        return H.substring(48);
    }
}
