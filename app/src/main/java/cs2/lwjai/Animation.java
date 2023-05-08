package cs2.lwjai;

public class Animation {
	public static final class Type {
		public static final int Translation = 0;
		public static final int Dilation = 1;
		public static final int Rotation = 2;
	}

	public static final class Ease {
		public static final int Linear = 0;
		public static final int EaseIn = 1;
		public static final int EaseOut = 2;
		public static final int EaseInOut = 3;
	}

	public int translate_x;
	public int translate_y;

	public double scale;

	public int center_x;
	public int center_y;
	public boolean use_center;

	public double degrees;

	public int type;
	public int ease = Animation.Ease.Linear;

	public int start_tick = -1;
	public int end_tick = -1;

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

	public Animation set_ease(int ease) {
		this.ease = ease;
		return this;
	}

	public Animation set_start_tick(int start_tick) {
		this.start_tick = start_tick;
		return this;
	}

	public Animation set_end_tick(int end_tick) {
		this.end_tick = end_tick;
		return this;
	}

	public Animation set_tick_duration(int tick_duration) {
		if (this.start_tick == -1) {
			this.start_tick = this.end_tick - tick_duration;
		} else if (this.end_tick == -1) {
			this.end_tick = this.start_tick + tick_duration;
		} else {
			System.out.println("W: Animation.set_tick_duration: start_tick and end_tick are already set. Ignoring.");
		}

		return this;
	}

	// boilerplate calculations for easing
	public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	// boilerplate for ease functions
	public static double cubic_bezier(double x1, double y1, double x2, double y2, double t) {
		// Coefficients for the y-axis
		double cy = 3.0 * y1;
		double by = 3.0 * (y2 - y1) - cy;
		double ay = 1.0 - cy - by;
	
		// Calculate the value of t squared and t cubed
		double t_squared = t * t;
		double t_cubed = t_squared * t;
	
		// Calculate the y-coordinate using the cubic-bezier formula
		double y = (ay * t_cubed) + (by * t_squared) + (cy * t);
	
		// Return the y-coordinate
		return y;
	}

	// calculations for easing
	public double linear(long tick, double start_val, double end_val) {
		if (tick<=start_tick) {
			return start_val;
		}

		if (tick >= end_tick) {
			return end_val;
		}

		return ((double) tick-start_tick)*(end_val - start_val)/((double)(end_tick-start_tick))+start_val;
	}

	public int linear(long tick, int start_val, int end_val) {
		return (int) linear(tick, (double) start_val, (double) end_val);
	}

	public int ease_in(long tick, int start_val, int end_val) {
		return (int) ease_in(tick, (double) start_val, (double) end_val);
	}

	public double ease_in(long tick, double start_val, double end_val) {
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

	public int ease_out(long tick, int start_val, int end_val) {
		return (int) ease_out(tick, (double) start_val, (double) end_val);
	}

	public double ease_out(long tick, double start_val, double end_val) {
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

	public int ease_in_out(long tick, int start_val, int end_val) {
		return (int) ease_in_out(tick, (double) start_val, (double) end_val);
	}

	public double ease_in_out(long tick, double start_val, double end_val) {
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

	public int calculate_total(long tick, int start_val, int end_val) {
		return (int) calculate_total(tick, (double) start_val, (double) end_val);
	}

	public double calculate_total(long tick, double start_val, double end_val) {
		switch (this.ease) {
			case Animation.Ease.Linear:
				return linear(tick, start_val, end_val);
			case Animation.Ease.EaseIn:
				return ease_in(tick, start_val, end_val);
			case Animation.Ease.EaseOut:
				return ease_out(tick, start_val, end_val);
			case Animation.Ease.EaseInOut:
				return ease_in_out(tick, start_val, end_val);
			default:
				return ease_in_out(tick, start_val, end_val);
		}
	}

	public double calculate_next(long tick, double start_val, double end_val) {
		return calculate_total(tick, start_val, end_val) - calculate_total(tick-1, start_val, end_val);
	}

	public int calculate_next(long tick, int start_val, int end_val) {
		return (int) calculate_next(tick, (double) start_val, (double) end_val);
	}
}
