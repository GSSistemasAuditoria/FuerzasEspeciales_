package com.auditorias.fuerzasespeciales.ui.main.ui.busqueda.modela;

public class DataModal {
    int level;
    String name;

    public DataModal(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
