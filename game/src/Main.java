/*
 * For more details https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 */
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){

		JFrame frame = new JFrame("Block Destroyer"); //Create the frame named Block Destroyer written on top left
		
		//now we will make a new class for the panel which I'm calling it to be a BlockBreakerPanel
		BlockDestroyerPanel panel = new BlockDestroyerPanel();
		
		frame.getContentPane().add(panel);  //create components and put them in the frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true); //to make the frame visible
		frame.setSize(490, 600);
		
		frame.setResizable(false);
	}
}
