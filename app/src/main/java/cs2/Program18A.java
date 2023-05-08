package cs2;

import java.awt.*;
import java.applet.*;

public class Program18A extends Applet implements Runnable {
	public static class Polygon {

		public static class PointBuilder {
			int[] x = new int[0];
			int[] y = new int[0];

			public PointBuilder next(int x, int y) {
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

			public PointBuilder next_dist(int distx, int disty) {
				return next(x[x.length - 1] + distx, y[y.length - 1] + disty);
			}

			public PointBuilder next_dist(double distx, double disty) {
				return next_dist((int) distx, (int) disty);
			}
		}
	
		int[] center = new int[]{0, 0};
		int[] original_center = new int[]{0, 0};
		int[] x = new int[0];
		int[] y = new int[0];
		Color color = Color.black;
		boolean filled = true;
		Ticker ticker;
		boolean showing = true;
		long last_toggled_tick = 0;
		int toggle_showing = 0;

		public static Polygon from(int[] x, int[] y) {
			Polygon p = new Polygon();
			p.x = x;
			p.y = y;
			return p;
		}

		public static Polygon from_points(PointBuilder pts) {
			Polygon p = new Polygon();
			p.x = pts.x;
			p.y = pts.y;
			return p;
		}

		public static Polygon centered_at(int x, int y) {
			Polygon p = new Polygon();
			p.center = new int[]{x, y};
			p.original_center = new int[]{x, y};
			return p;
		}

		public Polygon equilateral(int vertex_ct, int dist_to_ctr) {
			x = new int[vertex_ct];
			y = new int[vertex_ct];

			double angleinc = 2 * Math.PI / vertex_ct;

			for (int i = 0; i < vertex_ct; i++) {
				double angle = angleinc * i;
				x[i] = (int) (center[0] + dist_to_ctr * Math.cos(angle));
				y[i] = (int) (center[1] + dist_to_ctr * Math.sin(angle));
			}

			return this;
		}

		public Polygon translate(int move_x, int move_y) {
			for (int i = 0; i < x.length; i++) {
				x[i] += move_x;
				y[i] += move_y;
			}

			center[0] += move_x;
			center[1] += move_y;

			return this;
		}

		public Polygon dilate(int scale) {
			return dilate((double) scale);
		}

		public Polygon dilate(double scale) {
			return dilate(scale, center[0], center[1]);
		}

		public Polygon dilate(double scale, int center_x, int center_y) {
			for (int i = 0; i < x.length; i++) {
				x[i] = (int) (center_x + (x[i] - center_x) * scale);
				y[i] = (int) (center_y + (y[i] - center_y) * scale);
			}

			return this;
		}

		public Polygon rotate(int degrees) {
			return rotate((double) degrees);
		}

		public Polygon rotate(double degrees) {
			return rotate(degrees, center[0], center[1]);
		}

		public Polygon rotate(double degrees, int center_x, int center_y) {
			double rad = Math.toRadians(degrees);

			for (int i = 0; i < x.length; i++) {
				// calculate distance between point and (ctrx, ctry)
				double distance = (double) Math.sqrt(Math.pow(x[i] - center_x, 2) + Math.pow(y[i] - center_y, 2));

				// get angle between line connecting points and point
				double angle = Math.atan2(y[i] - center_y, x[i] - center_x) + rad;

				// calculate new point
				x[i] = (int) (center_x + distance * Math.cos(angle));
				y[i] = (int) (center_y + distance * Math.sin(angle));
			}

			return this;
		}

		public Polygon set_color(Color color) {
			this.color = color;
			return this;
		}

		public Polygon set_filled(boolean filled) {
			this.filled = filled;
			return this;
		}

		public Polygon use_ticker(Ticker ticker) {
			this.ticker = ticker;
			return this;
		}

		public Polygon add_blinking(int every_x_ticks) {
			this.toggle_showing = every_x_ticks;
			return this;
		}

		public void draw(Graphics g) {
			if (ticker != null) {
				long ticks = ticker.get_ticks();

				if (ticks % toggle_showing == 0) {
					showing = !showing;
					last_toggled_tick = ticks;	
				}
			}

			if (showing) {
				Color old_color = g.getColor();
			
				if (color != null) {
					g.setColor(color);
				}

				if (filled) {
					g.fillPolygon(x, y, x.length);
				} else {
					g.drawPolygon(x, y, x.length);
				}
				
				g.setColor(old_color);
			}
		}
	}
	
	public static class Ticker {
		int minutes = 0;
		int seconds = 0;
		int ticks = 0;
		int tps = 20;

		public Ticker() {
			this(20);
		}

		public Ticker(int tps) {
			this.tps = tps;
		}

