package com.github.simaodiazz.simplifier.cooldown;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    // Mapa estático que armazena os cooldowns por chave
    private static final Map<String, CooldownValue> keys;

    static {
        keys = new HashMap<>();
    }

    /**
     * Adiciona um novo cooldown para a chave especificada.
     *
     * @param id            Chave única para identificar o cooldown
     * @param cooldownName  Nome do cooldown
     * @param cooldownDelay Tempo de espera do cooldown em milissegundos
     */
    public static void put(String id, String cooldownName, long cooldownDelay) {
        // Obtém ou cria um novo CooldownValue para a chave e adiciona o cooldown
        keys.computeIfAbsent(id, (key) -> new CooldownValue()).getCooldowns().put(cooldownName, cooldownDelay);
    }

    /**
     * Remove um cooldown específico da chave.
     *
     * @param key           Chave que identifica o cooldown
     * @param cooldownName  Nome do cooldown a ser removido
     * @return true se o cooldown foi removido com sucesso, false se a chave não existir
     */
    public static boolean remove(String key, String cooldownName) {
        // Obtém o CooldownValue para a chave e remove o cooldown pelo nome
        CooldownValue cooldownValue = keys.get(key);
        if (cooldownValue == null) {
            return false;
        }
        cooldownValue.getCooldowns().remove(cooldownName);
        return true;
    }

    /**
     * Obtém o tempo restante em milissegundos para um cooldown específico da chave.
     *
     * @param key           Chave que identifica o cooldown
     * @param cooldownName  Nome do cooldown
     * @return o tempo restante em milissegundos para o cooldown, ou 0 se a chave não existir ou o cooldown já tiver expirado
     */
    public static long remain(String key, String cooldownName) {
        // Obtém o CooldownValue para a chave e verifica o tempo restante do cooldown
        CooldownValue cooldownValue = keys.get(key);
        if (cooldownValue == null) {
            return 0;
        }
        long now = System.currentTimeMillis();
        long cooldownDelay = cooldownValue.getCooldowns().getOrDefault(cooldownName, 0L);
        return cooldownDelay >= now ? cooldownDelay - now : 0;
    }

    /**
     * Obtém o mapa de chaves e seus respectivos CooldownValue.
     *
     * @return o mapa de chaves e seus cooldowns
     */
    public static Map<String, CooldownValue> getKeys() {
        return keys;
    }
}
