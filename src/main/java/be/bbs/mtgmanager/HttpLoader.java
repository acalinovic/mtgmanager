package be.bbs.mtgmanager;

import be.bbs.bbsfx.utils.HttpHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpLoader {

	
	public static void main(String[] args) {
		
		Document doc = HttpHelper.getGathererPage("http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=263540");
		System.out.println(doc.getElementById("ctl00_ctl00_ctl00_MainContainer"));
	}

}