		public void tick() {
			try {
				Thread.sleep(1000 / tps);
				add_tick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void increase_tps(int by) {
			tps += by;
		}

		public void increase_tps() {
			increase_tps(1);
		}

		public void set_tps(int tps) {
			this.tps = tps;
		}

		private void add_tick() {
			ticks++;

			if (ticks == 20) {
				seconds++;
				ticks = 0;
			}

			if (seconds == 60) {
				minutes++;
				seconds = 0;
			}
		}

		public long get_ticks() {
			long i = 0;

			i = i+minutes * 60 * 20;
			i = i+seconds * 20;
			i = i+ticks;

			return i;
		}

		public int get_tps() {
			return tps;
		}
	}

	Thread this_thread = null;
	Ticker ticker = new Ticker();

	public void update(Graphics g) {
		Image offscreen = createImage(500, 500);
		Graphics buffer2 = offscreen.getGraphics();

		buffer2.setColor(getBackground());
		buffer2.fillRect(0, 0, 500, 500);
		buffer2.setColor(getForeground());
		paint(buffer2);

		// debug
		buffer2.setColor(Color.green);
		buffer2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		buffer2.drawString("TPS: " + ticker.get_tps(), 0, 10);
		buffer2.drawString("Ticks: " + ticker.get_ticks(), 0, 20);

		g.drawImage(offscreen, 0, 0, this);
	}

	public void start() {
		this_thread = new Thread(this);
		this_thread.start();
	}

	public void run() {
		while (this_thread != null) {
			ticker.tick();
			repaint();
		}
	}

	/*
	 * ====== END COPY/PASTE ======
	 */

	public void init() {
		setBackground(Color.blue);
	}

	public void paint(Graphics g) {
		Font forStop = new Font("TimesRoman", Font.PLAIN, 16);
		Font forInitials = new Font("Serif", Font.PLAIN, 12);
		Font forHome = new Font("SansSerif", Font.PLAIN, 20);
		Font forSweetHome = new Font("Monospaced", Font.PLAIN, 8);

		// house
		if (ticker.get_ticks() % 10 < 5) {
			Polygon.from_points(
				new Polygon.PointBuilder()
					.next(100, 400)
					.next(100, 220)
					.next(210, 80)
					.next(320, 220)
					.next(320, 400)
			)
				.set_color(Color.orange)
				.draw(g);
		}

		// door
		if (ticker.get_ticks() % 12 < 6) {
			Polygon.from_points(new Polygon.PointBuilder()
					.next(180, 400)
					.next(180, 320)
					.next(240, 320)
					.next(240, 400))
				.set_color(Color.white)
				.draw(g);
		}

		// window
		if (ticker.get_ticks() % 22 < 11) {
			Polygon.from_points(
					new Polygon.PointBuilder()
						.next(120, 240)
						.next(180, 240)
						.next(180, 300)
						.next(120, 300)
				)
				.set_color(Color.black)
				.draw(g);
		}

		// curtain
		Polygon.from_points(new Polygon.PointBuilder()
				.next(160, 240)
				.next(180, 240)
				.next(180, 300))
			.set_color(new Color(106, 13, 173))
			.draw(g);

		// stop sign
		Polygon.from_points(new Polygon.PointBuilder()
				.next(430, 320)
				.next_dist(-(10 * Math.sqrt(2)), -(10 * Math.sqrt(2)))
				.next_dist(0, -20)
				.next_dist(10*Math.sqrt(2), -10*Math.sqrt(2))
				.next_dist(20, 0)
				.next_dist(10*Math.sqrt(2), 10*Math.sqrt(2))
				.next_dist(0, 20)
				.next(450, 320))
			.set_color(Color.red)
			.draw(g);

		// home-sweet-home sign
		Polygon.from_points(new Polygon.PointBuilder()
				.next(20, 240)
				.next(80, 240)
				.next(80, 300)
				.next(20, 300))
			.set_color(Color.green)
			.draw(g);

		// ground rect
		Polygon.from_points(new Polygon.PointBuilder()
				.next(0, 400)
				.next(0, 500)
				.next(500, 500)
				.next(500, 400))
			.set_color(Color.green)
			.draw(g);

		// roof seperator
		// completes:
		// 1. line 1/4
		// 2. color black predefined 2/7
		g.setColor(Color.black);
		g.drawLine(100, 220, 320, 220);

		// doorknob
		// completes:
		// 1. circle 1/1
		g.setColor(Color.black);
		g.fillOval(230, 370, 3, 3);



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

		// stop words
		// completes:
		// 1. font 2/4
		// 2. font color 2/4
		// 3. font size 2/4
		g.setFont(forStop);
		g.setColor(Color.white);
		g.drawString("STOP", 420, 300);

		// ground line
		// completes:
		// 1. line 4/4
		// 2. custom color 4/4
		g.setColor(new Color(0, 0, 0));
		g.drawLine(0, 400, 500, 400);

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
