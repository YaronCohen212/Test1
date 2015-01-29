package il.ac.mta.model;

import java.util.List;

import il.ac.mta.exception.BalanceException;
import il.ac.mta.exception.IllegalQuantityException;
import il.ac.mta.exception.PortfolioFullException;
import il.ac.mta.exception.StockAlreadyExistsException;
import il.ac.mta.exception.StockNotExistException;

/**
 * This class describes stock portfolio.<br>
 * title - the name of the stock portfolio.<br>
 * stocks - array of stocks.<br>
 * stocks status - array of recommendation for each stock will do only: buy, sell or do nothing.<br>
 * portfolioSize - the logic size of stocks.<br>
 * <br>MAX_PORTFOLIO_SIZE - the physical size of stocks.
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class Portfolio {
	//finals
	public enum ALGO_RECOMMENDATION {DO_NOTHING , SELL , BUY};
	public final static int MAX_PORTFOLIO_SIZE=5;
	//members
	private String title;
	private StockStatus[] stocksStatus;
	private float balance;
	private int portfolioSize;
	
	//c'tors
	public Portfolio(){
		this.title = "";
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.balance = 0;
		this.portfolioSize = 0;
	}
	
	public Portfolio(List<StockStatus> stockStatuses) {
		this();
		this.portfolioSize = stockStatuses.size();
		for (int i=0; i<portfolioSize; i++){
			this.stocksStatus[i] = stockStatuses.get(i);
		}
	}
	
	//Copy c'tor
	public Portfolio(Portfolio p){
		this.title = p.title;
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			if (p.stocksStatus[i] != null){
				this.stocksStatus[i] = new StockStatus(p.stocksStatus[i]);	
			}
		}	
		this.balance = p.balance;
		this.portfolioSize = p.portfolioSize;
	}

	//Setters and getters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StockStatus[] getStocks(){
		return this.stocksStatus;
	}
	
	public int getPortfolioSize() {
		return portfolioSize;
	}
	
	public float getBalance()
	{
		return balance;
	}
	
	/**
	 * 
	 * @param amount- the money to add
	 * @return true if action complete false if not.
	 */
	public void updateBalance(float amount) throws BalanceException{
		if (this.balance + amount >= 0){
			this.balance += amount;
		}
		else{
			throw new BalanceException();
		}
	}
	
	/**
	 * return the HTML String for portfolio 
	 * @example
	 * <h1>Extreme risk stocks</h1>
	 * <b>Stock symbol:</b> PIHGT, <b>ask:</b> 12.4, <b>bid:</b> 13.1, <b>date:</b> Sat Nov 15 22:12:04 IST 2014 <b>quantity: </b>256 <br>
	 * <b>Stock symbol:</b> AAPLE, <b>ask:</b> 54.2, <b>bid:</b> 6.4, <b>date:</b> Sun Dec 2 14:22:56 IST 2013 <b>quantity: </b>88<br>.....
	 */
	public String getHtmlString(){
		String res = "<h1>" + this.title + "</h1>"+"<b>Total Portfolio Value: </b>" + getTotalValue() + "$, "
				+ "<b>Total Stocks Value: </b>" + getStocksValue() + "$, " + "<b>Balance: </b>" + getBalance() + "$<br>";
		for(int i=0 ; i<this.portfolioSize ; i++){
			res+=this.stocksStatus[i].getHtmlDescription() + "<br>";
		}
		return res;
	}
	
	/**
	 * add stock to the end of stocksStatus[] if there is a place and he is not there yet if not do nothing.  
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock (Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		int index = isStockExists(stock.getSymbol());
		if (index != -1){
			throw new StockAlreadyExistsException(stock.getSymbol());
		}
		else if (this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocksStatus[this.portfolioSize++] = new StockStatus(stock);
		}
		else{
			throw new PortfolioFullException();
		}
	}
	
	/**
	 * this method buy a stock.
	 * @param symbol - unique key, the name of a stock
	 * @param quantity - the amount of stock (-1 and above, -1= buy with all the balance)
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 */
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistException{		
		int i = isStockExists(symbol);
		if (i == -1){
			throw new StockNotExistException(symbol);
		}
		if (quantity == -1){
			quantity = (int)(balance / stocksStatus[i].getAsk());
		}
		updateBalance(quantity * stocksStatus[i].getAsk() * -1);	
		stocksStatus[i].setStockQuantity(quantity + stocksStatus[i].getStockQuantity());
	}
	
	/**
	 * remove the stock with the same symbol.
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 * @throws IllegalQuantityException 
	 */
	public void removeStock (String symbol) throws StockNotExistException, BalanceException, IllegalQuantityException{
		int i = isStockExists(symbol);
		if (i == -1){
			throw new StockNotExistException(symbol);
		}
		sellStock(stocksStatus[i].getSymbol() ,-1);
		this.stocksStatus[i] = null;
		this.fixStocksStatus();
		this.portfolioSize--;
	}
	
	/**
	 * this method sell a stock.<br>
	 * if quantity is more then you have it will sell all
	 * @param symbol - unique key, the name of a stock
	 * @param quantity - the amount of stock
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 * @throws IllegalQuantityException 
	 */
	public void sellStock(String symbol, int quantity) throws BalanceException, StockNotExistException, IllegalQuantityException{
		int i=isStockExists(symbol);
		if (i < 0){ 	
			throw new StockNotExistException(symbol);
		}
		else if (quantity > stocksStatus[i].getStockQuantity()){ //if quantity is more then what you have
			this.sellStock(symbol,-1);
		}
		else if(quantity == -1){ //sell the whole quantity
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getBid());
			stocksStatus[i].setStockQuantity(0);
		}
		else if (quantity >= 0) { //Partial sale
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getBid());
			stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() - quantity);	
		}
		else{
			//stock exists and quantity less then -1 (Task7 6.4.b)
			throw new IllegalQuantityException();
		}
	}
	
	public StockStatus findBySymbol(String symbol) {
		for (int i=0; i<MAX_PORTFOLIO_SIZE ;i++){
			if(this.stocksStatus[i] != null && 
					symbol.equalsIgnoreCase(stocksStatus[i].getSymbol()) == true){
				return stocksStatus[i];
			}
		}
		return null;
	}
	
	/**
	 * fix the stocksStatus array, put all cells that in use at the start of the array.
	 */
	private void fixStocksStatus(){
		int j = 0;
		StockStatus[] res=new StockStatus[MAX_PORTFOLIO_SIZE];
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocksStatus[i] != null){
				res[j++] = stocksStatus[i];				
			}
		}
		this.stocksStatus = res;
	}
	
	/**
	 * check if the the symbol is in stocks[], when can't find it will return -1
	 * @param symbol - the key id of stock
	 * @return the index number or -1 
	 */
	private int isStockExists(String symbol){
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocksStatus[i].getSymbol().equalsIgnoreCase(symbol)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return the value of all the stocks together
	 */
	public float getStocksValue(){
		float res = 0;
		for (int i=0 ; i<portfolioSize ; i++){
			res += (stocksStatus[i].getStockQuantity() * stocksStatus[i].bid);
		}
		return res;
	}
	
	/**
	 * @return the value of the portfolio
	 */
	public float getTotalValue(){
		return getBalance()+getStocksValue();
	}
	
	
	/**
	 * Inner class. more information in future
	 * @author Yaron_Cohen
	 * @since JDK 7
	 */
	}

