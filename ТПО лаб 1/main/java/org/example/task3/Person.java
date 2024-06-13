package org.example.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Person extends AbstractObject{
    private final List<Things> things = new ArrayList<>();
    private final List<Actions> actions = new ArrayList<>();
    private final String homeTown;
    private Integer minConfident=10;

    public Person(String name,String homeTown, Integer minConfident) {
        super(name);
        this.homeTown = homeTown;
        this.minConfident = minConfident;
    }

    public Integer getMinConfident() {
        return minConfident;
    }

    public Person(String name, String homeTown) {
        super(name);
        this.homeTown = homeTown;
    }
    public void addAction(Actions action){
        actions.add(action);
    }
    public void addThing(Things thing){
        things.add(thing);
    }
    public boolean hasThing(Things thing){
        return things.contains(thing);
    }
    public boolean hasAction(Actions action){
        return actions.contains(action);
    }
    public void allowSmth(Actions action,Things thing){
        if(things.contains(thing)){
            actions.add(action);
            return;
        }
        throw new IllegalArgumentException("no thing");
    }
    public boolean isConfident(Predicate<Environment> environmentPredicate, Environment environment ){
        return environmentPredicate.test(environment);
    }
}
