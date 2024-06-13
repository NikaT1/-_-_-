package org.example.task1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TangImplTest {

    public static Stream<Arguments> tanProvider() {
        return Stream.of(
                Arguments.of(1d, 0.00001, Math.tan(1)),
                Arguments.of(0d, 0.00001, Math.tan(0)),
                Arguments.of(-1d, 0.00001, Math.tan(-1)),
                Arguments.of(1d, 0.00001, Math.tan(1+2*Math.PI)),
                Arguments.of(1d, 0.00001, -Math.tan(-1)),
                Arguments.of(2.4d, 0.00001, Math.tan(2.4)),
                Arguments.of(Math.PI, 0.00001, Math.tan(Math.PI)),
                Arguments.of(Math.PI + 2*Math.PI, 0.00001, Math.tan(Math.PI)),
                Arguments.of(Math.PI/2, 0.00001, Math.tan(Math.PI/2))
        );
    }
    public static Stream<Arguments> tanIncorrectProvider() {
        return Stream.of(
                Arguments.of(Double.POSITIVE_INFINITY, 0.00001, Math.tan(Double.POSITIVE_INFINITY)),
                Arguments.of(Double.NEGATIVE_INFINITY, 0.00001, Math.tan(Double.NEGATIVE_INFINITY))
        );
    }
    public static Stream<Arguments> tanExperimentProvider() {
        List<Arguments> arguments = new ArrayList<>();
        double current = Math.PI/2;

        for (int i = 0; i < 100; i++) {
            arguments.add(Arguments.of(current, 0.00001, Math.tan(current)));
            System.out.println(current);
            current = current+Math.ulp(current);
        }
        return arguments.stream();
    }

    @ParameterizedTest(name = "Index {index}, arg {0} expected {2}")
    @MethodSource("tanProvider")
    public void tan_shouldCorrectlyCalculateTangens(Double x, Double n, Double expectedAnswer) {
        double actualAnswer = TangImpl.tan(x, n);
        Assertions.assertEquals(expectedAnswer , actualAnswer , 0.0001);
    }

    @ParameterizedTest()
    @MethodSource("tanIncorrectProvider")
    public void tan_shouldCorrectlyCalculateTangensForIncorrectData(Double x, Double n, Double expectedAnswer) {
        double actualAnswer = TangImpl.tan(x, n);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @ParameterizedTest
    @MethodSource("tanExperimentProvider")
    public void tan_experimentAroundHalfOdPi(Double x, Double n, Double expectedAnswer) {
        double actualAnswer = TangImpl.tan(x, n);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
}
