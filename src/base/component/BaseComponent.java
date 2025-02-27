package base.component;

public class BaseComponent {

	private double x,y;
	private int hp;
	
	public BaseComponent(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.setHp(3);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void getHp(int hp) {
		this.hp = hp;
	}
	
}
