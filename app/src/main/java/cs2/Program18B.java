package cs2;

import java.awt.*;
import java.applet.*;

public class Program18B extends Applet implements Runnable {
	public static class Animation {
		public enum Type {
			Translation,
			Dilation,
			Rotation
		}

		public enum Ease {
			Linear,
			EaseIn,
			EaseOut,
			EaseInOut
		}

		public int translate_x;
		public int translate_y;

		public double scale;

		public int center_x;
		public int center_y;
		public boolean use_center;

		public double degrees;

		public Type type;
		public Ease ease = Animation.Ease.Linear;

		public static Animation translation(int translate_x, int translate_y) {
			Animation a = new Animation();

			a.translate_x = translate_x;
			a.translate_y = translate_y;
			a.type = Animation.Type.Translation;

			return a;
		}

		public static Animation dilation(double scale, int center_x, int center_y) {
			Animation a = new Animation();

			a.scale = scale;
			a.center_x = center_x;
			a.center_y = center_y;
			a.type = Animation.Type.Dilation;
			a.use_center = true;

			return a;
		}

		public static Animation dilation(double scale) {
			Animation a = new Animation();

			a.scale = scale;
			a.type = Animation.Type.Dilation;
			a.use_center = false;

			return a;
		}

		public static Animation rotation(int degrees, int center_x, int center_y) {
			Animation a = new Animation();

			a.degrees = degrees;
			a.center_x = center_x;
			a.center_y = center_y;
			a.type = Animation.Type.Rotation;
			a.use_center = true;

			return a;
		}

		public static Animation rotation(int degrees) {
			Animation a = new Animation();

			a.degrees = degrees;
			a.type = Animation.Type.Rotation;
			a.use_center = false;

			return a;
		}

		public Animation set_ease(Animation.Ease ease) {
			this.ease = ease;
			return this;
		}

		public static double linear(long tick, long start_tick, long end_tick, double start_val, double end_val) {
			if (tick<=start_tick) {
				return start_val;
			}

			if (tick >= end_tick) {
				return end_val;
			}

			return ((double) tick-start_tick)*(end_val - start_val)/((double)(end_tick-start_tick))+start_val;
		}

		public static int linear(long tick, long start_tick, long end_tick, int start_val, int end_val) {
			return (int) linear(tick, start_tick, end_tick, (double) start_val, (double) end_val);
		}

		// boilerplate for ease functions
		public static double cubic_bezier(double x1, double y1, double x2, double y2, double t) {
			// Coefficients for the x-axis
			double cx = 3.0 * x1;
			double bx = 3.0 * (x2 - x1) - cx;
			double ax = 1.0 - cx - bx;
			
			// Coefficients for the y-axis
			double cy = 3.0 * y1;
			double by = 3.0 * (y2 - y1) - cy;
			double ay = 1.0 - cy - by;
			
			// Calculate the value of t squared and t cubed
			double t_squared = t * t;
			double t_cubed = t_squared * t;
			
			// Calculate the result using the cubic-bezier formula
			double result = (ax * t_cubed) + (bx * t_squared) + (cx * t);
			result *= (ay * t_cubed) + (by * t_squared) + (cy * t);
			
			// Return the final result
			return result;
		}

		public static int ease_in(long tick, long start_tick, long end_tick, int start_val, int end_val) {
			return (int) ease_in(tick, start_tick, end_tick, (double) start_val, (double) end_val);
		}

		public static double ease_in(long tick, long start_tick, long end_tick, double start_val, double end_val) {
			if (tick<=start_tick) {
				return start_val;
			}

			if (tick>=end_tick) {
				return end_val;
			}

			// normalize tick
			double t = map(tick, start_tick, end_tick, 0, 1);

			// calculate ease
			double ease = cubic_bezier(0.42, 0, 1, 1, t);

			// calculate value
			return start_val + (end_val - start_val) * ease;
		}

		public static int ease_out(long tick, long start_tick, long end_tick, int start_val, int end_val) {
			return (int) ease_out(tick, start_tick, end_tick, (double) start_val, (double) end_val);
		}

