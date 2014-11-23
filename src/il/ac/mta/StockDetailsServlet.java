package il.ac.mta;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

public class StockDetailsServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			resp.setContentType("text/html");
			
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			Calendar c3 = Calendar.getInstance();
			c1.set(2014, 10, 15, 0, 0, 0);
			c2.set(2014, 10, 15, 0, 0, 0);
			c3.set(2014, 10, 15, 0, 0, 0);
			
			Stock s1 = new Stock("PIH", (float)12.4, (float)13.1, c1.getTime());
			Stock s2= new Stock("AAL" , (float)5.5 , (float)5.78 , c2.getTime());
			Stock s3= new Stock("CAAS" , (float)31.5 , (float)31.2 , c3.getTime());
			
			resp.getWriter().println(s1.getHtmlDescription());
			resp.getWriter().println(s2.getHtmlDescription());
			resp.getWriter().println(s3.getHtmlDescription());
	}
}
