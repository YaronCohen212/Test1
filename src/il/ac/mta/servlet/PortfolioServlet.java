package il.ac.mta.servlet;

import il.ac.mta.model.Portfolio;
import il.ac.mta.service.PortfolioService;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Displays the information about stock portfolio in HTML
 *  
 * @author Yaron_Cohen
 * @since JDK 7
 */
@SuppressWarnings("serial")
public class PortfolioServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			resp.setContentType("text/html");
			
			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio = portfolioService.getProtfolio();

			resp.getWriter().println(portfolio.getHtmlString());		
	}
}
