package com.croesus.scraping;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class ScrapingMain {
	public static void main(String[] args) throws IOException {
		
		
		
	}
	
	public String getMonexPrice() throws IOException {
		Document doc = Jsoup.connect("https://info.monex.co.jp/service/fee/stock/index.html").get();

		Elements elements = doc.getElementsByClass("table-cmn_01 s-mb-10");
		Node priceTable = elements.get(0).childNode(3).parentNode();
		
		System.out.println(priceTable.toString());
		
		return priceTable.toString();
	}

}
