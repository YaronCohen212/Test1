package il.ac.mta;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Task4 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
			//start here
			resp.setContentType("text/html");
			resp.getWriter().println("<h1>Task under construction</h1>");
	}
}
