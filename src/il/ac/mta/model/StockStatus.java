package il.ac.mta.model;

import java.util.Date;

import il.ac.mta.model.Portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock{
	//members
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	//c'tor
	public StockStatus(){
		super();
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	public StockStatus (String symbol, float ask, float bid, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
		super(symbol, ask, bid, date);
		this.recommendation = recommendation;
		this.stockQuantity = stockQuantity;
	}
	
	//copy c'tor
	public StockStatus (StockStatus stockStatus){
		super(stockStatus);
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
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
}
