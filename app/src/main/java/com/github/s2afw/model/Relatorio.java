package com.github.s2afw.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Relatorio implements Serializable {
    private String name;
    private HashMap<String, Object> data;

    public Relatorio() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Relatorio{");
        sb.append("name='").append(name).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relatorio relatorio = (Relatorio) o;
        return Objects.equals(name, relatorio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Relatorio(String name, HashMap<String, Object> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
