package cs2;

import java.applet.Applet;
import java.awt.*;

// Angad Tendulkar
// Program 16
// Period 6

// This program will be the applet project
// 500x500

public class Program16 extends Applet {
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


	public static int[][] equalateral(int centerx, int centery, int sidect, int pointDistToCenter, Graphics g) {
		int[] xpts = new int[sidect];
		int[] ypts = new int[sidect];

		double angleinc = 2 * Math.PI / sidect;

		for (int i = 0; i < sidect; i++) {
			double angle = angleinc * i;
			xpts[i] = (int) (centerx + pointDistToCenter * Math.cos(angle));
			ypts[i] = (int) (centery + pointDistToCenter * Math.sin(angle));
		}

		return new int[][] { xpts, ypts };
	}

	public static void rotate(int[] x, int[] y, int ctrx, int ctry, int deg) {
		double rad = Math.toRadians(deg);

		for (int i = 0; i < x.length; i++) {
			// calculate distance between point and (ctrx, ctry)
			int distance = (int) Math.sqrt(Math.pow(x[i] - ctrx, 2) + Math.pow(y[i] - ctry, 2));

			// get angle between line connecting points and point
			double angle = Math.atan2(y[i] - ctry, x[i] - ctrx) + rad;

			// calculate new point
			x[i] = (int) (ctrx + distance * Math.cos(angle));
			y[i] = (int) (ctry + distance * Math.sin(angle));
		}
	}

	public void init() {
		setBackground(Color.blue);
	}

	public void paint(Graphics g) {
		Font forStop = new Font("TimesRoman", Font.PLAIN, 16);
		Font forInitials = new Font("Serif", Font.PLAIN, 12);
		Font forHome = new Font("SansSerif", Font.PLAIN, 20);
		Font forSweetHome = new Font("Monospaced", Font.PLAIN, 8);

		Points house = Points
			.first(100, 400)
			.next(100, 220)
			.next(210, 80)
			.next(320, 220)
			.next(320, 400);

		Points door = Points
			.first(180, 400)
			.next(180, 320)
			.next(240, 320)
			.next(240, 400);

		Points window = Points
			.first(120, 240)
			.next(180, 240)
			.next(180, 300)
			.next(120, 300);

		Points curtain = Points
			.first(160, 240)
			.next(180, 240)
			.next(180, 300);

		Points stopsign = Points
			.first(430, 320)
			.next_dist(-(10 * Math.sqrt(2)), -(10 * Math.sqrt(2)))
			.next_dist(0, -20)
			.next_dist(10*Math.sqrt(2), -10*Math.sqrt(2))
			.next_dist(20, 0)
			.next_dist(10*Math.sqrt(2), 10*Math.sqrt(2))
			.next_dist(0, 20)
			.next(450, 320);

		Points sign = Points
			.first(20, 240)
			.next(80, 240)
			.next(80, 300)
			.next(20, 300);

		Points ground = Points
			.first(0, 400)
			.next(0, 500)
			.next(500, 500)
			.next(500, 400);

		// house
		// completes:
		// 1. other polygon
		// 2. color orange predefined 1/7
		g.setColor(Color.orange);
		house.draw(g);

		// roof seperator
		// completes:
		// 1. line 1/4
		// 2. color black predefined 2/7
		g.setColor(Color.black);
		g.drawLine(100, 220, 320, 220);

		// door
		// completes:
		// 1. rectangle 1/1
		// 2. color white predefined 3/7
		g.setColor(Color.white);
		door.draw(g);

		// doorknob
		// completes:
		// 1. circle 1/1
		g.setColor(Color.black);
		g.fillOval(230, 370, 3, 3);


		// window
		// completes:
		// 1. square 1/1
		window.draw(g);
		
		// curtain
		// completes:
		// 1. triangle 1/1
		// 2. custom color 1/4
		g.setColor(new Color(106, 13, 173));
		curtain.draw(g);

		// pole
		// completes:
		// 1. line 2/4
		// 2. color yellow predefined 4/7
		g.setColor(Color.yellow);
		g.drawLine(210, 80, 210, 40);

		// oval at top
		// completes:
		// 1. oval 1/1
		// 2. custom color 2/4
		// 3. transparent 1/1
		g.setColor(new Color(205, 127, 50, 175));
		g.fillOval(190, 20, 40, 20);

		// at in oval
		// completes:
		// 1. font 1/4
		// 2. font color 1/4
		// 3. font size 1/4
		g.setColor(Color.black);
		g.setFont(forInitials);
		g.drawString("AT", 200, 35);

		// stop sign pole
		// completes:
		// 1. line 3/4
		// 2. custom color 3/4
		g.setColor(new Color(61, 59, 59));
		g.drawLine(440, 400, 440, 320);

		// stop sign
		// completes:
		// 1. stop sign 1/1
		// 2. predefined color red 5/7
		g.setColor(Color.red);
		stopsign.draw(g);

		// stop words
		// completes:
		// 1. font 2/4
		// 2. font color 2/4
		// 3. font size 2/4
		g.setFont(forStop);
		g.setColor(Color.white);
		g.drawString("STOP", 420, 300);

		// ground
		// completes:
		// 1. predefined color green 6/7
		g.setColor(Color.green);
		ground.draw(g);

		// ground line
		// completes:
		// 1. line 4/4
		// 2. custom color 4/4
		g.setColor(new Color(0, 0, 0));
		g.drawLine(0, 400, 500, 400);

		// sign
		g.setColor(Color.green);
		sign.draw(g);

		// home
		// completes:
		// 1. font 3/4
		// 2. font color 3/4
		// 3. font size 3/4
		// 4. color pink predefined 7/7
		g.setColor(Color.pink);
		g.setFont(forHome);
		g.drawString("HOME", 20, 260);

		// sweet home
		// completes:
		// 1. font 4/4
		// 2. font color 4/4
		// 3. font size 4/4
		g.setColor(Color.red);
		g.setFont(forSweetHome);
		g.drawString("SWEET HOME", 20, 280);

		// line connecting sign
		g.setColor(Color.black);
		g.drawLine(80, 260, 100, 260);
	}
}
