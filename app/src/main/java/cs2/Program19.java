package cs2;

import java.awt.*;

import cs2.lwjai.*;
import cs2.lwjai.objects.*;
import cs2.lwjai.objects.Polygon;

public class Program19 extends LWJAI {
    public boolean debug() {
        return true;
    }

    public void applet_init() {
        setBackground(Color.blue);
    }

    public void declare_drawables(Drawable.Cache cache) {
		
		
		// ground
		Polygon
			.from_points(
				new Polygon.PointBuilder()
					.next(0, 400)
					.next(0, 500)
					.next(500, 500)
					.next(500, 400)
			)
			.set_color(Color.green)
			.set_filled(true)
			.add_to(cache);

		// house
        Polygon
            .from_points(
				new Polygon.PointBuilder()
					.next(100, 400)
					.next(100, 220)
					.next(210, 80)
					.next(320, 220)
					.next(320, 400)
			)
			.set_color(Color.orange)
			.set_filled(true)
			.add_to(cache);

		// door
		Polygon
			.from_points(
				new Polygon.PointBuilder()
					.next(180, 400)
					.next(180, 320)
					.next(240, 320)
					.next(240, 400)
			)
			.set_color(Color.white)
			.set_filled(true)
			.add_to(cache);

		// doorknob
		Ellipse
			.circle(230, 370, 3)
			.set_color(Color.black)
			.set_filled(true)
			.add_to(cache);

		// window
		Polygon
			.centered_at(150, 270)
			.equilateral(4, (int)(30 * Math.sqrt(2)))
			.rotate(45)
			.set_color(Color.black)
			.set_filled(true)
			.add_to(cache);
		
		// curtain
		Polygon
			.from_points(
				new Polygon.PointBuilder()
					.next(160, 240)
					.next(180, 240)
					.next(180, 300)
			)
			.set_color(new Color(106, 13, 173, 1))
			.set_filled(true)
			.add_animation(
				Animation
					.translation(-40, 0)
					.set_start_tick(20)
					.set_tick_duration(20)
					.set_ease(Animation.Ease.EaseInOut)
			)
			.add_to(cache);
			
		// side sign pole
		new Line()
			.set_startpoint(80, 260)
			.set_endpoint(100, 260)
			.set_color(Color.black)
			.add_to(cache);

		// side sign
		Polygon
			.centered_at(50, 270)
			.equilateral(4, (int)(30*Math.sqrt(2)))
			.rotate(45)
			.set_color(Color.green)
			.set_filled(true)
			.add_to(cache);

		// side sign text
		new Text("HOME", 20, 260)
			.set_font(new Font("SansSerif", Font.PLAIN, 20))
			.set_color(Color.pink)
			.add_to(cache);

		new Text("SWEET HOME", 20, 280)
			.set_font(new Font("Monospaced", Font.PLAIN, 8))
			.set_color(Color.red)
			.add_to(cache);

		// roof seperator
		new Line()
			.set_startpoint(100, 220)
			.set_endpoint(320, 220)
			.set_color(Color.black)
			.add_to(cache);

		// pole
		new Line()
			.set_startpoint(210, 80)
			.set_endpoint(210, 40)
			.set_color(Color.yellow)
			.add_to(cache);

		// top sign
		new Ellipse(190, 20, 40, 20)
			.set_color(new Color(205, 127, 50, 175))
			.set_filled(true)
			.add_to(cache);

		// top sign text
		new Text("AT", 200, 35)
			.set_color(Color.black)
			.set_font(new Font("Serif", Font.PLAIN, 12))
			.add_to(cache);

		// stop sign pole
		new Line()
			.set_startpoint(440, 400)
			.set_endpoint(440, 320)
			.set_color(new Color(61, 59, 59))
			.add_to(cache);

		// stop sign
		Polygon
			.centered_at(440, 300)
			.equilateral(8, 25)
			.rotate(((8-2)*180)/8/2)
			.set_color(Color.red)
			.set_filled(true)
			.add_to(cache);

		// stop words
		new Text("STOP", 420, 305)
			.set_color(Color.white)
			.set_font(new Font("TimesRoman", Font.PLAIN, 16))
			.add_to(cache);

		Animation car_animation = Animation
			.translation(600, 0)
			.set_start_tick(5)
			.set_tick_duration(100)
			.set_ease(Animation.Ease.EaseIn);

		//car
		Polygon
			.from_points(
				new Polygon.PointBuilder()
					.next(0, 405)
					.next(100, 405)
					.next(75, 350)
					.next(0, 350)
					.next(0, 405)
			)
			.set_color(Color.red)
			.add_animation(car_animation)
			.add_to(cache);

		// car wheels
		Ellipse
			.circle(30, 405, 5)
			.add_animation(car_animation)
			.add_to(cache);
		
		Ellipse
			.circle(70, 405, 5)
			.add_animation(car_animation)
			.add_to(cache);

    }
}
