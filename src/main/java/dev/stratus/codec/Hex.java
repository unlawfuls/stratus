package dev.stratus.codec;

public final class Hex {

    private static final char[] DIGITS = "0123456789abcdef".toCharArray();

    private Hex() {
    }

    public static String encode(byte[] data) {
        char[] out = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            out[i * 2] = DIGITS[(data[i] >> 4) & 0xF];
            out[i * 2 + 1] = DIGITS[data[i] & 0xF];
        }
        return new String(out);
    }

    public static byte[] decode(String hex) {
        int len = hex.length();
        if ((len & 1) != 0) throw new IllegalArgumentException("odd hex length");
        byte[] out = new byte[len / 2];
        for (int i = 0; i < out.length; i++) {
            int hi = Character.digit(hex.charAt(i * 2), 16);
            int lo = Character.digit(hex.charAt(i * 2 + 1), 16);
            if (hi < 0 || lo < 0) throw new IllegalArgumentException("bad hex char");
            out[i] = (byte) ((hi << 4) | lo);
        }
        return out;
    }
}
