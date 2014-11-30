package il.ac.mta;

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
		symbol="";
		ask=0;
		bid=0;
		date = new Date();
	}
	
	public Stock (String inputSymbol, float inputAsk, float inputBid, Date d){
		symbol=inputSymbol;
		ask=inputAsk;
		bid=inputBid;
		date=d;
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
	
	public String getHtmlDescription(){
		return ("<b>Stock symbol</b>: " + symbol + ", <b>ask</b>: " + ask + ", <b>bid</b>:" + bid + ", <b>date</b>: " +
		date+ "<br>");	
	}
}
