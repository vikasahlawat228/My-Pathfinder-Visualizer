public class Edge {
	private int cost;	
	private Vertex destination;
	
	//Constructs a weighted edge to connect two vertices together.
	public Edge(int cost, Vertex destination){
		this.cost = cost;
		this.destination = destination;
	}
	
	//Gets the cost of traversing this edge.
	public int getCost(){
		return cost;
	}
	
	//Gets the destination vertex of this edge
	public Vertex getDestination(){
		return destination;
	}	
	
	//sets the cost of traversing this edge.
	public void setCost(int cost){
		this.cost = cost;
	}
}
