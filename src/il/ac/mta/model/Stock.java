package il.ac.mta.model;

import java.util.Date;
/**
 * This class describes stock
 * members : symbol - single value can seen as the "name".
 * 			 ask - is the price a seller states she or he will accept for a good.
 *  		 bid - is the highest price that a buyer is willing to pay for a good.
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class Stock {
	protected String symbol;
	protected float ask;
	protected float bid;
	protected Date date;
	
// C'tor
	public Stock (){
		this("",0,0,new Date(0));
	}
	
	public Stock (String inputSymbol, float inputAsk, float inputBid, Date d){
		this.symbol = inputSymbol;
		this.ask = inputAsk;
		this.bid = inputBid;
		this.date = new Date(d.getTime());
	}

// Copy C'tor	
	public Stock (Stock s){
		this(s.symbol , s.ask , s.bid , s.date );
		this.date = new Date(s.date.getTime());
	}
	

	public void setSymbol(String inputSymbol ){
		symbol = inputSymbol;
	}
	
	public void setAsk(float inputAsk ){
		ask = inputAsk;
	}
	
	public void setBid(float inputBid ){
		bid = inputBid;
	}
	
	/** set a new date for date member
	 * @param date - the date that you want to change to.
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public float getAsk(){
		return ask;
	}
	
	public float getBid(){
		return bid;
	}
	
	public void setStock(String inputSymbol, float inputAsk, float inputBid, Date d){
		this.symbol = inputSymbol;
		this.ask = inputAsk;
		this.bid = inputBid;
		this.date = new Date (d.getTime());
	}
	
	public boolean equal (String symbol){
		if (this.symbol.equalsIgnoreCase(symbol))
			{return true;}
		else
			{return false;}
	}
	
	/**
	 * return an HTML code string of stock. 
	 *  @example
	 * <b>Stock symbol:</b> PIH, <b>ask:</b> 12.4, <b>bid:</b> 13.1, <b>date:</b> Sat Nov 15 22:12:04 IST 2014<br>
	 */
	public String getHtmlDescription(){
		return ("<b>Stock symbol</b>: " + symbol + ", <b>ask</b>: " + ask + ", <b>bid</b>: " + bid + ", <b>date</b>: " +
		date);	
	}
}
