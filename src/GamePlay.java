import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;

	private int lengthofsnake = 3;

	private Timer timer;
	private int delay = 150; // speed of snake // user will get choice

	private ImageIcon snakeimage;
	private ImageIcon titleImage;

	private int moves = 0;

	private int[] foodxposition = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425,
			450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] foodyposition = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
			500, 525, 550, 575, 600, 625 };

	private ImageIcon foodimage;

	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);

	private int score = 0;

	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {

		if (moves == 0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;

			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;

		}



		
		// draw the border for playing area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// draw the background for gameplay
		g.setColor(Color.lightGray);
		g.fillRect(25, 75, 850, 575);

		// draw the snake Image

		rightmouth = new ImageIcon("head_right.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

		// draw scores
	

		for (int i = 0; i < lengthofsnake; i++) {
			if (i == 0 && right) {

				rightmouth = new ImageIcon("head_right.png");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && left) {

				leftmouth = new ImageIcon("head_left.png");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);

			}
			if (i == 0 && up) {

				upmouth = new ImageIcon("head_up.png");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);

			}
			if (i == 0 && down) {

				downmouth = new ImageIcon("head_down.png");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);

			}
			if (i != 0) {
				snakeimage = new ImageIcon("snakebody.png");
				snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);

			}
		}

		foodimage = new ImageIcon("apple.png");
		if (foodxposition[xpos] == snakexlength[0] && foodyposition[ypos] == snakeylength[0]) {
			lengthofsnake++;
			score++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		foodimage.paintIcon(this, g, foodxposition[xpos], foodyposition[ypos]);

		for (int i = 1; i < lengthofsnake; i++) {
			if (snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER", 300, 300);

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press SPACE to play again", 350, 340);

				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.CENTER_BASELINE, 35));
				g.drawString("Your score: " + score, 400, 400);

			}
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (right) {
			for (int i = lengthofsnake - 1; i >= 0; i--) {
				snakeylength[i + 1] = snakeylength[i];

			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if (i == 0) {
					snakexlength[i] = snakexlength[i] + 25;
				} else {
					snakexlength[i] = snakexlength[i - 1];
				}
				if (snakexlength[i] > 850) {
					snakexlength[i] = 25;
				}
			}
			repaint();
		}
		if (left) {
			for (int i = lengthofsnake - 1; i >= 0; i--) {
				snakeylength[i + 1] = snakeylength[i];

			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if (i == 0) {
					snakexlength[i] = snakexlength[i] - 25;
				} else {
					snakexlength[i] = snakexlength[i - 1];
				}
				if (snakexlength[i] < 25) {
					snakexlength[i] = 850;
				}
			}
			repaint();
		}
		if (up) {
			for (int i = lengthofsnake - 1; i >= 0; i--) {
				snakexlength[i + 1] = snakexlength[i];

			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if (i == 0) {
					snakeylength[i] = snakeylength[i] - 25;
				} else {
					snakeylength[i] = snakeylength[i - 1];
				}
				if (snakeylength[i] < 75) {
					snakeylength[i] = 625;
				}
			}
			repaint();
		}
		if (down) {
			for (int i = lengthofsnake - 1; i >= 0; i--) {
				snakexlength[i + 1] = snakexlength[i];

			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if (i == 0) {
					snakeylength[i] = snakeylength[i] + 25;
				} else {
					snakeylength[i] = snakeylength[i - 1];
				}
				if (snakeylength[i] > 625) {
					snakeylength[i] = 75;
				}
			}
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			rightmouth = new ImageIcon("head_right.png");
			repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			down = false;
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				right = true;
				left = false;
			}
			down = false;
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				down = true;
				up = false;
			}
			left = false;
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				up = true;
				down = false;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
