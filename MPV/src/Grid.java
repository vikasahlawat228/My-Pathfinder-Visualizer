import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Grid extends JPanel implements MouseListener{

	private int width;
	private int height;

	private int rowHeight;
	private int columnWidth;

	private int rows;
	private int columns;

	private boolean isSearching;
	private boolean isStartPositionable;
	private boolean isWallPositionable;
	private boolean isGoalPositionable;

	private Cell cells[][];
	private Cell startCell;
	private Cell goalCell;
	private Set<Cell> wallCells;

	private Pathfinder pathfinder;

	//Constructs and initialises the Grid object
	public Grid(int width, int height, int rows, int columns){

		this.width = width;
		this.height = height;
		this.wallCells = new HashSet<>();
		this.rows = rows;
		this.columns = columns;
		rowHeight = height / columns;
		columnWidth = width / rows;

		isSearching = false;
		isStartPositionable = true;
		isStartPositionable = false;
		isGoalPositionable = false;

		buildGraph();
		addMouseListener(this);

		this.setPreferredSize(new Dimension(width,height));
	}

	//Start the pathfinding algorithm for this grid
	public void start(int step , int algorithm){
		isSearching = true;
		pathfinder.findShortestPath(startCell, goalCell, wallCells, this, step, algorithm);
	}

	//Refresh the grid and draw any updates to the screen
	public void update(){
		this.repaint();
	}

	//Reset the grid, interrupting any currently running searches
	public void reset(){
		isSearching = false;
		pathfinder.stop();
		pathfinder = new Pathfinder();
		buildGraph();
		this.repaint();
	}

	//Building a graph where each cell is connected to all of its 8 neighbours..
	//Cost of diagonal connections is set slightly more than the horizontal or vertical edges
	private void buildGraph(){
		pathfinder = new Pathfinder();
		cells = new Cell[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j] = new Cell(new Point(i * columnWidth,j * rowHeight),columnWidth,rowHeight);
			}
		}

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(i + 1 < rows)
					cells[i][j].addEdge(new Edge(columnWidth, cells[i + 1][j]));
				if(j + 1 < columns)
					cells[i][j].addEdge(new Edge(rowHeight, cells[i][j + 1]));
				if(i - 1 >= 0)
					cells[i][j].addEdge(new Edge(columnWidth, cells[i - 1][j]));
				if(j - 1 >= 0)
					cells[i][j].addEdge(new Edge(rowHeight, cells[i][j - 1]));
				if(i + 1 < rows && j + 1 < columns)
					cells[i][j].addEdge(new Edge((int)(rowHeight * 1.4), cells[i + 1][j + 1]));
				if(i - 1 >= 0 && j - 1 >= 0)
					cells[i][j].addEdge(new Edge((int)(rowHeight * 1.4), cells[i - 1][j - 1]));
				if(i + 1 < rows && j - 1 >= 0)
					cells[i][j].addEdge(new Edge((int)(rowHeight * 1.4), cells[i + 1][j - 1]));
				if(i - 1 >= 0 && j + 1 < columns)
					cells[i][j].addEdge(new Edge((int)(rowHeight * 1.4), cells[i - 1][j + 1]));

			}
		}

		startCell = cells[0][0];
		startCell.setColor(Color.MAGENTA);
		goalCell = cells[rows -1][columns - 1];
		goalCell.setColor(Color.RED);
//		if(!wallCells.isEmpty()) {
//			for (Cell c : wallCells) {
//				c.setColor(Color.BLACK);
//			}
//		}
		update();

	}

	public void setPositionable(int cellType){
		switch(cellType){
		case 0: {
			isStartPositionable = true;
			isGoalPositionable = false;
			isWallPositionable = false;
			break;
		}
		case 1: {
			isGoalPositionable = true;
			isStartPositionable = false;
			isWallPositionable = false;
			break;
		}
		case 2: {
			isStartPositionable = false;
			isGoalPositionable = false;
			isWallPositionable = true;
		}
		}
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j].draw(g);
			}
		}

	}

	// Handle user clicks on the grid, placing the start or goal cell at the position the user selected
	public void mouseClicked(MouseEvent e){
		if(!isSearching){
			Point mousePos = new Point(e.getX(),e.getY());
			if(isStartPositionable){
				if(startCell != null)
					startCell.setColor(Color.WHITE);
				startCell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
				startCell.setColor(Color.MAGENTA);
			}

			if(isGoalPositionable){
				if(goalCell != null)
					goalCell.setColor(Color.WHITE);
				goalCell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
				goalCell.setColor(Color.RED);
			}
			if(isWallPositionable){
				Cell wallCell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
				wallCell.setColor(Color.BLACK);
				wallCells.add(wallCell);
				update();
			}
			update();
		}
	}

	 public void mouseEntered(MouseEvent e){}

	 public void mouseExited(MouseEvent e){}

	 public void mousePressed(MouseEvent e){}

	 public void mouseReleased(MouseEvent e){}
}
