package il.ac.mta.model;

import java.util.Date;

public class Portfolio {
	//finals
	public final static int MAX_PORTFOLIO_SIZE=5;
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
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			this.stocks[i]=new Stock();
			this.stocksStatus[i]=new StockStatus();
		}
		this.portfolioSize = 0;
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

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	public Stock[] getStocks(){
		return this.stocks;
	}
	
	public void setPortfolioSize(int portfolioSize){
		this.portfolioSize=portfolioSize;
	}
	
	public String getHtmlString(){
		String res="<h1>" + this.title + "</h1>";
		for(int i=0;i<this.portfolioSize;i++){
			//res.concat(this.stocks[i].getHtmlDescription());
			res+=this.stocks[i].getHtmlDescription()+"<br>";//-->is it ok to write br tag?
		}
		return res;
	}
	
	public class StockStatus{
		//finals
		public final static int DO_NOTHING=0;
		public final static int BUY=1;
		public final static int SELL=2;
		//members
		private String symbol;
		private float currentAsk;
		private float currentBid;
		private Date date; 
		private int recommendation;
		private int stockQuantity;
		
		//c'tors
		public StockStatus(){
			this.symbol="";
			this.currentAsk = 0;
			this.currentBid = 0;
			this.date = new Date();
			this.recommendation = StockStatus.DO_NOTHING;
			this.stockQuantity = 0;
		}
		
		public StockStatus(String symbol, float currentAsk, float currentBid, Date date, int recommendation, int stockQuantity){
			this.symbol=symbol;
			this.currentAsk = currentAsk;
			this.currentBid = currentBid;
			this.date = new Date();
			this.recommendation = recommendation;
			this.stockQuantity = stockQuantity;
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

		public int getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}

