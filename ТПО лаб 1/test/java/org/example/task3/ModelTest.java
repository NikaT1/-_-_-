package org.example.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {
    private Person fedor;
    private Person artur;
    private Bottle bottle;
    private Animal fish;
    private Environment environment;
    private Things underware;
    private Things matress;
    private BagOfCornFlakes bagOfCornFlakes;

    @BeforeEach
    public void setUp() {
        environment = new Environment();
        fedor = new Person("Fedor", "Earth");
        bottle = new Bottle("bottle");
        bottle.addCharacteristics("glass");
        fish = new Animal("fish");
        fish.addAction(new Actions("swim"));
        fish.addAction(new Actions("shimmer"));
        fish.addCharacteristic("small");
        fish.addCharacteristic("yellow");
        environment.addObject(fedor);
        bottle.addEntrail(fish);
        fedor.addThing(bottle);
        fedor.addAction(new Actions("want..."));
        fedor.addAction(new Actions("allow"));
        underware = new Things("underware");
        environment.addObject(underware);
        matress = new Things("matress");
        environment.addObject(matress);
        artur = new Person("artur", "betelgeuse");
        artur.addAction(new Actions("look at fedor"));
        artur.addAction(new Actions("blink"));
        environment.addObject(artur);
        bagOfCornFlakes = new BagOfCornFlakes("bag of corn flakes");
    }

    @Test
    public void isConfident_shouldReturnTrueIfAllConditionsChecked() {
        environment.addObject(bagOfCornFlakes);
        bagOfCornFlakes.setPower(100);
        boolean actualResult = artur.isConfident(
                (environment1) -> environment1.contains(underware)
                        && environment1.contains(matress)
                        && environment1.contains(fedor)
                        && environment1.contains(bagOfCornFlakes)
                        && bagOfCornFlakes.getPower()>artur.getMinConfident(),
                environment
        );
        environment.deleteObject(bagOfCornFlakes);
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void isConfident_shouldReturnFalseIfNotAllConditionsChecked() {
        boolean actualResult = artur.isConfident(
                (environment1) -> environment1.contains(underware)
                        && environment1.contains(matress)
                        && environment1.contains(fedor)
                        && environment1.contains(bagOfCornFlakes),
                environment
        );
        Assertions.assertFalse(actualResult);
    }
    @Test
    public void isConfident_shouldReturnFalseIfBagOfCornFlakesHasNotALotConfident() {
        bagOfCornFlakes.setPower(0);
        boolean actualResult = artur.isConfident(
                (environment1) -> environment1.contains(underware)
                        && environment1.contains(matress)
                        && environment1.contains(fedor)
                        && environment1.contains(bagOfCornFlakes)
                        && bagOfCornFlakes.getPower()<artur.getMinConfident(),
                environment
        );
        Assertions.assertFalse(actualResult);
    }

    @Test
    public void addThing_shouldAddThingToPerson() {
        Assertions.assertTrue(fedor.hasThing(bottle));
    }

    @Test
    public void addAction_shouldAddActionToPerson() {
        Assertions.assertTrue(fedor.hasAction(new Actions("want...")));
    }

    @Test
    public void allowSmth_shouldAddActionIfThingExist() {
        fedor.allowSmth(new Actions("allow"), bottle);
    }

    @Test
    public void allowSmth_shouldThrowExceptionIfThingDoesntExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> fedor.allowSmth(new Actions("allow"), new Things("bottle2")));
    }

    @Test
    public void addThing_shouldCorrectlyAddSameThing() {
        environment.addObject(bottle);
        environment.addObject(bottle);
        Assertions.assertTrue(environment.contains(bottle));
    }
    @Test
    public void deleteObject_shouldCorrectlyDeleteObject() {
        Things things = new Things("d");
        environment.deleteObject(things);
        Assertions.assertFalse(environment.contains(things));
    }
    @Test
    public void deleteObject_shouldCorrectlyWorkWithNull() {
        environment.deleteObject(null);
    }
    @Test
    public void addObject_shouldCorrectlyWorkWithNull() {
        environment.addObject(null);
    }
}
