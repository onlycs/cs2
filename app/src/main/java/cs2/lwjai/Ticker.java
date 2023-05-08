package cs2.lwjai;

public class Ticker {
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

		i = i + minutes * 60 * 20;
		i = i + seconds * 20;
		i = i + ticks;

		return i;
	}

	public int get_tps() {
		return tps;
	}
}
