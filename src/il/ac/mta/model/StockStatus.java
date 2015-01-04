package il.ac.mta.model;

import java.util.Date;

import il.ac.mta.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * this class extends for stock it add add members:<br>
 * current quantity, algo last recommendation.
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
	
	public StockStatus(String symbol, float ask, float bid, Date date){
		super(symbol, ask, bid, date);
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
	
	/**
	 * return an HTML code string of stock Status. 
	 *  @example
	 * <b>Stock symbol:</b> PIH, <b>ask:</b> 12.4, <b>bid:</b> 13.1, <b>date:</b> Sat Nov 15 22:12:04 IST 2014 <b>quantity: </b>32 <br>
	 */
	public String getHtmlDescription(){
		String res = super.getHtmlDescription();
		res += " <b>quantity: </b>" + this.stockQuantity;
		return res;
	}
}
