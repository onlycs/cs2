package cs2.lwjai;

// Angad Tendulkar
// Period 6
// Lightweight Java Applet Interface

import java.applet.*;
import java.awt.*;
import cs2.lwjai.objects.Drawable;

public abstract class LWJAI extends Applet implements Runnable {
    public Ticker ticker = new Ticker();
    
	Thread this_thread = null;
    Drawable.Cache cache = new Drawable.Cache();

    public void update(Graphics g) {
        Image offscreen = createImage(500, 500);
        Graphics buffer2 = offscreen.getGraphics();

        buffer2.setColor(getBackground());
        buffer2.fillRect(0, 0, 500, 500);
        buffer2.setColor(getForeground());
        paint(buffer2);

        // debug
        if (debug()) {
            buffer2.setColor(Color.green);
            buffer2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            buffer2.drawString("TPS: " + ticker.get_tps(), 0, 10);
            buffer2.drawString("Ticks: " + ticker.get_ticks(), 0, 20);
        }

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

    public void init() {
        setBackground(Color.black);
        applet_init();
        declare_drawables(cache);

        for (Drawable drawable : cache.drawables) {
            drawable.use_ticker(ticker);
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        
        cache.draw(g);
    }

	public abstract void applet_init();
    public abstract void declare_drawables(Drawable.Cache cache);
	public abstract boolean debug();
}
