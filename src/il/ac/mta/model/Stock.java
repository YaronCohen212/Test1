package il.ac.mta.model;

import java.util.Date;

/**
 * @author Yaron_Cohen
 *
 */
public class Stock {
	private String symbol;
	private float ask;
	private float bid;
	private Date date; 
	
	
	/**
	 * 
	 */
	public Stock (){
		this.symbol="";
		this.ask=0;
		this.bid=0;
		this.date = new Date();
	}
	
	public Stock (String inputSymbol, float inputAsk, float inputBid, Date d){
		this.symbol=inputSymbol;
		this.ask=inputAsk;
		this.bid=inputBid;
		this.date=d;
	}
	
	public void setStockSymbol(String inputSymbol ){
		symbol=inputSymbol;
	}
	
	public void setStockAsk(float inputAsk ){
		ask=inputAsk;
	}
	
	public void setStockBid(float inputBid ){
		bid=inputBid;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getStockSymbol(){
		return symbol;
	}
	
	public float getStockAsk(){
		return ask;
	}
	
	public float getStockBid(){
		return bid;
	}
	
	public void setStock(String inputSymbol, float inputAsk, float inputBid, Date d){
		this.symbol=inputSymbol;
		this.ask=inputAsk;
		this.bid=inputBid;
		this.date=d;
	}
	
	public String getHtmlDescription(){
		return ("<b>Stock symbol</b>: " + symbol + ", <b>ask</b>: " + ask + ", <b>bid</b>:" + bid + ", <b>date</b>: " +
		date);	
	}
}
