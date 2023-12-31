package pongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	Random random;
	int xVolocity;
	int yVolacity;
	int initialSpeed=2;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*initialSpeed);

		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);

	}

	public void setXDirection(int randomXDirection) {
		xVolocity = randomXDirection;
	}

	public void setYDirection(int randomYDirection) {
		yVolacity = randomYDirection;
	}

	public void move() {
		x += xVolocity;
		y += yVolacity;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);
	}
}
