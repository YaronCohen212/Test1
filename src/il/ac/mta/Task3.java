package il.ac.mta;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Task3 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				resp.setContentType("text/html");//sub task 1
				
				int rad=50;
				float area=rad*(float)Math.pow(Math.PI,2);
				resp.getWriter().println("<h1>Area of circle with radius " + rad + "cm is: " + area +" square-cm.</h1>");
				//sub task 2
				int angleDeg=30,hypotenuseLen=50;
				float oppositeLen=(float) (Math.sin(Math.toRadians(angleDeg))*hypotenuseLen);
				resp.getWriter().println("<h1>Length of opposite angle B is " + angleDeg + "&deg and hypotenuse length is "+ oppositeLen +" cm.</h1>");
				//sub task 3
				int base=20,power=13;
				resp.getWriter().println("<h1>" + base + "<sup>" + power + "</sup>=" + (int)Math.pow(base,power) +"</h1>");
	}
}
