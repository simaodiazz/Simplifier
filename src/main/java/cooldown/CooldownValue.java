package cooldown;

import java.util.HashMap;

public class CooldownValue {

    private HashMap<String, Long> cooldowns;

    public CooldownValue() {
        this.cooldowns = new HashMap<>();
    }

    public HashMap<String, Long> getCooldowns() {
        return cooldowns;
    }

    public void setCooldowns(HashMap<String, Long> cooldowns) {
        this.cooldowns = cooldowns;
    }
}
