package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigLoader {
    private static ConfigLoader instance;
    private final IslandConfig config;

    private ConfigLoader() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            this.config = mapper.readValue(new File("src/main/java/org/example/setting.yaml"), IslandConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить конфигурацию", e);
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public IslandConfig getConfig() {
        return config;
    }
}

class IslandConfig {
    public Map<String, AnimalConfig> animals;
    public PlantConfig plants;
    public IslandParams island;
}

class AnimalConfig {
    public String type;
    public double weight;
    public int max_per_cell;
    public int speed;
    public double food_needed;
    public Map<String, Integer> prey;
}

class PlantConfig {
    public double weight;
    public int max_per_cell;
    public int growth_rate_per_turn;
}

class IslandParams {
    public int width;
    public int height;
    public int cycle_duration_ms;
}
