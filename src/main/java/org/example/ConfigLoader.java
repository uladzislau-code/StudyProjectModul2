package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

//TODO Класс который мапит параметры из указанного файла .YAML

public class ConfigLoader {
    private static ConfigLoader instance;
    private final IslandConfig config;

    //TODO получение файла с параметрами

    private ConfigLoader() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            this.config = mapper.readValue(new File("src/main/java/org/example/setting.yaml"), IslandConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить конфигурацию", e);
        }
    }

    // TODO: Реализация Singleton для загрузчика конфигурации

    public static synchronized ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    // TODO: Получение объекта конфигурации

    public IslandConfig getConfig() {
        return config;
    }
}

// TODO: Класс для хранения общей конфигурации острова

class IslandConfig {
    public Map<String, AnimalConfig> animals;
    public PlantConfig plants;
    public IslandParams island;
}

// TODO: Класс для хранения конфигурации животного

class AnimalConfig {
    public String type;
    public double weight;
    public int max_per_cell;
    public int speed;
    public double food_needed;
    public Map<String, Integer> prey;
}

// TODO: Класс для хранения конфигурации растений

class PlantConfig {
    public double weight;
    public int max_per_cell;
    public int growth_rate_per_turn;
}

// TODO: Класс для хранения параметров острова

class IslandParams {
    public int width;
    public int height;
    public int cycle_duration_ms;
}
