package org.example.task2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DfsTest {
    private Dfs dfs;

    @BeforeEach
    public void setUp() {
        dfs = new Dfs(8);
        dfs.addEdge(0, 2);
        dfs.addEdge(0, 1);
        dfs.addEdge(0, 4);
        dfs.addEdge(2, 1);
        dfs.addEdge(2, 6);
        dfs.addEdge(1, 0);
        dfs.addEdge(5, 1);
        dfs.addEdge(4, 6);
        dfs.addEdge(4, 7);
        dfs.addEdge(6, 7);
    }

    public static Stream<Arguments> dfsProvider() {
        return Stream.of(
                Arguments.of(0, List.of(0, 1, 2, 4, 6, 7)),
                Arguments.of(1, List.of(0, 1, 2, 4, 6, 7)),
                Arguments.of(2, List.of(0, 1, 2, 4, 6, 7)),
                Arguments.of(3, List.of(3)),
                Arguments.of(4, List.of(4, 6, 7)),
                Arguments.of(5, List.of(0, 1, 2, 4, 5, 6, 7)),
                Arguments.of(6, List.of(6, 7)),
                Arguments.of(7, List.of(7))
        );
    }

    @ParameterizedTest(name = "Index {index} vertex for check {0} expected answer {1}")
    @MethodSource("dfsProvider")
    public void dfs_shouldCorrectlySearchPath(Integer vertex, List<Integer> expectedResult) {
        dfs.dfs(vertex);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    public void dfs_shouldCorrectlySearchPathForEmptyGraph() {
        Dfs dfs = new Dfs(0);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    public void dfs_shouldCorrectlySearchPathForGraphWithOneVertex() {
        Dfs dfs = new Dfs(1);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(Set.of(0).stream().toList().toArray(), actualResult.toArray());
    }

    @Test
    public void dfs_shouldCorrectlySearchPathForGraphWithTwoVertex() {
        Dfs dfs = new Dfs(2);
        dfs.addEdge(0, 1);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(Set.of(0, 1).stream().sorted().toList().toArray(), actualResult.toArray());
    }

    @Test
    public void dfs_shouldCorrectlySearchPathForGraphWithNoEdjes() {
        Dfs dfs = new Dfs(3);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertEquals(1, actualResult.size());
    }
    @Test
    public void dfs_shouldCorrectlySearchPathForFullyConnectedGraph(){
        Dfs dfs = new Dfs(3);
        dfs.addEdge(0, 1);
        dfs.addEdge(1, 0);
        dfs.addEdge(0, 2);
        dfs.addEdge(2, 0);
        dfs.addEdge(1, 2);
        dfs.addEdge(2, 1);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(Set.of(0, 1, 2).stream().sorted().toList().toArray(), actualResult.toArray());
    }
    @Test
    public void dfs_shouldCorrectlySearchPathForGraphWithCycle(){
        Dfs dfs = new Dfs(3);
        dfs.addEdge(0, 1);
        dfs.addEdge(1, 2);
        dfs.addEdge(2, 0);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(Set.of(0, 1, 2).stream().sorted().toList().toArray(), actualResult.toArray());
    }
    @Test
    public void dfs_shouldCorrectlySearchPathForDuplexGraph(){
        Dfs dfs = new Dfs(3);
        dfs.addEdge(0, 1);
        dfs.addEdge(1, 0);
        dfs.addEdge(1, 2);
        dfs.addEdge(2, 1);
        dfs.dfs(0);
        Set<Integer> actualResult = dfs.getVisited();
        Assertions.assertArrayEquals(Set.of(0, 1, 2).stream().sorted().toList().toArray(), actualResult.toArray());
    }


}
