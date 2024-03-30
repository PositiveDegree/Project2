import java.util.*;
import java.io.*;
public class WeightedGraph {
    // weighted edge structure  - consists of source, destination and weight
    static class WeightedEdge {
        int start;
        int destination;
        int weight;

        // constructor
        public WeightedEdge(int start, int destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices; // number of vertices
        LinkedList<WeightedEdge>[] adj; // linkedlist rep of adjacency list

        // constructor
        Graph(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // adding edge, with weight
        public void addEdge(int start, int destination, int weight) {
            WeightedEdge edge = new WeightedEdge(start, destination, weight);
            adj[start].addFirst(edge);
        }
    }

}