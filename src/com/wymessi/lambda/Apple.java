package com.wymessi.lambda;

/**
 * Created by wy on 2019/1/27
 */
public class Apple {
    private String color;
    private Double weight;

    Apple(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

