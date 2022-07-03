import java.awt.*;
import java.util.*;

public class Vertex implements Comparable<Vertex>{
	protected Point position;
	private double distanceFromStart = Double.POSITIVE_INFINITY;
	private double cost;
	private Vertex predecessor;
	private ArrayList<Edge> edges;

	//2D point vertex where the coordinates are contained by position*
	public Vertex(Point position){
		this.position = position;
		this.distanceFromStart = Double.POSITIVE_INFINITY;
		this.edges = new ArrayList<Edge>();
	}

	//adding edge to the vertex
	public void addEdge(Edge edge){
		edges.add(edge);		
	}

	//Sets the distance of this vertex from the start vertex.

	public void setDistanceFromStart(double distanceFromStart){
		this.distanceFromStart = distanceFromStart;
	}
	//Sets the cost of traversing to vertex.
	public void setCost(double cost){
		this.cost = cost;
	}

	//Sets the predecessor of this vertex.
	public void setPredecessor(Vertex predecessor){
		this.predecessor = predecessor;
	}
	
	//Gets the distance of this vertex from the start vertex.
	public double getDistanceFromStart(){
		return distanceFromStart;
	}

	//Gets the cost of traversing to this vertex.
	public double getCost(){
		return cost;
	}

	//Gets the position of this vertex.
	public Point getPosition(){
		return position;
	}

	//Gets a specified edge connected to this vertex.
	public Edge getEdge(int index){
		return edges.get(index);
	}
	
	//Gets all edges traversable from this node.
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	//Gets the predecessor of this vertex.
	public Vertex getPredecessor(){
		return predecessor;
	}

	//Compares two vertices.
	public int compareTo(Vertex other){
		return Double.compare(cost, other.cost);
	}
	
	//Converts this object to string format.
	@Override
	public String toString(){
		return "Vertex " + position.getX() + ", " + position.getY();
	}
	
}
