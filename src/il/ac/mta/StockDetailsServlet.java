package il.ac.mta;
import java.util.Date;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

public class StockDetailsServlet extends HttpServlet{
	public class Stock{
		private String symbol;
		private float ask;
		private float bid;
		private Date date; 
		
		
		public Stock (){
			symbol="";
			ask=0;
			bid=0;
			Date dateTmp=new Date(0,0,0);
			date=dateTmp;
		}
		
		public Stock (String inputSymbol, float inputAsk, float inputBid, int dd ,int mm ,int yyyy){
			symbol=inputSymbol;
			ask=inputAsk;
			bid=inputBid;
			Date dateTmp=new Date(dd,mm,yyyy);
			date=dateTmp;	
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
		@SuppressWarnings("deprecation")
		public void setStockDate(int dd ,int mm ,int yyyy){
			date.setDate(dd);
			date.setMonth(mm-1);
			date.setYear(yyyy);
		}
		public void setStock(String inputSymbol, float inputAsk, float inputBid,int dd ,int mm ,int yyyy){
			setStockSymbol(inputSymbol);
			setStockAsk(inputAsk);
			setStockBid(inputBid);
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
		public Date getStockDate(){
			return date;
		}
		public String getHtmlDescription(){
			return ("<body><b>Stock symbol</b>: "+symbol+", <b>ask</b>: "+ask+", <b>bid</b>:"+bid+", <b>date</b>: "+
			date.getDate()+"/"+date.getMonth()+"/"+date.getYear()+"</body>");	
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			resp.setContentType("text/html");
			/*			Stock s1= new Stock("PIH" , (float)12.4 , (float)13.1 ,15,11,2014);
			Stock s2= new Stock("AAL" , (float)5.5 , (float)5.78 , 15 , 11 , 2014);
			Stock s3= new Stock("CAAS" , (float)31.5 , (float)31.2 , 15 , 11 , 2014);
			
			resp.getWriter().println(s1.getHtmlDescription()+"<br>");
			resp.getWriter().println(s2.getHtmlDescription()+"<br>");
			resp.getWriter().println(s3.getHtmlDescription()+"<br>");
*/
			Stock[] s= new Stock[3];
			s[0].setStock("PIH" , (float)12.4 , (float)13.1 , 15 ,11 ,2014);
			s[1].setStock("AAL" , (float)5.5 , (float)5.78 , 15 , 11 , 2014);
			s[2].setStock("CAAS" , (float)31.5 , (float)31.2 , 15 , 11 , 2014);
			resp.getWriter().println(s[0].getHtmlDescription()+"<br>");
			resp.getWriter().println(s[1].getHtmlDescription()+"<br>");
			resp.getWriter().println(s[2].getHtmlDescription()+"<br>");

	}
}
