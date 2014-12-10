package il.ac.mta.servlet;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;
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
			Portfolio portfolioCopy= new Portfolio(portfolio);
			Stock[] stocks=portfolioCopy.getStocks();
					
			//make copy of portfolio and change title
			portfolioCopy.setTitle("Low risk stocks");
			resp.getWriter().println(portfolio.getHtmlString());		
			resp.getWriter().println(portfolioCopy.getHtmlString());
			
			//remove last stock
			portfolioCopy.removeStock(stocks[0]);
			resp.getWriter().println(portfolio.getHtmlString());		
			resp.getWriter().println(portfolioCopy.getHtmlString());
			
			//change last stock bid value
			stocks[2].setStockBid(55.55f);
			resp.getWriter().println(portfolio.getHtmlString());		
			resp.getWriter().println(portfolioCopy.getHtmlString());
	}
}
