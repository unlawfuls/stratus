package dev.stratus.text;

import java.text.Normalizer;
import java.util.Locale;

public final class Slug {

    private Slug() {
    }

    public static String slugify(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        StringBuilder sb = new StringBuilder(normalized.length());
        boolean lastDash = false;
        for (char c : normalized.toLowerCase(Locale.ROOT).toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
                lastDash = false;
            } else if (!lastDash && sb.length() > 0) {
                sb.append('-');
                lastDash = true;
            }
        }
        int len = sb.length();
        if (len > 0 && sb.charAt(len - 1) == '-') sb.setLength(len - 1);
        return sb.toString();
    }

    public static String truncate(String s, int max) {
        if (s == null || s.length() <= max) return s;
        return max <= 3 ? s.substring(0, max) : s.substring(0, max - 3) + "...";
    }
}
