package il.ac.mta.model;

import java.util.Calendar;
import java.util.Date;

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
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private float balance;
	private int portfolioSize;
	
	//c'tors
	public Portfolio(){
		this.title = "";
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.balance=0;
		this.portfolioSize = 0;
	}
	
	//Copy c'tor
	public Portfolio(Portfolio p){
		this.title=p.title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			if (p.stocks[i] != null){
				this.stocks[i] = new Stock(p.stocks[i]);
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
		String res="<h1>" + this.title + "</h1>"+"<b>Total Portfolio Value: </b>" + getTotalValue() + "$, "
				+ "<b>Total Stocks Value: </b>" + getStocksValue() + "$, " + "<b>Balance: </b>" + getBalance() + "$<br>";
		for(int i=0;i<this.portfolioSize;i++){
			res+=this.stocks[i].getHtmlDescription() + " <b>quantity: </b>" + this.stocksStatus[i].stockQuantity + "<br>";
		}
		return res;
	}
	
	/**
	 * add data to the end of stocks[] if there is a place and he is not there yet if not do nothing.  
	 */
	private boolean addStock (Stock stock, StockStatus stockStatus){
		int index = isStockExist(stock.getStockSymbol());
		if (index >= 0){
			System.out.println("can't add new stock, portfolio alredy have \"" + stock.getStockSymbol() + "\" stocks");
			return false;
		}
		
		if (this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocks[this.portfolioSize] = new Stock(stock);
			this.stocksStatus[this.portfolioSize++] = new StockStatus(stockStatus);
			return true;
		}
		else{
			System.out.println("can't add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
			return false;
		}
	}
	
	/**
	 * remove existing stock in stocks[]
	 * @param i is the index of stocks[], between 0 to MAX_PORTFOLIO_SIZE-1;
	 */
	private Boolean removeStock (int i){
		if (i >= 0 && this.stocks[i] != null && i < this.portfolioSize){
			this.stocks[i] = null;
			this.stocksStatus[i] = null;
			this.portfolioSize--;
			this.fixStocks();
			this.fixStocksStatus();
			return true;
		}
		return false;
	}
	
	/**
	 * remove the stock with the same symbol otherwise it do nothing.
	 */
	public Boolean removeStock (String symbol){
		int i=isStockExist(symbol);
		return this.removeStock(i);
	}
	/**
	 * this method buy a stock.
	 * @param symbol - unique key, the name of a stock
	 * @param quantity - the amount of stock (-1 and above, -1= buy with all the balance)
	 * @return action is success or fail
	 */
	public boolean buyStock(String symbol, int quantity){		
		StockStatus newStockStatus = serverLike(symbol);
		Stock newStock;
		int i = isStockExist(symbol);
		
		if (newStockStatus == null){ //symbol not found
			return false;
		}
		
		newStock = new Stock(symbol , newStockStatus.getCurrentAsk() , newStockStatus.getCurrentBid() , newStockStatus.getDate());
		if (quantity == -1){
			quantity = (int)(balance / newStockStatus.getCurrentAsk());
		}
		
		if (quantity * newStockStatus.getCurrentAsk() >= balance){
			System.out.println("can't buy more then your balance");
			return false;	
		}
		else{
			newStockStatus.stockQuantity = quantity;
		}
		//--> quantity is good number and not -1
		if (i == -1){
			addStock(newStock, newStockStatus);
		}
		else{
			stocks[i].setStockAsk(newStock.getStockAsk());
			stocks[i].setStockBid(newStock.getStockBid());
			stocksStatus[i].stockQuantity += quantity;
		}
		updateBalance(newStockStatus.stockQuantity * newStockStatus.getCurrentBid() * -1);
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
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentBid());
			stocksStatus[i].setStockQuantity(0);
		}
		else if (quantity >= 0) { //Partial sale
			updateBalance(stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentBid());
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
	 * fix the stocks array, put all cells that in use at the start of the array.
	 */
	private void fixStocks(){
		int j=0;
		Stock[] res=new Stock[MAX_PORTFOLIO_SIZE];
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocks[i] != null){
				res[j++]=stocks[i];				
			}
		}
		this.stocks=res;
	}
	
	/**
	 * fix the stocksStatus array, put all cells that in use at the start of the array.
	 */
	private void fixStocksStatus(){
		int j=0;
		StockStatus[] res=new StockStatus[MAX_PORTFOLIO_SIZE];
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocksStatus[i] != null){
				res[j++]=stocksStatus[i];				
			}
		}
		this.stocksStatus=res;
	}
	
	/**
	 * check if the the symbol is in stocks[], when can't find it will return -1
	 * @param symbol - the key id of stock
	 * @return the index number or -1 
	 */
	private int isStockExist(String symbol){
		for (int i=0 ; i<this.portfolioSize ; i++){
			if (this.stocks[i].equal(symbol)){
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
			res+=(stocksStatus[i].stockQuantity * stocksStatus[i].currentBid);
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
	 * it simulate "real time" server for bring stocks information by symbol
	 * @param symbol - key
	 * @return stock status
	 */
	private StockStatus serverLike(String symbol){
		StockStatus res = new StockStatus();
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 12, 15);
		if (symbol.equals("PIH")){
			res.setStockStatus("PIH", 10, 8.5f, c1.getTime(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		}
		else if (symbol.equals("AAL")){
			res.setStockStatus("AAL", 30, 25.5f, c1.getTime(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		}
		else if (symbol.equals("CAAS")){
			res.setStockStatus("CAAS", 20, 15.5f, c1.getTime(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		}
		else{
			return null;
		}
		return res;
	}
	/**
	 * Inner class. more information in future
	 * @author Yaron_Cohen
	 * @since JDK 7
	 */
	public class StockStatus{
		//members
		private String symbol;
		private float currentAsk;
		private float currentBid;
		private Date date; 
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;
		
		//c'tors
		public StockStatus(){
			this("", 0, 0, new Date(), ALGO_RECOMMENDATION.DO_NOTHING,0);
		}
		
		public StockStatus(String symbol, float currentAsk, float currentBid, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
			this.symbol=symbol;
			this.currentAsk = currentAsk;
			this.currentBid = currentBid;
			this.date = new Date(date.getTime());
			this.recommendation=recommendation;
			this.stockQuantity = stockQuantity;
		}
		
		public StockStatus(String symbol){
			this(symbol, 0, 0, new Date(), ALGO_RECOMMENDATION.DO_NOTHING,0);
		}
		
		//Copy c'tor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.symbol;
			this.currentAsk = stockStatus.currentAsk;
			this.currentBid = stockStatus.currentBid;
			this.date = new Date(stockStatus.date.getTime());
			this.recommendation = stockStatus.recommendation;
			this.stockQuantity = stockStatus.stockQuantity;
		}
		//Setters and getters
		public String getSymbol() {
			return symbol;
		}
		
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}
		
		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		
		public void setStockStatus(String symbol, float currentAsk, float currentBid, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
			this.symbol = symbol;
			this.currentAsk = currentAsk;
			this.currentBid = currentBid;
			this.date = new Date(date.getTime());
			this.recommendation = recommendation;
			this.stockQuantity = stockQuantity;
		}
	}
}

