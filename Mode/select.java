package Mode;

import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Point;
import java.awt.*;

import Shape.shape;

public class select extends mode {
	private List<shape> shapes;
	private Point startP = null;
	private String judgeInside = null;
	
	public void mousePressed(MouseEvent e) {
		startP = e.getPoint();
		shapes = canvas.getShape();

		canvas.reset();

		for (int i = shapes.size() - 1; i >= 0; i--) {
			shape shape = shapes.get(i);
			judgeInside = shape.inside(startP);
			if (judgeInside != null) {
				canvas.selectedObj = shape;
				break;
			}
		}
		canvas.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startP.x;
		int moveY = e.getY() - startP.y;
		if (canvas.selectedObj != null){
			canvas.selectedObj.resetLocation(moveX, moveY);
			startP.x = e.getX();
			startP.y = e.getY();
		}
		else{
			if (e.getX() > startP.x && e.getY() > startP.y)
				canvas.SelectedArea.setBounds(startP.x, startP.y, Math.abs(moveX), Math.abs(moveY));
			else if (e.getX() < startP.x && e.getY() < startP.y)
				canvas.SelectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));
			else if (e.getX() > startP.x && e.getY() < startP.y)
				canvas.SelectedArea.setBounds(startP.x, e.getY(), Math.abs(moveX), Math.abs(moveY));
			else if (e.getX() < startP.x && e.getY() > startP.y)
				canvas.SelectedArea.setBounds(e.getX(), startP.y, Math.abs(moveX), Math.abs(moveY));
		}
		canvas.repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		for(int i=0; i < shapes.size(); i++){
			shape shape = shapes.get(i);
			if(canvas.checkSelectedArea(shape)){
				shape.group_selected = true;
			}
		}
		canvas.SelectedArea = new Rectangle();
		canvas.repaint();
	}
}
