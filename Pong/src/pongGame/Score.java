package pongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int palyer1;
	int palyer2;

	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, 60));
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

		g.drawString(String.valueOf(palyer1/10)+String.valueOf(palyer1%10), (GAME_WIDTH / 2) - 85, 50);
		g.drawString(String.valueOf(palyer2/10)+String.valueOf(palyer2%10), (GAME_WIDTH / 2) + 20, 50);
	}
}
