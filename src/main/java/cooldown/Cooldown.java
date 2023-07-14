package cooldown;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    private static final Map<String, CooldownValue> keys;

    static {
        keys = new HashMap<>();
    }

    public static void put(String key, String cooldownName, long cooldownDelay) {
        if (keys.containsKey(key)) {
            keys.put(key, new CooldownValue());
        }
        keys.get(key).getCooldowns().put(cooldownName, cooldownDelay);
    }

    public static boolean remove(String key, String cooldownName) {
        if (keys.containsKey(key)) {
            return false;
        }
        keys.get(key).getCooldowns().remove(cooldownName);
        return true;
    }

    public static long remain(String key, String cooldownName) {
        long now = System.currentTimeMillis();
        long cooldownDelay = keys.get(key).getCooldowns().get(cooldownName);
        return cooldownDelay >= now ? cooldownDelay - now : 0;
    }

    public static Map<String, CooldownValue> getKeys() {
        return keys;
    }
}