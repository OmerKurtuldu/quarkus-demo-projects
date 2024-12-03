package org.gib.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SquareRequest {
    @JsonProperty("number")
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
