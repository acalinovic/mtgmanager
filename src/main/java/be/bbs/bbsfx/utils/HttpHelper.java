package be.bbs.bbsfx.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpHelper {
	
	public static Document getGathererPage(String url) {
		try {
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla")
					.timeout(3000)
					.get();
			return doc;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static Document getMCMPage(String lang, String expansion,String cardname) {
		cardname = cardname.replaceAll(" ", "-");
		cardname = cardname.replaceAll("'", "");
		String url = "https://www.cardmarket.com/" + lang + "/Magic/Products/Singles/" + expansion + "/" + cardname;
		//System.out.println(url);
		try {
			Document doc = Jsoup.connect(url)
					.data("query", "Java")
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000)
					.post();
			Element image = doc.getElementById("image").getElementsByAttribute("alt").get(1);
			//System.out.println(image.attr("src"));
			//System.out.println(image);
			return doc;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
