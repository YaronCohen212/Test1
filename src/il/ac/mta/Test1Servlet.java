package il.ac.mta;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Test1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int num1=3, num2=4, num3=7,res;
		res=(num1+num2)*num3;
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Result of (" + num1 + "+" + num2 +")*" + num3 + "=" + res + "</h1>");
	}
}
