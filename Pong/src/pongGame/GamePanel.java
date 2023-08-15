package pongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIEMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;

	public GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);
		gameThread.start();

	}

	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIEMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIEMETER),
				BALL_DIEMETER, BALL_DIEMETER);

	}

	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	public void checkCollision() {

		// ball out of window
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVolacity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIEMETER) {
			ball.setYDirection(-ball.yVolacity);// last update 50:01

		}

		// bounces ball of paddle
		if (ball.intersects(paddle1)) {

			ball.xVolocity = Math.abs(ball.xVolocity);
			ball.xVolocity++;// option for more diffulty

			if (ball.yVolacity > 0)
				ball.yVolacity++; // option for more diffulty
			else
				ball.yVolacity--;
			ball.setXDirection(ball.xVolocity);
			ball.setYDirection(ball.yVolacity);
		}

		if (ball.intersects(paddle2)) {

			ball.xVolocity = Math.abs(ball.xVolocity);
			ball.xVolocity++;// option for more diffulty

			if (ball.yVolacity > 0)
				ball.yVolacity++; // option for more diffulty
			else
				ball.yVolacity--;
			ball.setXDirection(-ball.xVolocity);
			ball.setYDirection(ball.yVolacity);

		}

		// stops paadle

		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (ball.x <= 0) {
			score.palyer2++;
			newPaddles();
			newBall();

		}

		if (ball.x >= GAME_WIDTH - BALL_DIEMETER) {
			score.palyer1++;
			newPaddles();
			newBall();

		}
	}

	public void run() {
		// game loop
		long lastTime = System.nanoTime();
		double amountTicks = 60.0;
		double ns = 1000000000 / amountTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
