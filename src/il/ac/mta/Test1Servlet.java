package il.ac.mta;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Test1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int num1=3, num2=4, num3=7;
		resp.setContentType("text/plain");
		resp.getWriter().println("Result of (" + num1 + "+" + num2 +")*" + num3 + "=" + (num1+num2)*num3);
	}
}
