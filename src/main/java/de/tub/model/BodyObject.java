package de.tub.model;

import com.fasterxml.jackson.databind.JsonNode;

public class BodyObject {
    private JsonNode blueprint;
    private int threads;
    private int delay;
    private int variance;
    private String base;


    public JsonNode getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(JsonNode blueprint) {
        this.blueprint = blueprint;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getVariance() {
        return variance;
    }

    public void setVariance(int variance) {
        this.variance = variance;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "BodyObject{" +
                "blueprint=" + blueprint +
                ", threads=" + threads +
                ", delay=" + delay +
                ", variance=" + variance +
                ", base='" + base + '\'' +
                '}';
    }
}
