package cs2.lwjai.objects;

import cs2.lwjai.*;
import java.awt.*;

public class Text extends Drawable {
	Ticker ticker;

	String text = "";
	Font font = new Font("TimesRoman", Font.PLAIN, 10);
	Color color = Color.black;
	int x = 0;
	int y = 0;

	int flash_every = -1;
	boolean showing = true;

	Animation[] animations = new Animation[0];

	public Text(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}

	public Text set_font(Font font) {
		this.font = font;
		return this;
	}

	public Text set_color(Color color) {
		this.color = color;
		return this;
	}

	public void use_ticker(Ticker ticker) {
		this.ticker = ticker;
	}

	public Text flash_every(int ticks) {
		flash_every = ticks;
		return this;
	}

	public Text add_animation(Animation animation) {
		if (animation.type == Animation.Type.Rotation) {
			javax.swing.JOptionPane.showMessageDialog(null, "Cannot rotate text");
			throw new IllegalArgumentException("Cannot rotate text");
		}
		
		if (animation.type == Animation.Type.Dilation && animation.use_center) {
			javax.swing.JOptionPane.showMessageDialog(null, "Cannot dilate text with custom center");
			throw new IllegalArgumentException("Cannot dilate text with custom center");
		}

		Animation[] new_animations = new Animation[animations.length + 1];

		for (int i = 0; i < animations.length; i++) {
			new_animations[i] = animations[i];
		}

		new_animations[new_animations.length - 1] = animation;

		return this;
	}

	public void draw(Graphics g) {
		if (flash_every != -1 && ticker != null) {
			if (ticker.get_ticks() % flash_every == 0) {
				showing = !showing;
			}
		}

		if (showing) {
			for (Animation anim : animations) {
				switch (anim.type) {
					case Animation.Type.Translation:
						int translate_x = anim.calculate_next(ticker.get_ticks(), 0, anim.translate_x);
						int translate_y = anim.calculate_next(ticker.get_ticks(), 0, anim.translate_y);

						x += translate_x;
						y += translate_y;
						break;
					case Animation.Type.Dilation:
						double scale = anim.calculate_next(ticker.get_ticks(), 0, anim.scale);
						int fsize = font.getSize();

						font = new Font(font.getName(), font.getStyle(), (int) (fsize * scale));

						break;
				}
			}
		}

		g.setColor(color);
	}
}
