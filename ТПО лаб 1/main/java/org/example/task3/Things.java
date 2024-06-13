package org.example.task3;

import java.util.ArrayList;
import java.util.List;

public class Things extends AbstractObject{
    private final List<String> characteristics = new ArrayList<>();


    public Things(String name) {
        super(name);
    }
    public void addCharacteristics(String characteristic){
        characteristics.add(characteristic);
    }
}
