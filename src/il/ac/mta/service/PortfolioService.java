package il.ac.mta.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;


/**
 * More information will be announced later
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class PortfolioService {
	public Portfolio getProtfolio(){
		Portfolio myPortfolio = new Portfolio();
		
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		return myPortfolio;
	}
	
	/**
	 * it simulate "real time" server for bring stocks information by symbol
	 * @param symbol - key
	 * @return stock
	 */
	public static Stock serverLike(String symbol){
		Stock res = new Stock();
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 11, 15, 0, 0, 0);
		Date date = c1.getTime();
	
		if (symbol.equals("PIH")){
			res.setStock("PIH", 10, 8.5f, date);
		}
		else if (symbol.equals("AAL")){
			res.setStock("AAL", 30, 25.5f, date);
		}
		else if (symbol.equals("CAAS")){
			res.setStock("CAAS", 20, 15.5f, date);
		}
		else if (symbol.equals("GOOG")){
			res.setStock("GOOG", 42, 39.5f, date);
		}
		else if (symbol.equals("IBM")){
			res.setStock("IBM", 67, 60.1f, date);
		}
		else{
			return null;
		}
		return res;
	}
}
