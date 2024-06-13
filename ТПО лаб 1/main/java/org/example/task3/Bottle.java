package org.example.task3;

import java.util.ArrayList;
import java.util.List;

public class Bottle extends Things{
    private final List<AbstractObject> entrails = new ArrayList<>();
    public Bottle(String name) {
        super(name);
    }
    public void addEntrail(AbstractObject abstractObject){
        entrails.add(abstractObject);
    }
}
