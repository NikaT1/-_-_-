package org.example.task3;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractObject{
    private final List<Actions> actions = new ArrayList<>();
    private final List<String> characteristics = new ArrayList<>();
    public Animal(String name) {
        super(name);
    }
    public void addAction(Actions action){
        actions.add(action);
    }
    public void addCharacteristic(String characteristic){
        characteristics.add(characteristic);
    }
}
