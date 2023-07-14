package com.github.simaodiazz.simplifier.cooldown;

import java.util.HashMap;
import java.util.Map;

public class CooldownValue {

    private Map<String, Long> cooldowns;

    public CooldownValue() {
        this.cooldowns = new HashMap<>();
    }

    public Map<String, Long> getCooldowns() {
        return cooldowns;
    }

    public void setCooldowns(HashMap<String, Long> cooldowns) {
        this.cooldowns = cooldowns;
    }
}
