package Shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public abstract class shape {
   protected int x1, y1, x2, y2;
   public boolean group_selected = false;
   
   public shape() {
      
   }
   public int getx1(){
      return x1;
   }
   public int getx2(){
      return x2;
   }
   public int gety1(){
      return y1;
   }
   public int gety2(){
      return y2;
   }
   
   public abstract void draw(Graphics g);
   public abstract void showport(Graphics g);
   public abstract String inside(Point p);
   public abstract String gettype();
   public abstract List<line> getline();
   public void resetLocation(int moveX, int moveY){} //object
   public void resetLocation(){} //line
   public void changeName(String name){} //object
   public port getport(int idx){
      return null;
   }
   //group
   public void resetSelectedShape() {}
   public shape getSelectedShape() {
      return null;
   }
   public List<shape> getShapes() {
		return null;
	}
}
