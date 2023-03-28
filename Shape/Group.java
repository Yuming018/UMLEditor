package Shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;
import java.awt.Color;

public class Group extends shape {

   private List<shape> shapes = new ArrayList<shape>();
   private Rectangle bounds = new Rectangle();
   private shape selectedShape = null;
   protected List<line> lines = new ArrayList<line>();
   protected String type = "Group";

   public void draw(Graphics g){
      for (int i = 0; i < shapes.size(); i++) {
			shape shape = shapes.get(i);
			shape.draw(g);
		}
   }

   public String gettype(){
      return type;
   }

   public void changeName(String name) {
      for (int i = 0; i < shapes.size(); i++) {
			shape shape = shapes.get(i);
         shape.changeName(name);
		}
	}

   public void showport(Graphics g){
      g.setColor(new Color(100, 100, 100, 50));
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
      g.setColor(new Color(200, 200, 200));
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
      g.setColor(Color.white);
      for (int i = 0; i < shapes.size(); i++) {
         shape shape = shapes.get(i);
         shape.showport(g);
      }
   }

   public String inside(Point p){
      for(int i = 0; i < shapes.size(); i++){
         shape shape = shapes.get(i);
         String judgeInside = shape.inside(p);
         if (judgeInside != null) {
				selectedShape = shape;
				return "insideGroup";
			}
      }
      return null;
   }

   public shape getSelectedShape() {
		return selectedShape;
	}

   public void resetLocation(int moveX, int moveY) {
      for(int i=0; i<shapes.size(); i++){
         shape shape = shapes.get(i);
         shape.resetLocation(moveX, moveY);
      }
		resetbound(moveX, moveY);
	}

   public void setBounds() {

      int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;
      
      for (int i = 0; i < shapes.size(); i++) {
			shape shape = shapes.get(i);
			if (shape.getx1() < leftX) {
				leftX = shape.getx1();
			}
			if (shape.getx2() > rightX) {
				rightX = shape.getx2();
			}
			if (shape.gety1() < upY) {
				upY = shape.gety1();
			}
			if (shape.gety2() > bottomY) {
				bottomY = shape.gety2();
			}
		}
      
      Point upLeftP = new Point(leftX, upY);
      Point bottomRightP = new Point(rightX, bottomY);
      bounds.setBounds(upLeftP.x, upLeftP.y, Math.abs(upLeftP.x - bottomRightP.x),
				Math.abs(upLeftP.y - bottomRightP.y));

      x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
   }

   protected void resetbound(int moveX, int moveY) {
		bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}

   public void addShapes(shape shape) {
		shapes.add(shape);
	}

   public List<shape> getShapes() {
		return shapes;
	}

   public List<line> getline(){
      for(int i=0; i<shapes.size(); i++){
         shape shape = shapes.get(i);
         if(shape.gettype() == "obj"){
            for(int j=0; j<4; j++){
               List<line> line = shape.getport(j).getline();
               for(int k=0; k < line.size(); k++){
                  lines.add(line.get(k));
               }
            }
         }
         else if (shape.gettype() == "Group"){
            List<line> line = shape.getline();
            for(int j = 0; j < line.size(); j++){
               lines.add(line.get(j));
            }
         }
      }
      return lines;
   }
}
