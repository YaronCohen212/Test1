package il.ac.mta.model;

import il.ac.mta.service.PortfolioService;

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
	 * @return ture if action complete false if not.
	 */
	public boolean updateBalance(float amount){
		if (this.balance + amount >= 0){
			this.balance += amount;
			return true;
		}
		else{
			System.out.println("Not enough balance to complete purchase");
			return false;
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
	 * add data to the end of stocksStatus[] if there is a place and he is not there yet if not do nothing.  
	 */
	private boolean addStock (StockStatus stockStatus){
		int index = isStockExist(stockStatus.getStockSymbol());
		if (index != -1){
			System.out.println("can't add new stock, portfolio alredy have \"" + stockStatus.getStockSymbol() + "\" stocks");
			return false;
		}
		else if (this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocksStatus[this.portfolioSize++] = stockStatus;
			return true;
		}
		else{
			System.out.println("can't add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
			return false;
		}
	}
	
	/**
	 * remove existing stock in stocks[]
	 * @param i is the index of stocks[], between -1 to MAX_PORTFOLIO_SIZE-1;
	 */
	private Boolean removeStock (int i){
		if (i >= 0 && this.stocksStatus[i] != null && i < this.portfolioSize){
			if (stocksStatus[i].getStockQuantity() > 0){
				sellStock(stocksStatus[i].getStockSymbol() ,-1);
			}
			this.stocksStatus[i] = null;
			this.fixStocksStatus();
			this.portfolioSize--;
			return true;
		}
		return false;
	}
	
	/**
	 * remove the stock with the same symbol otherwise it do nothing.
	 */
	public Boolean removeStock (String symbol){
		int i = isStockExist(symbol);
		return this.removeStock(i);
	}
	
	/**
	 * this method buy a stock.
	 * @param symbol - unique key, the name of a stock
	 * @param quantity - the amount of stock (-1 and above, -1= buy with all the balance)
	 * @return action is success or fail
	 */
	public boolean buyStock(String symbol, int quantity){		
		Stock newStock = PortfolioService.serverLike(symbol);
		StockStatus newStockStatus; 
		int i = isStockExist(symbol);
		
		if (newStock == null){ //symbol not found
			return false;
		}
		newStockStatus = new StockStatus(newStock);
		
		if (quantity == -1){
			quantity = (int)(balance / newStockStatus.getStockAsk());
		}
		newStockStatus.setStockQuantity(quantity);
		
		if (newStockStatus.getStockQuantity() * newStockStatus.getStockAsk() >= balance){
			System.out.println("can't buy more then your balance");
			return false;	
		}
		
		//--> quantity is good number and not -1
		if (i == -1){
			addStock(newStockStatus);
		}
		else{
			stocksStatus[i].setStockAsk(newStockStatus.getStockAsk());
			stocksStatus[i].setStockBid(newStockStatus.getStockBid());
			stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + quantity);
		}
		updateBalance(newStockStatus.getStockQuantity() * newStockStatus.getStockAsk() * -1);
		return true;
	}
	
	/**
	 * this method sell a stock.<br>
	 * if quantity is more then you have it will sell all
	 * @param symbol - unique key, the name of a stock
	 * @param quantity - the amount of stock
	 * @return action is success or fail
	 */
	public boolean sellStock(String symbol, int quantity){
		int i=isStockExist(symbol);
		if (i < 0){
			return false;
		}
		else if (quantity > stocksStatus[i].getStockQuantity()){ //if quantity is more then what you have
			System.out.println("Not enough stocks to sell");
			return false;
		}
		else if(quantity == -1){ //sell the whole quantity
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getStockBid());
			stocksStatus[i].setStockQuantity(0);
		}
		else if (quantity >= 0) { //Partial sale
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getStockBid());
			stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() - quantity);	
		}
		else{
			//stock exists and quantity less then -1 (Task7.6.4.b)
			System.out.println("quantity can't be " + quantity + " (-1 or more)");
			return false;
		}
		return true;
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
	private int isStockExist(String symbol){
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocksStatus[i].getStockSymbol().equals(symbol)){
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