		public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
			return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
		}

		public static double ease_out(long tick, long start_tick, long end_tick, double start_val, double end_val) {
			if (tick<=start_tick) {
				return start_val;
			}

			if (tick>=end_tick) {
				return end_val;
			}

			// normalize tick
			double t = map(tick, start_tick, end_tick, 0, 1);

			// calculate ease
			double ease = cubic_bezier(0, 0, 0.3, 1, t);

			// calculate value
			return start_val + (end_val - start_val) * ease;
		}

		public static int ease_in_out(long tick, long start_tick, long end_tick, int start_val, int end_val) {
			return (int) ease_in_out(tick, start_tick, end_tick, (double) start_val, (double) end_val);
		}

		public static double ease_in_out(long tick, long start_tick, long end_tick, double start_val, double end_val) {
			if (tick<=start_tick) {
				return start_val;
			}

			if (tick>=end_tick) {
				return end_val;
			}

			// normalize tick
			double t = map(tick, start_tick, end_tick, 0, 1);

			// calculate ease
			double ease = cubic_bezier(0.42, 0, 0.58, 1, t);

			// calculate value
			return start_val + (end_val - start_val) * ease;
		}

		public int calculate(long tick, long start_tick, long end_tick, int start_val, int end_val) {
			return (int) calculate(tick, start_tick, end_tick, (double) start_val, (double) end_val);
		}

