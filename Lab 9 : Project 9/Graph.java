/**
 * Di Luo
 * CS 231
 * Project 9
 * Vertex.java
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph{
	private ArrayList<Vertex> graph;

	//constructor
	public Graph(){
		this.graph = new ArrayList<Vertex>();
	}

	//returns the number of vertices in the graph
	public int vertexCount(){
		return graph.size();
	}

	//add a vertex into the graph
	public void add(Vertex v){
		this.graph.add(v);
	}

	// adds v1 and v2 to the graph and adds an edge connecting v1 to v2 
	// via direction dir and a second edge connecting v2 to v1 in the opposite direction, 
	// creating a bi-directional link.
	public void addEdge(Vertex v1, Direction dir, Vertex v2){
		if (!graph.contains(v1)){
			this.graph.add(v1);
		}
		if (!graph.contains(v2)){
			this.graph.add(v2);
		}
		v1.connect(v2,dir);
	}

	// implements a single-source shortest-path algorithm for the Graph, 
	// Dijkstra's algorithm
	public void shortestPath(Vertex v0){
		// Initialize all vertices in G to be unmarked and have infinite cost
		for (int i = 0; i < graph.size(); i++){
			graph.get(i).setMarked(false);
			graph.get(i).setCost((int) Double.POSITIVE_INFINITY);
		}

		// Create a priority queue, q, that orders vertices by lowest cost
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(graph.size());

		// Set the cost of v0 to 0 and add it to q
		v0.setCost(0);
		q.add(v0);

		while(q.size() != 0){
			Vertex v = q.poll();
			v.setMarked(true);
			for (Vertex w : v.getNeighbors()){
				if (w.getMarked() == false && v.getCost()+1 < w.getCost()){
					w.setCost(v.getCost()+1);
					if (q.contains(w)){
						q.remove(w);
					}
					q.offer(w);
				}
			}
		}
	}

	//test function
	public static void main(String[] args){
		Graph g = new Graph();
		Vertex v1 = new Vertex(0,0);
		Vertex v2 = new Vertex(0,0);
		Vertex v3 = new Vertex(0,0);
		Vertex v4 = new Vertex(0,0);
		Vertex v5 = new Vertex(0,0);

		g.addEdge(v1, Direction.South, v2);
		g.addEdge(v2, Direction.East, v3);
		g.addEdge(v3, Direction.South, v5);
		g.addEdge(v3, Direction.East, v4);

		System.out.println(g.vertexCount());
		g.shortestPath(v2);
		System.out.println(v1.getCost());
		System.out.println(v2.getCost());
		System.out.println(v3.getCost());
		System.out.println(v4.getCost());
		System.out.println(v5.getCost());
	}
}



// KeyValuePair comparator, which check the value associated with the stirng
// put the string with the big number in the top part of the heap
class CostComparator implements Comparator<Vertex> {
    public int compare( Vertex i1, Vertex i2 ) {
        int diff = i1.getCost() - i2.getCost();
        if (diff == 0){return 0;}
        if (diff > 0){return -1;}
        else{return 1;}
    }
}