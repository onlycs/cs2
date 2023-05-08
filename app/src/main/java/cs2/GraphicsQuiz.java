package cs2; 

import java.awt.*;
import java.applet.*;

public class GraphicsQuiz extends Applet {
	public static class Points {
		int[] x = new int[0];
		int[] y = new int[0];

		public static Points first(int x, int y) {
			Points pb = new Points();
			pb.x = new int[] { x };
			pb.y = new int[] { y };
			return pb;
		}

		public Points next(int x, int y) {
			int[] newx = new int[this.x.length + 1];
			int[] newy = new int[this.y.length + 1];

			for (int i = 0; i < this.x.length; i++) {
				newx[i] = this.x[i];
				newy[i] = this.y[i];
			}

			newx[newx.length - 1] = x;
			newy[newy.length - 1] = y;

			this.x = newx;
			this.y = newy;

			return this;
		}

		public Points next_dist(int distx, int disty) {
			return next(x[x.length - 1] + distx, y[y.length - 1] + disty);
		}

		public Points next_dist(double distx, double disty) {
			return next_dist((int) distx, (int) disty);
		}

		public int[][] last(int x, int y) {
			return next(x, y).build();
		}

		public int[][] build() {
			return new int[][] { x, y };
		}

		public void draw(Graphics g) {
			g.fillPolygon(x, y, x.length);
		}
	}

	public void init() {
		setBackground(Color.blue);
	}

	public void paint(Graphics g) {
		Points bat_body = Points
			.first(90, 50)
			.next(115, 50)
			.next(115,300)
			.next(150,300)
			.next(150,350)
			.next(50,350)
			.next(50,300)
			.next(90,300);

		Points bat_arm_stripe = Points
			.first(90, 250)
			.next(115, 250)
			.next(115,275)
			.next(90,275);

		Points bat_head_stripe_left = Points
			.first(60, 300)
			.next(75, 300)
			.next(75,350)
			.next(60,350);

		Points bat_head_stripe_right = Points
			.first(120, 350)
			.next(140, 350)
			.next(140,300)
			.next(120,300);

		int[] red_ball = {175, 300};
		int[] yellow_ball = {390, 300};

		Points sign_shape = Points
			.first(200, 150)
			.next(175, 150)
			.next(175, 200)
			.next(200, 200)
			.next(225, 225)
			.next(325, 225)
			.next(350, 200)
			.next(375, 200)
			.next(375, 150)
			.next(350, 150)
			.next(325, 125)
			.next(225, 125);


		
		// set color brown
		g.setColor(new Color(139, 69, 19));
		bat_body.draw(g);

		
		// set color light brown
		g.setColor(new Color(205, 133, 63));

		Points.first(90, 50)
			.next(115, 50)
			.next(115, 300)
			.next(90, 300)
			.draw(g);

		g.setColor(Color.red);
		bat_arm_stripe.draw(g);
		bat_head_stripe_left.draw(g);
		bat_head_stripe_right.draw(g);
		g.fillOval(red_ball[0], red_ball[1], 50, 50);

		

		g.setColor(Color.yellow);
		g.fillOval(yellow_ball[0], yellow_ball[1], 50, 50);

		sign_shape.draw(g);

		g.setColor(Color.white);
		g.drawOval(350, 250, 75, 200);

		g.setColor(Color.blue);
		Points.first(350, 360)
			.next(450, 360)
			.next(450, 500)
			.next(350, 500)
			.draw(g);

		g.setColor(Color.white);
		g.setFont(new Font("Monospaced", Font.BOLD, 25));
		g.drawString("SHAKER", 230, 120);
		g.drawString("BISON", 240, 245);

		g.setColor(Color.blue);
		g.setFont(new Font("Serif", Font.ITALIC, 30));
		g.drawString("Croquet Team", 190, 185);
	}
}
