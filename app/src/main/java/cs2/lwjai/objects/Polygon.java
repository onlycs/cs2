package cs2.lwjai.objects;

import java.awt.*;
import cs2.lwjai.*;

public class Polygon extends Drawable {
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
	
	Animation[] animations = new Animation[0];
	Ticker ticker;
	
	int flash_delay = -1;
	boolean showing = true;

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

	public Polygon rotate(double degrees) {
		return rotate(degrees, center[0], center[1]);
	}

	public Polygon rotate(double degrees, int center_x, int center_y) {
		double rad = Math.toRadians(degrees);

		for (int i = 0; i < x.length; i++) {
			// calculate distance between point and (ctrx, ctry)
			double distance = Math.sqrt(Math.pow(x[i] - center_x, 2) + Math.pow(y[i] - center_y, 2));

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

	public void use_ticker(Ticker ticker) {
		this.ticker = ticker;
		this.original_center = this.center;
	}

	public Polygon add_animation(Animation animation) {
		Animation[] new_animations = new Animation[animations.length + 1];
		
		for (int i = 0; i < animations.length; i++) {
			new_animations[i] = animations[i];
		}

		new_animations[new_animations.length - 1] = animation;
		animations = new_animations;

		return this;
	}

	public Polygon flash_every(int ticks) {
		this.flash_delay = ticks;
		return this;
	}

	public void draw(Graphics g) {
		if (ticker != null && flash_delay != -1) {
			long ticks = ticker.get_ticks();

			if (ticks % flash_delay == 0) {
				showing = !showing; 
			}
		}

		if (showing) {
			Color old_color = g.getColor();
			
			if (color != null) {
				g.setColor(color);
			}
			
			for (Animation anim : animations) {
				switch (anim.type) {
					case Animation.Type.Translation:
						int translate_x =  anim.calculate_next(ticker.get_ticks(), original_center[0], anim.translate_x+original_center[0]);
						int translate_y =  anim.calculate_next(ticker.get_ticks(), original_center[1], anim.translate_y+original_center[1]); 

						translate(translate_x, translate_y);
						break;
					case Animation.Type.Dilation:
						double scale = anim.calculate_next_dil(ticker.get_ticks(), 0, anim.scale);
						
						if (anim.use_center) {
							dilate(scale, anim.center_x, anim.center_y);
						} else {
							dilate(scale);
						}
						break;
					case Animation.Type.Rotation:
						double rotate = anim.calculate_next(ticker.get_ticks(), 0, anim.degrees);
						
						if (anim.use_center) {
							rotate(rotate, anim.center_x, anim.center_y);
						} else {
							rotate(rotate);
						}
						
						break;
					default:
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