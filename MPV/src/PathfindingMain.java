import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("unchecked")
public class PathfindingMain{
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				PathfindingFrame frame = new PathfindingFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("My Pathfinding Visualizer");
				frame.setVisible(true);
			}			
		});		
	}
	
}
