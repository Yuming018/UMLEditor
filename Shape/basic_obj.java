package Shape;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;
import java.util.ArrayList;

public abstract class basic_obj extends shape {
   protected int width, height; 
   protected String objectName = "";
   protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
   protected port[] ports = new port[4];
   protected List<line> lines = new ArrayList<line>();
   private String type = "obj";

   public abstract void draw(Graphics g);

   public String gettype() {
      return type;
   }

   public void changeName(String name){
		this.objectName = name;
	}

   public void showport(Graphics g){
      for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
   }

   public port getport(int idx){
      return ports[idx];
   }

   public void createport(){
      int[] xpoint = {(x1+x2)/2, x2, (x1+x2)/2, x1};
      int[] ypoint = {y1, (y1+y2)/2, y2, (y1+y2)/2};
      
      for(int i=0; i < ports.length; i++){
         port point = new port();
			point.setpoint(xpoint[i], ypoint[i], 5);
			ports[i] = point;
      }
   }

   public String inside(Point p){
		Point[] points = {new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2)};

		for(int i=0; i < points.length; i++){
			Polygon t = new Polygon();
			int secondIndex = ((i + 1) % 4);
			t.addPoint(points[i].x, points[i].y);
			t.addPoint(points[secondIndex].x, points[secondIndex].y);
			t.addPoint((x1 + x2)/2, (y1 + y2)/2);
			if (t.contains(p)) {
				return Integer.toString(i);
			}
		}
		return null;
	}
   
   public void resetLocation(int moveX, int moveY){
		this.x1 = x1 + moveX;
		this.y1 = y1 + moveY;
      this.x2 = x1 + width;
      this.y2 = y1 + height;
      int[] xpoint = {(x1+x2)/2, x2, (x1+x2)/2, x1};
      int[] ypoint = {y1, (y1+y2)/2, y2, (y1+y2)/2};
      for(int i=0; i < ports.length; i++){
			ports[i].setpoint(xpoint[i], ypoint[i], 5);
         ports[i].resetlines();
      }
   }

   public List<line> getline(){
      for(int i=0; i < ports.length; i++){
         List<line> line = ports[i].getline();
         for(int j=0; j < line.size(); j++){
            lines.add(line.get(j));
         }
      }
      return lines;
   }
   
}
