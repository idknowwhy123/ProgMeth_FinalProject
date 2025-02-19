package base.component;

import javafx.scene.image.Image;

public class BaseComponent {

	private double x,y;
	private Image baseImage;
	
	public BaseComponent(double x, double y, String strImage) {
		super();
		this.x = x;
		this.y = y;
		this.setBaseImage(new Image(strImage));
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

	public Image getBaseImage() {
		return baseImage;
	}

	public void setBaseImage(Image baseImage) {
		this.baseImage = baseImage;
	}
	
}
