package am.smartCode.shop.util;

import jakarta.validation.constraints.NotNull;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static String getCookieValueByName(@NotNull Cookie[] cookies, String name) {
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Cookie getCookieByName(Cookie[] cookies, String name) {
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
