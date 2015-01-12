package il.ac.mta.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.exception.BalanceException;
import il.ac.mta.exception.IllegalQuantityException;
import il.ac.mta.exception.PortfolioFullException;
import il.ac.mta.exception.StockAlreadyExistsException;
import il.ac.mta.exception.StockNotExistException;
import il.ac.mta.model.Portfolio;
import il.ac.mta.model.StockStatus;


/**
 * Class for managing stock status (current quantity, algo last recommendation and last update of bid/ask).
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class PortfolioService {
	public Portfolio getProtfolio() throws BalanceException, StockAlreadyExistsException, PortfolioFullException, StockNotExistException, IllegalQuantityException{
		Portfolio myPortfolio = new Portfolio();
		StockStatus currStock;
		
		myPortfolio.setTitle("Exercise 9 portfolio");
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
		c1.set(2014, 12-1, 15, 0, 0, 0);
		Date date = c1.getTime();
		
		if ("PIH".equalsIgnoreCase(symbol)){
			res = new StockStatus(symbol, 10, 8.5f, date);
		}
		else if ("AAL".equalsIgnoreCase(symbol)){
			res = new StockStatus(symbol, 30, 25.5f, date);
		}
		else if ("CAAS".equalsIgnoreCase(symbol)){
			res = new StockStatus(symbol, 20, 15.5f, date);
		}
		else if ("GOOG".equalsIgnoreCase(symbol)){
			res = new StockStatus(symbol, 60, 55.5f, date);
		}
		else if ("IBM".equalsIgnoreCase(symbol)){
			res = new StockStatus(symbol, 100, 100f, date);
		}
		else{	
		}
		return res;
	}

}
