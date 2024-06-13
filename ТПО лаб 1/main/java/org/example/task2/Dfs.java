package org.example.task2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dfs {
    private final List<List<Integer>> vertex;
    private final Set<Integer> visited = new HashSet<>();

    public Dfs(Integer dimension) {
        this.vertex = new ArrayList<>(dimension);
        for (int i = 0; i < dimension; i++) {
            vertex.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        vertex.get(from).add(to);
    }

    public void dfs(int n) {
        if(vertex.isEmpty()){
            return;

        }
        visited.add(n);
        for (Integer child : vertex.get(n)) {
            if (!visited.contains(child)) {
                dfs(child);
            }
        }
    }

    public Set<Integer> getVisited() {
        return visited;
    }
}
