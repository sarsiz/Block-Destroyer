/*
 * Press enter to start the game
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockDestroyerPanel extends JPanel implements KeyListener{
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate anime;
	int size = 25;
	BlockDestroyerPanel(){
		paddle = new Block(175, 480, 150, 25, "paddle.png");
		for(int i=0; i<8; i++){
			blocks.add(new Block((i*60+2), 0, 60, 25, "blue.png"));	
		}
		for(int i=0; i<8; i++){
			blocks.add(new Block((i*60+2), 25, 60, 25, "red.png"));	
		}
		for(int i=0; i<8; i++){
			blocks.add(new Block((i*60+2), 50, 60, 25, "green.png"));	
		}
		for(int i=0; i<8; i++){
			blocks.add(new Block((i*60+2), 75, 60, 25, "blue.png"));	
		}
		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup  = true;
		blocks.get(random.nextInt(32)).powerup  = true;
		blocks.get(random.nextInt(32)).powerup  = true;
		blocks.get(random.nextInt(32)).powerup  = true;
		blocks.get(random.nextInt(32)).powerup  = true;
		ball.add(new Block(237,437,25,25, "ball.png"));
		addKeyListener(this);
		setFocusable(true); //whithout this it won't be able to read the keys
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g); //erase the screen and then reprint
		for(Block b : blocks){
			b.draw(g, this);
		}
		for(Block b : ball){
			b.draw(g, this);
		}
		for(Block b : powerup){
			b.draw(g, this);
		}
		paddle.draw(g, this);
	}

	public void update() {
		for(Block p: powerup){
			p.y+=1;
			if(p.intersects(paddle) && !p.destroyed){
				p.destroyed = true;
				ball.add(new Block(paddle.dx+75, 437,25, 25, "ball.png")); //half the paddle width
			}
		}
		for(Block ba : ball){
			ba.x+=ba.dx;
			if(ba.x > (getWidth()-size) && ba.dx>0 || ba.x < 0)
				ba.dx*=-1;
			if(ba.y < 0 || ba.intersects(paddle))
				ba.dy*=-1;
			ba.y+=ba.dy;
			for(Block b : blocks){ //to check if the ball interscts
				if((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed){
					ba.dx*=-1;
					b.destroyed = true;
					if(b.powerup){
						powerup.add(new Block(b.x, b.y, 25, 19, "grey.png"));
					}
				}
				else if(ba.intersects(b) && !b.destroyed){  //if block is not destroyed and it intersects with the ball...then DESTROYYY
					b.destroyed = true;
					ba.dy*=-1; 
					if(b.powerup){
						powerup.add(new Block(b.x, b.y, 25, 19, "grey.png"));
					}
				}
			}
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			anime = new Animate(this);
			thread = new Thread(anime);
			thread.start();
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0){
			paddle.x-=10;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x <(getWidth()-paddle.width)){
			paddle.x+=10;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
