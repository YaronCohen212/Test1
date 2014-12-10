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
	private String symbol;
	private float ask;
	private float bid;
	private Date date; 
	
// C'tor
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

// Copy C'tor	
	public Stock (Stock s){
		this(s.symbol , s.ask , s.bid , s.date);
		this.date = new Date(s.date.getTime());
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
	
	public boolean equal (Stock stock){
		if (this.symbol.equals(stock.symbol) && this.date.equals(stock.date))//-->add ask==stock ask and bid==stock.bid
			{return true;}
		else
			{return false;}
	}
	/**
	 * return the HTML code for stock 
	 * Stock symbol: <symbol>, ask: <ask>, bid: <bid>, date: <UTC date>
	 */
	public String getHtmlDescription(){
		return ("<b>Stock symbol</b>: " + symbol + ", <b>ask</b>: " + ask + ", <b>bid</b>: " + bid + ", <b>date</b>: " +
		date);	
	}
}
