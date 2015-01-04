package il.ac.mta.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.StockStatus;


/**
 * Class for managing stock status (current quantity, algo last recommendation and last update of bid/ask).
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class PortfolioService {
	public Portfolio getProtfolio(){
		Portfolio myPortfolio = new Portfolio();
		StockStatus currStock;
		
		myPortfolio.setTitle("Exercise 8 portfolio");
		myPortfolio.updateBalance(10000);
		
		currStock = getUpdatedStock("PIH");
		myPortfolio.addStock(currStock);
		myPortfolio.buyStock(currStock.getStockSymbol(), 20);
		
		currStock = getUpdatedStock("AAL");
		myPortfolio.addStock(currStock);
		myPortfolio.buyStock(currStock.getStockSymbol(), 30);
		
		currStock = getUpdatedStock("CAAS");
		myPortfolio.addStock(currStock);
		myPortfolio.buyStock(currStock.getStockSymbol(), 40);	

		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		return myPortfolio;
	}
	public static StockStatus getUpdatedStock (String symbol){
		StockStatus res = null;
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 11, 15, 0, 0, 0);
		Date date = c1.getTime();
		
		if ("PIH".equalsIgnoreCase(symbol)){
			res = new StockStatus("PIH", 10, 8.5f, date);
		}
		else if ("AAL".equalsIgnoreCase(symbol)){
			res = new StockStatus("AAL", 30, 25.5f, date);
		}
		else if ("CAAS".equalsIgnoreCase(symbol)){
			res = new StockStatus("CAAS", 20, 15.5f, date);
		}
		else if ("GOOG".equalsIgnoreCase(symbol)){
			res = new StockStatus("GOOG", 60, 55.5f, date);
		}
		else if ("IBM".equalsIgnoreCase(symbol)){
			res = new StockStatus("IBM", 100, 100f, date);
		}
		else{	
		}
		return res;
	}

}
