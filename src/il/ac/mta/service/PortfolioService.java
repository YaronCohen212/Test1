package il.ac.mta.service;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;

import java.util.Calendar;
/**
 * More information will be announced later
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class PortfolioService {
	public Portfolio getProtfolio(){
		
		Portfolio myPortfolio = new Portfolio();;
		Stock[] myStock=myPortfolio.getStocks();
		myPortfolio.setTitle("High risk stocks");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		
		c1.set(2014, 10, 15, 0, 0, 0);
		c2.set(2014, 10, 15, 0, 0, 0);
		c3.set(2014, 10, 15, 0, 0, 0);
		
		myStock[0].setStock("PIH", (float)12.4, (float)13.1, c1.getTime());
		myStock[1].setStock("AAL", (float)5.5, (float)5.78, c2.getTime());
		myStock[2].setStock("CAAS", (float)31.5, (float)31.2, c3.getTime());
		myPortfolio.setPortfolioSize(3);
		
		return myPortfolio;
	}
}
