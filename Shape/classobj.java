package Shape;
import java.awt.Graphics;

public class classobj extends basic_obj{
   
   public classobj(int x1, int y1) {
      	this.width = 100;
		this.height = 120;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		createport();
   }
   
   public void draw(Graphics g){
      	g.drawRect(x1, y1, width, height);

      	int portion = height / 3;
		g.drawLine(x1, y1 + portion, x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);

      	int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(font);	
		g.drawString(objectName, x1 + (int)empty, y1 + 25);
   }
}
