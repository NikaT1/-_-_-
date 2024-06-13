package org.example.task3;

public class BagOfCornFlakes extends Things{
    private Integer power=0;
    public BagOfCornFlakes(String name) {
        super(name);
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
