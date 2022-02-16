package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.model.AsiakasTyyppi;

public class Visualisointi extends Canvas implements IVisualisointi{

	private GraphicsContext gc;
	
	double i = 0;
	double j = 10;
	
	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}

	public void tyhjennaNaytto() {
		this.i = 0;
		this.j = 10;
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void uusiAsiakas(AsiakasTyyppi asiakasTyyppi) {
		if (AsiakasTyyppi.X.equals(asiakasTyyppi)) {
			gc.setFill(Color.GREEN);
		} else {
			gc.setFill(Color.RED);
		}	
		gc.fillOval(i,j,10,10);
		
		//i = (i + 10 % this.getWidth());
		i += 10;
		//j = (j + 12) % this.getHeight();
		if (i >= this.getWidth()) {
			i = 0;
			j+=10;			
		}
		
		if (j >= this.getHeight()) {
			j = 10;
		}
	}

	
}
