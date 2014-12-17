package il.ac.mta.service;

import il.ac.mta.model.Portfolio;


/**
 * More information will be announced later
 * @author Yaron_Cohen
 * @since JDK 7
 */
public class PortfolioService {
	public Portfolio getProtfolio(){
		Portfolio myPortfolio = new Portfolio();
		
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
