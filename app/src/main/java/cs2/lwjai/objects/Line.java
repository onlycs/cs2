package cs2.lwjai.objects;

import java.awt.*;
import cs2.lwjai.*;

public class Line extends Drawable {
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;

	Color color = Color.black;
	Animation[] animations = new Animation[0];

	int flash_every = -1;
	boolean showing = true;

	Ticker ticker;

	int[] original_point1 = new int[]{0, 0};


	public Line set_startpoint(int x, int y) {
		x1 = x;
		y1 = y;
		return this;
	}

	public Line set_endpoint(int x, int y) {
		x2 = x;
		y2 = y;
		return this;
	}

	public Line set_color(Color color) {
		this.color = color;
		return this;
	}

	public void use_ticker(Ticker ticker) {
		this.ticker = ticker;
		this.original_point1[0] = x1;
		this.original_point1[1] = x2;
	}

	public Line flash_every(int ticks) {
		flash_every = ticks;
		return this;
	}

	public Line add_animation(Animation animation) {
		Animation[] new_animations = new Animation[animations.length + 1];

		for (int i = 0; i < animations.length; i++) {
			new_animations[i] = animations[i];
		}

		new_animations[new_animations.length - 1] = animation;

		animations = new_animations;

		return this;
	}

	private int[] center() {
		return new int[]{(x1 + x2) / 2, (y1 + y2) / 2};
	}

	public void translate(int x, int y) {
		x1 += x;
		y1 += y;
		x2 += x;
		y2 += y;
	}

	public void rotate(double degrees) {
		rotate(degrees, center()[0], center()[1]);
	}

	public void rotate(double degrees, int center_x, int center_y) {
		double radians = Math.toRadians(degrees);

		int new_x1 = (int) Math.round((x1 - center_x) * Math.cos(radians) - (y1 - center_y) * Math.sin(radians) + center_x);
		int new_y1 = (int) Math.round((x1 - center_x) * Math.sin(radians) + (y1 - center_y) * Math.cos(radians) + center_y);

		int new_x2 = (int) Math.round((x2 - center_x) * Math.cos(radians) - (y2 - center_y) * Math.sin(radians) + center_x);
		int new_y2 = (int) Math.round((x2 - center_x) * Math.sin(radians) + (y2 - center_y) * Math.cos(radians) + center_y);

		x1 = new_x1;
		y1 = new_y1;
		x2 = new_x2;
		y2 = new_y2;
	}

	public void dilate(double scale) {
		dilate(scale, center()[0], center()[1]);
	}

	public void dilate(double scale, int center_x, int center_y) {
		int new_x1 = (int) Math.round(center_x + (x1 - center_x) * scale);
		int new_y1 = (int) Math.round(center_y + (y1 - center_y) * scale);

		int new_x2 = (int) Math.round(center_x + (x2 - center_x) * scale);
		int new_y2 = (int) Math.round(center_y + (y2 - center_y) * scale);

		x1 = new_x1;
		y1 = new_y1;
		x2 = new_x2;
		y2 = new_y2;
	}

	public void draw(Graphics g) {
		if (ticker != null && flash_every != -1) {
			long ticks = ticker.get_ticks();

			if (ticks % flash_every == 0) {
				showing = !showing; 
			}
		}

		if (showing) {
			for (Animation anim : animations) {
				switch (anim.type) {
					case Animation.Type.Translation:
						int translate_x =  anim.calculate_next(ticker.get_ticks(), original_point1[0], anim.translate_x);
						int translate_y =  anim.calculate_next(ticker.get_ticks(), original_point1[1], anim.translate_y);
					
						translate(translate_x, translate_y);
						break;
					case Animation.Type.Dilation:
						double scale = anim.calculate_next_dil(ticker.get_ticks(), 1, anim.scale);

						if (anim.use_center) {
							dilate(scale, anim.center_x, anim.center_y);
						} else {
							dilate(scale);
						}	

						break;
					case Animation.Type.Rotation:
						double degrees = anim.calculate_next(ticker.get_ticks(), 0, anim.degrees);

						if (anim.use_center) {
							rotate(degrees, anim.center_x, anim.center_y);
						} else {
							rotate(degrees);
						}
						
						break;
				}
			}

			Color old_color = g.getColor();

			if (color != null) {
				g.setColor(color);
			}

			g.drawLine(x1, y1, x2, y2);

			g.setColor(old_color);
		}
	}
}
