package org.example;

// TODO: Класс Island представляет остров, состоящий из клеток
public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    // TODO: Конструктор для создания острова с заданной шириной и высотой
    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    // TODO: Метод для получения конкретной локации на острове по координатам x и y
    public Location getLocation(int x, int y) {
        return locations[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
