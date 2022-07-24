package com.github.s2afw.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Relatorio implements Serializable {
    private String nome;
    private HashMap<String, Float> data;

    public Relatorio() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Relatorio{");
        sb.append("name='").append(nome).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relatorio relatorio = (Relatorio) o;
        return Objects.equals(nome, relatorio.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Relatorio(String name, HashMap<String, Float> data) {
        this.nome = name;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String, Float> getData() {
        return data;
    }

    public void setData(HashMap<String, Float> data) {
        this.data = data;
    }
}
