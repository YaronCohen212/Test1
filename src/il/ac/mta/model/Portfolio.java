package il.ac.mta.model;

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
	public final static int MAX_PORTFOLIO_SIZE=5;//= 2 or more
	//members
	private String title;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	
	//c'tors
	public Portfolio(){
		this.title = "";
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
	}
	
	//Copy c'tor
	public Portfolio(Portfolio p){
		this.title=p.title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			if (p.stocks[i] != null){
				this.stocks[i]=new Stock(p.stocks[i]);
				this.stocksStatus[i]=new StockStatus(p.stocksStatus[i]);	
			}
		}	
		this.portfolioSize = p.portfolioSize;
	}
	
	//Setters and getters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	public Stock[] getStocks(){
		return this.stocks;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}
	
	/**
	 * return the HTML code for portfolio 
	 * @example
	 * <h1>Extreme risk stocks</h1>
	 * <b>Stock symbol:</b> PIH, <b>ask:</b> 12.4, <b>bid:</b> 13.1, <b>date:</b> Sat Nov 15 22:12:04 IST 2014<br>
	 * <b>Stock symbol:</b> AAPLE, <b>ask:</b> 54.2, <b>bid:</b> 6.4, <b>date:</b> Sun Dec 2 14:22:56 IST 2013<br>.....
	 */
	public String getHtmlString(){
		String res="<h1>" + this.title + "</h1>";
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			if (this.stocks[i] == null){
				continue;}
			else{
			res+=this.stocks[i].getHtmlDescription()+"<br>";}
		}
		return res;
	}
	/**
	 * add data to the end of stocks[] if there is a place if not do nothing.  
	 */
	public void addStock (Stock stock){
		if (this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocks[this.portfolioSize] = new Stock(stock);
			this.stocksStatus[this.portfolioSize++]=new StockStatus();
		}
	}
	
	/**
	 * add stock to the end of stocks[] if there is a place if not do nothing.  
	 */
	public void addStock (String symbol, float ask, float bid, Date date){
		Stock stock = new Stock(symbol , ask , bid, date);
		this.addStock(stock);
	}
	/**
	 * remove existing stock in stocks[]
	 * @param i is the index of stocks[], between 0 to MAX_PORTFOLIO_SIZE-1;
	 */
	public void removeStock (int i){
		if (i>=0 && this.stocks[i] != null && i<MAX_PORTFOLIO_SIZE){
			this.stocks[i] = null;
			this.stocksStatus[i] = null;
			this.portfolioSize--;
			this.fixStocks();
		}
	}	
	
	/**
	 * remove the selected stock, stock must bee inside stocks[] otherwise it do nothing.
	 */
	public void removeStock(Stock stock){
		for (int i=0 ; i<MAX_PORTFOLIO_SIZE ; i++){
			if (stock==this.stocks[i]){
				this.removeStock(i);
				break;
			}
		}
	}
	
	//put all stocks in the start of the array stocks keep the same order
	private void fixStocks(){
		int j=0;
		Stock[] res=new Stock[MAX_PORTFOLIO_SIZE];
		for (int i=0 ; i<MAX_PORTFOLIO_SIZE ; i++){
			if (this.stocks[i] != null){
				res[j++]=stocks[i];				
			}
		}
		this.stocks=res;
	}
	
	/**
	 * Inner class. more information in future
	 * @author Yaron_Cohen
	 * @since JDK 7
	 */
	public class StockStatus{//static if enum
		//finals
		//private enum Recommendation  {DO_NOTHING , SELL , BUY};
		public final static int DO_NOTHING=0;
		public final static int BUY=1;
		public final static int SELL=2;
		//members
		private String symbol;
		private float currentAsk;
		private float currentBid;
		private Date date; 
		//private enum recommendation {DO_NOTHING , SELL , BUY};
		private int stockQuantity;
		
		//c'tors
		public StockStatus(){
			this("", 0, 0, new Date(), 0,0);
		}
		
		public StockStatus(String symbol, float currentAsk, float currentBid, Date date, int recommendation, int stockQuantity){
			this.symbol=symbol;
			this.currentAsk = currentAsk;
			this.currentBid = currentBid;
			this.date = new Date();
			this.stockQuantity = stockQuantity;
		}
		
		//Copy c'tor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.symbol;
			this.currentAsk = stockStatus.currentAsk;
			this.currentBid = stockStatus.currentBid;
			this.date = new Date(stockStatus.date.getTime());
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

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}

