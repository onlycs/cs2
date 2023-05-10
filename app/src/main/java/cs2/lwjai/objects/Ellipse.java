package cs2.lwjai.objects;

import java.awt.*;
import cs2.lwjai.*;

public class Ellipse extends Drawable {
	Color color = Color.black;
	boolean filled = true;
	int x = 0;
	int y = 0;
	int w = 0;
	int h = 0;

	Animation[] animations = new Animation[0];

	int flash_delay = -1;
	boolean showing = true;

	Ticker ticker;

	int[] original_pos = new int[]{0, 0};

	public Ellipse(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public static Ellipse circle(int x, int y, double rad) {
		return new Ellipse(x, y, (int) rad*2, (int) rad*2);
	}

	public static Ellipse circle(int x, int y, int rad) {
		return circle(x, y, (double) rad);
	}

	public Ellipse set_color(Color c) {
		this.color = c;
		return this;
	}

	public Ellipse set_filled(boolean filled) {
		this.filled = filled;
		return this;
	}

	public void use_ticker(Ticker ticker) {
		this.ticker = ticker;
		this.original_pos = new int[]{x, y};
	}

	public Ellipse add_animation(Animation animation) {
		if (animation.type == Animation.Type.Rotation) {
			javax.swing.JOptionPane.showMessageDialog(null, "Cannot rotate ellipse");
			throw new IllegalArgumentException("Cannot rotate ellipse");
		}
		
		if (animation.type == Animation.Type.Dilation && animation.use_center) {
			javax.swing.JOptionPane.showMessageDialog(null, "Cannot dilate ellipse with custom center");
			throw new IllegalArgumentException("Cannot dilate ellipse with custom center");
		}
		
		Animation[] new_animations = new Animation[animations.length + 1];
		
		for (int i = 0; i < animations.length; i++) {
			new_animations[i] = animations[i];
		}

		new_animations[new_animations.length - 1] = animation;
		animations = new_animations;

		return this;
	}

	public Ellipse flash_every(int ticks) {
		flash_delay = ticks;
		return this;
	}

	public Ellipse dilate(int scale) {
		return dilate((double) scale);
	}

	public Ellipse dilate(double scale) {
		w *= scale;
		h *= scale;

		return this;
	}

	public Ellipse translate(int tx, int ty) {
		x += tx;
		y += ty;

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
			for (Animation anim : animations) {
				switch (anim.type) {
					case Animation.Type.Translation:
						int tx = anim.calculate_next(ticker.get_ticks(), original_pos[0], anim.translate_x+original_pos[0]);
						int ty = anim.calculate_next(ticker.get_ticks(), original_pos[1], anim.translate_y+original_pos[1]);

						translate(tx, ty);
						break;
					case Animation.Type.Dilation:
						double scale = anim.calculate_next_dil(ticker.get_ticks(), 0, anim.scale);

						dilate(scale);
						break;
				}
			}
		}

		Color old_color = g.getColor();

		if (color != null) {
			g.setColor(color);
		}

		if (filled) {
			g.fillOval(x, y, w, h);
		} else {
			g.drawOval(x, y, w, h);
		}

		g.setColor(old_color);
	}
}
