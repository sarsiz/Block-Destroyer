import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle{
	Image pic;  //superclass of all classes that represent graphical Images.
	int dx = 3;
	int dy = -3;
	//in the last part 
	Rectangle left, right;
	boolean powerup = false;
	boolean destroyed = false;
	Block(int a, int b, int w, int h, String s){
		x = a;
		y = b;
		width = w;
		height = h;
		left = new Rectangle(a-1, b, 1, h);
		right = new Rectangle(a+w+1, b, 1, h);
		pic = Toolkit.getDefaultToolkit().getImage(s);

	}

	public void draw(Graphics g, Component c){
		if(!destroyed)
			g.drawImage(pic, x, y, width, height, c);
	}

}
