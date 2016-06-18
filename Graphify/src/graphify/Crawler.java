/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//code modified from http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
// purpose here is to enhance this soon
package graphify;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;

/**
 *
 * @author Ayomitunde
 */
public class Crawler {
    static Document doc;
    static int _maxSearch = 50;

    public static void Crawl(String url, String searchWord) throws IOException {
        System.out.println("starting ...");
              String currentUrl;
              JSearch js = new JSearch();
              if(Model._unvisited.isEmpty()){
                  currentUrl = url;
                  Model._visited.add(url);
              }else{
                  currentUrl = nextUrl();
              }
              js.strip(currentUrl, searchWord);
              if(js.searchWord(searchWord))
              {
                  System.out.println("found "+searchWord+" at "+currentUrl);
              }else{
                  Model.nextSearchWord = Model.searchedText.get(1);
                  Crawl(currentUrl, Model.nextSearchWord);
              }
              Model._unvisited.addAll(js.getLinks());
          
    }
    
    private static String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = Model._unvisited.remove(0);
        }while(Model._visited.contains(nextUrl) && Model._unvisited.size() > 0);
        Model._visited.add(nextUrl);
        return nextUrl;
    }
    
    public static void main(String [] args){
        Model.nextSearchWord = "hi";
        try {
            while(Model._visited.size() < _maxSearch){
                Crawl("http://www.urbandictionary.com/define.php?term=hi", Model.nextSearchWord);
            }
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Model.wordMeaning.toString());
        System.out.println("Visited all ");
    }
    
}
