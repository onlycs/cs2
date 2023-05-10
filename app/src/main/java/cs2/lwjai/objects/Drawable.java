package cs2.lwjai.objects;

import java.awt.*;
import cs2.lwjai.Animation;
import cs2.lwjai.Ticker;

public class Drawable {
	public void draw(Graphics g) {
		javax.swing.JOptionPane.showMessageDialog(null, "the -- void draw(Graphics g) -- method MUST be extended");
		throw new UnsupportedOperationException("the -- void draw(Graphics g) -- method MUST be extended");
	}

	public void use_ticker(Ticker ticker) {
		javax.swing.JOptionPane.showMessageDialog(null, "the -- void use_ticker(Ticker ticker) -- method MUST be extended");
		throw new UnsupportedOperationException("the -- void use_ticker(Ticker ticker) -- method MUST be extended");
	}

	public Drawable flash_every(int ticks) {
		javax.swing.JOptionPane.showMessageDialog(null, "the -- Drawable flash_every(int ticks) -- method MUST be extended");
		throw new UnsupportedOperationException("the -- Drawable flash_every(int ticks) -- method MUST be extended");
	}

	public Drawable add_animation(Animation animation) {
		return this;
	}

	public void add_to(Drawable.Cache cache) {
		cache.add(this);
	}


	public static class Cache {
		public Drawable[] drawables = new Drawable[0];

        public void add(Drawable drawable) {
            Drawable[] new_drawables = new Drawable[drawables.length + 1];
            System.arraycopy(drawables, 0, new_drawables, 0, drawables.length);
            new_drawables[new_drawables.length - 1] = drawable;
            drawables = new_drawables;
        }

        public void draw(Graphics g) {
            for (Drawable drawable : drawables) {
                drawable.draw(g);
            }
        }
        
        public int len() {
            return drawables.length;
        }
        
        public Drawable at(int i) {
            return drawables[i];
        }
	}
}
