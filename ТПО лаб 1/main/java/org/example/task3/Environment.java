package org.example.task3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Environment {
    private final Set<AbstractObject> abstractObjects = new HashSet<>();

    public void addObject(AbstractObject abstractObject){
        abstractObjects.add(abstractObject);
    }
    public void deleteObject(AbstractObject abstractObject){
        abstractObjects.remove(abstractObject);
    }
    public boolean contains(AbstractObject abstractObject){
        return abstractObjects.contains(abstractObject);
    }
}
