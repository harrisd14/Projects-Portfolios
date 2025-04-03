package p5; // do not delete
import java.io.*;
import java.util.*;

import graph.Edge;
import graph.Graph;

public class UndirectedGraph {
	Graph G; // Initialize this from within the constructor; the graph must be undirected
	// Do not declare any other graph class inside this class.
	public int numVerts;
	public int numEdges;

	//Scans in data for an undirected graph from file
	public UndirectedGraph(String inputFile) throws FileNotFoundException {
		FileInputStream fileStream = new FileInputStream(inputFile);
		Scanner fileScanner = new Scanner(fileStream);

		numVerts = Integer.parseInt(fileScanner.nextLine());
		numEdges = Integer.parseInt(fileScanner.nextLine());
		G = new Graph(numVerts,false);

		while(fileScanner.hasNextLine()){
			String[] line = fileScanner.nextLine().split(" ");
			int weight = Integer.parseInt(line[2]);
			int source= Integer.parseInt(line[0]);
			int dest= Integer.parseInt(line[1]);
			if (source == dest) {
				throw new IllegalArgumentException("Self-loops are not allowed.");
			}
			if (G.isEdge(source, dest)) {
				throw new IllegalArgumentException("Parallel edges are not allowed.");
			}
			if(weight > 0){
				Edge e = new Edge(source,dest,weight);
				G.addEdge(e);
			}
			else{
				Edge e = new Edge(source,dest);
				G.addEdge(e);
			}


		}
	}

	//Returns a string representation of graph
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int vertex = 0; vertex < G.getVertexCount(); vertex++) {
			sb.append(vertex).append(":");

			// Fetch edges for the current vertex
			List<Edge> neighbors = new ArrayList<>();
			Iterator<Edge> edgeIterator = G.edgeIterator(vertex);

			while (edgeIterator.hasNext()) {
				neighbors.add(edgeIterator.next());
			}

			// Sort neighbors by their destination vertex ids
			neighbors.sort(Comparator.comparingInt(Edge::getDestination));
			for (Edge e : neighbors) {
				sb.append(" <").append(e.getDestination()).append(",").append((int) e.getWeight()).append(">");
			}


			sb.append("\n");
		}

		return sb.toString();
	}

	//Counts number of "triangles" present in graph
	public int countTriplets() {
		int tripletCount = 0;

		//iterate over all triplets
		for (int u = 0; u < G.getVertexCount(); u++) {
			for (int v = u + 1; v < G.getVertexCount(); v++) {
				if (G.isEdge(u, v)) { //check if edge between u and v
					for (int w = v + 1; w < G.getVertexCount(); w++) {
						if (G.isEdge(v, w) && G.isEdge(w, u)) {
							tripletCount++;
						}
					}
				}
			}
		}

		return tripletCount;
	}

	//Returns Breadth First Traversal from initial vertex 0
	//If any vertex is left unvisited (is not a connected graph) returns null
	public String ifConnectedThenBreadthFirstTraversal() {
		boolean[] visited = new boolean[G.getVertexCount()];
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder result = new StringBuilder();

		queue.offer(0);
		visited[0] = true;
		result.append(0);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			List<Edge> neighbors = new ArrayList<>();
			Iterator<Edge> edgeIterator = G.edgeIterator(current);

			while (edgeIterator.hasNext()) {
				neighbors.add(edgeIterator.next());
			}

			neighbors.sort(Comparator.comparingInt(Edge::getDestination));

			for (Edge edge : neighbors) {
				int neighbor = edge.getDestination();
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
					result.append(" ").append(neighbor);  // Append the neighbor to the result
				}
			}
		}

		// Check if all vertices have been visited
		for (boolean visit : visited) {
			if (!visit) {
				return null;
			}
		}

		return result.toString();
	}

	public int findShortestPathLengthBetween(int u, int v) {
		return -1; // dummy statement
	}

	public boolean isTree() {
		if (G.getEdgeCount() != G.getVertexCount() - 1) {
			return false;
		}

		boolean[] visited = new boolean[G.getVertexCount()];
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			Iterator<Edge> edgeIterator = G.edgeIterator(current);
			while (edgeIterator.hasNext()) {
				Edge edge = edgeIterator.next();
				int neighbor = edge.getDestination();

				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}

		for (boolean vertexVisited : visited) {
			if (!vertexVisited) {
				return false;
			}
		}

		return true; // dummy statement

	}
}