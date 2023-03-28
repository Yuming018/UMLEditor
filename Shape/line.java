package Shape;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;

public abstract class line extends shape{
   protected port[] ports = new port[2];
   protected List<line> lines = new ArrayList<line>();
   private String type = "line";

   public abstract void draw(Graphics g);

   public String gettype() {
      return type;
   }

   public void showport(Graphics g) {
      for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
   }

   public void createport(port port_1, port port_2) {
		this.ports[0] = port_1;
		this.ports[1] = port_2;
	}

   public String inside(Point p){
      Line2D line = new Line2D.Double(x1, y1, x2, y2);
		double distance =  line.ptLineDist(p.getX(), p.getY());
      if(distance < 5){
         return "line";
      }
      else{
         return null;
      }
   }

   public void resetLocation(){
      this.x1 = (int) ports[0].getCenterX();
		this.y1 = (int) ports[0].getCenterY();
		this.x2 = (int) ports[1].getCenterX();
		this.y2 = (int) ports[1].getCenterY();
   }

   public List<line> getline(){
      return lines;
   }

}
