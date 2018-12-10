package be.bbs.mtgmanager.entity.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;


public class CardHtml {

	private Document document;
	private String name;
	private Node mainContent;
	private Node imageNode;
	public CardHtml(Document doc) {
		this.document = doc;
		this.setName(doc.getElementsByTag("Title").text().replaceAll("...Cardmarket", ""));
		this.mainContent = doc.getElementById("mainContent");
		this.setImageNode(doc.getElementById("mainContent").getElementById("image"));
		// TODO Auto-generated constructor stub
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Node getMainContent() {
		return mainContent;
	}
	public void setMainContent(Node mainContent) {
		this.mainContent = mainContent;
	}
	public Node getImageNode() {
		return imageNode;
	}
	public void setImageNode(Node imageNode) {
		this.imageNode = imageNode;
	}
	public void translate() {
	}

}