		public double calculate(long tick, long start_tick, long end_tick, double start_val, double end_val) {
			switch (this.ease) {
				case Linear:
					return Animation.linear(tick, start_tick, end_tick, start_val, end_val);
				case EaseIn:
					return Animation.ease_in(tick, start_tick, end_tick, start_val, end_val);
				case EaseOut:
					return Animation.ease_out(tick, start_tick, end_tick, start_val, end_val);
				case EaseInOut:
					return Animation.ease_in_out(tick, start_tick, end_tick, start_val, end_val);
				default:
					return Animation.ease_in_out(tick, start_tick, end_tick, start_val, end_val);
			}
		}
	}
	
	public static class Polygon {
		public interface PointBuilderLambda {
			public PointBuilder build(PointBuilder pts);
		}

		public interface ToggleShowingLambda {
			public boolean toggle_this_tick(long tick);
		}

		private static class PointBuilder {
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

		private class FullAnimation {
			long start_tick;
			long end_tick;
			Animation animation;
		}
	
		int[] center = new int[]{0, 0};
		int[] original_center = new int[]{0, 0};
		int[] x = new int[0];
		int[] y = new int[0];
		Color color = Color.black;
		boolean filled = true;
		FullAnimation[] animations = new FullAnimation[0];
		Ticker ticker;
		ToggleShowingLambda toggle_showing = (tick) -> { return false; };
		boolean showing = true;
		long last_toggled_tick = 0;

		public static Polygon from(int[] x, int[] y) {
			Polygon p = new Polygon();
			p.x = x;
			p.y = y;
			return p;
		}

		public static Polygon from_points(PointBuilderLambda builder) {
			PointBuilder pts = builder.build(new PointBuilder());
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

		public Polygon add_animation(Animation animation, long tick_duration, long tick_start) {
			if (this.ticker == null) {
				throw new RuntimeException("Cannot add animation without a ticker");
			}
			
			FullAnimation[] new_animations = new FullAnimation[animations.length + 1];
			System.arraycopy(animations, 0, new_animations, 0, animations.length);

			FullAnimation new_animation = new FullAnimation();
			new_animation.start_tick = tick_start;
			new_animation.end_tick = tick_start+tick_duration;
			new_animation.animation = animation;

			new_animations[new_animations.length - 1] = new_animation;

			animations = new_animations;

			return this;
		}

		public Polygon add_blinking(ToggleShowingLambda when) {
			this.toggle_showing = when;
			return this;
		}

		public Polygon add_blinking(int every_x_ticks) {
			this.toggle_showing = (tick) -> {
				return tick% every_x_ticks == 0;
			};

			return this;
		}

		public void draw(Graphics g) {
			if (ticker != null) {
				long ticks = ticker.get_ticks();

				if (toggle_showing.toggle_this_tick(ticks) && last_toggled_tick != ticks) {
					showing = !showing;
					last_toggled_tick = ticks;	
				}
			}

			if (showing) {
				Color old_color = g.getColor();
			
				if (color != null) {
					g.setColor(color);
				}

				for (FullAnimation fa : animations) {
					switch (fa.animation.type) {
						case Translation:
							int translate_x =  fa.animation.calculate(ticker.get_ticks(), fa.start_tick, fa.end_tick, original_center[0], fa.animation.translate_x+original_center[0]);
							int translate_y =  fa.animation.calculate(ticker.get_ticks(), fa.start_tick, fa.end_tick, original_center[1], fa.animation.translate_y+original_center[1]);
							
							g.drawString("translate_x: " + translate_x, 0, 10);
							g.drawString("translate_y: " + translate_y, 0, 20);
							g.drawString("tick: " + ticker.get_ticks(), 0, 30);

							translate(translate_x - original_center[0], translate_y - original_center[1]);
							break;
						case Dilation:
							double scale_l = fa.animation.calculate(ticker.get_ticks(), fa.start_tick, fa.end_tick, 0, fa.animation.scale);
							
							if (fa.animation.use_center) {
								dilate(scale_l, fa.animation.center_x, fa.animation.center_y);
							} else {
								dilate(scale_l);
							}
							break;
						case Rotation:
							double rotate_l = fa.animation.calculate(ticker.get_ticks(), fa.start_tick, fa.end_tick, 0, fa.animation.degrees);
							
							if (fa.animation.use_center) {
								rotate(rotate_l, fa.animation.center_x, fa.animation.center_y);
							} else {
								rotate(rotate_l);
							}
							
							break;
					}
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
			Polygon.from_points((pts) -> {
				return pts
					.next(100, 400)
					.next(100, 220)
					.next(210, 80)
					.next(320, 220)
					.next(320, 400);
			})
				.set_color(Color.orange)
				.draw(g);
		}

		// door
		if (ticker.get_ticks() % 12 < 6) {
			Polygon.from_points((pts) -> {
				return pts
					.next(180, 400)
					.next(180, 320)
					.next(240, 320)
					.next(240, 400);
			})
				.set_color(Color.white)
				.draw(g);
		}

		// window
		if (ticker.get_ticks() % 22 < 11) {
			Polygon.from_points((pts) -> {
				return pts
					.next(120, 240)
					.next(180, 240)
					.next(180, 300)
					.next(120, 300);
			})
				.set_color(Color.black)
				.draw(g);
		}

		// curtain
		Polygon.from_points((pts) -> {
			return pts
				.next(160, 240)
				.next(180, 240)
				.next(180, 300);
		})
			.set_color(new Color(106, 13, 173))
			.draw(g);

		// stop sign
		Polygon.from_points((pts) -> {
			return pts
				.next(430, 320)
				.next_dist(-(10 * Math.sqrt(2)), -(10 * Math.sqrt(2)))
				.next_dist(0, -20)
				.next_dist(10*Math.sqrt(2), -10*Math.sqrt(2))
				.next_dist(20, 0)
				.next_dist(10*Math.sqrt(2), 10*Math.sqrt(2))
				.next_dist(0, 20)
				.next(450, 320);
		})
			.set_color(Color.red)
			.draw(g);

		// home-sweet-home sign
		Polygon.from_points((pts) -> {
			return pts
				.next(20, 240)
				.next(80, 240)
				.next(80, 300)
				.next(20, 300);
		})
			.set_color(Color.green)
			.draw(g);

		// ground rect
		Polygon.from_points((pts) -> {
			return pts
				.next(0, 400)
				.next(0, 500)
				.next(500, 500)
				.next(500, 400);
		})
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

		if (ticker.get_ticks() % 10 == 0) {
			ticker.increase_tps(5);
		}

		if (ticker.get_tps() >= 100) {
			ticker.set_tps(20);
		}
	}
}
