import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Article {
    public List<String> articleList = new ArrayList<String>();

    public void parseSite(){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.dota2.com/international/battlepass/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements h1Elements = doc.getElementsByAttributeValue("class", "PrizePool");
        Elements divElements = doc.getElementsByAttributeValue("div", "Content NewBlock Center");
        Element h1Element = doc.getElementsByAttributeValue("class", "PrizePool").first();
        String text = h1Element.text();
        articleList.add(0, text);
    }
}
