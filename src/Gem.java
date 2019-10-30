import java.awt.Font;
import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE, VOID //define the different types of Gems, comma delimited
}

@SuppressWarnings("WeakerAccess")
public class Gem 
{

	private GemType type;
	private int points;

	public Gem() {
		Random r = new Random();
		int val = r.nextInt(3);
		if(val == 0){
			this.type = GemType.BLUE;
		}
		else if(val == 1){
			this.type = GemType.GREEN;
		}
		else {
			this.type = GemType.ORANGE;
		}

		int[] values =  {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
		val = r.nextInt(values.length);
		this.points = values[val];

	}

	public Gem(GemType type, int points) {
		this.type = type;
		this.points = points;
	}

	public GemType getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}

	public void draw(double x, double y){
		StdDraw.picture(x, y, "gem_"+type.toString().toLowerCase()+".png");
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(x, y, points+"");
	}

	@Override
	public String toString() {
		return "Gem{" +
				"type=" + type +
				", points=" + points +
				'}';
	}

	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;

		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
