package cs2.lwjai.objects;

import java.awt.*;
import cs2.lwjai.Animation;
import cs2.lwjai.Ticker;

public abstract class Drawable {
	public abstract void draw(Graphics g);
	public abstract void use_ticker(Ticker ticker);
	public abstract Drawable flash_every(int ticks);
	public abstract Drawable add_animation(Animation animation);

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
