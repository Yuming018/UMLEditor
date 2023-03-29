package Mode;

import Shape.shape;
import Shape.line;
import Shape.composition_line;
import Shape.generalization_line;
import Shape.association_line;

import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Point;

public class createline extends mode {
	private String lineType = null;
	private line line = null;
	private shape shape1 = null, shape2 = null;
	private int idx_1 = -1, idx_2 = -1;
	private List<shape> shapes;
	private Point startP = null;

	public createline(String lineType){
		this.lineType = lineType;
	}

	public void mousePressed(MouseEvent e) {
		shapes = canvas.getShape();
		startP = findConnectedObj(e.getPoint(), "origin");
		canvas.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (startP != null){
			line = decideline(startP, e.getPoint());
			canvas.tempLine = line;
			canvas.repaint();
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		Point endP = null;
		if(startP != null){
			endP = findConnectedObj(e.getPoint(), "target");
			if(endP != null){
				line = decideline(startP, endP);
				canvas.addShape(line);
				line.createport(shape1.getport(idx_1), shape2.getport(idx_2));
				shape1.getport(idx_1).addline(line);
				shape2.getport(idx_2).addline(line);
			}
			canvas.tempLine = null;
			canvas.repaint();
			startP = null;
		}
	}

	private Point findConnectedObj(Point p, String target) {
		for(int i=0; i < shapes.size(); i++){
			shape shape = shapes.get(i);
			int idx;
			String judgeInside = shape.inside(p);
			if (judgeInside != null) {
				if (judgeInside == "insideGroup"){
					while(shape.inside(p) == "insideGroup"){
						shape = shape.getSelectedShape();
					}
					idx = Integer.parseInt(shape.inside(p));
				}
				else{
					idx = Integer.parseInt(judgeInside);
				}
				if (target == "origin"){
					shape1 = shape;
					idx_1 = idx;
				}
				else if (target == "target"){
					if (shape1 == shape){
						return null;
					}
					shape2 = shape;
					idx_2 = idx;
				}
				Point point = new Point();
				point.setLocation(shape.getport(idx).getCenterX(), shape.getport(idx).getCenterY());
				return point;
			}
		}
		return null;
	}

	private line decideline(Point startP, Point endP){
		if(lineType.equals("association")){
			return new association_line(startP.x, startP.y , endP.x, endP.y);
		}
		else if(lineType.equals("generalization")){
			return new generalization_line(startP.x, startP.y , endP.x, endP.y);
		}
		else if(lineType.equals("composition")){
			return new composition_line(startP.x, startP.y , endP.x, endP.y);
		}
		return null;
	}
}
