package il.ac.mta.model;

import java.util.Date;
import il.ac.mta.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * more information in future
 * @author Yaron_Cohen
 * @since JDK 7
 */
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
	
	public StockStatus(String symbol, float ask, float bid, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
		super(symbol, ask, bid, date);
		this.recommendation = recommendation;
		this.stockQuantity = stockQuantity;
	}
	
	public StockStatus (Stock stock){
		super(stock);
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	//copy c'tor
	public StockStatus (StockStatus stockStatus){
		super((Stock)stockStatus);
		this.recommendation = stockStatus.recommendation;
		this.stockQuantity = stockStatus.stockQuantity;
	}
	
	//getters & setters
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
	public String getHtmlDescription(){
		String res = super.getHtmlDescription();
		res += " <b>quantity: </b>" + this.stockQuantity;
		return res;
	}
}
