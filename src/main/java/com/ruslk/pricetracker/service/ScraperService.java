package com.ruslk.pricetracker.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScraperService {

    public Double getPriceByUrl(String url){
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();

            Element priceElement = document.selectFirst("[data-test='branded-price-whole-value']");

            if (priceElement != null) {
                String text = priceElement.text();
                System.out.println("Raw text from site: " + text);

                String noDots = text.replace(".", "");

                String normalizedComma = noDots.replace(",", ".");

                String cleanPrice = normalizedComma.replaceAll("[^0-9.]", "");

                System.out.println("Current price: " + cleanPrice);
                return Double.parseDouble(cleanPrice);
            }
        } catch (IOException e) {
            System.err.println("Error while parsing: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error while converting prices: " + e.getMessage());
        }
        return 0.0;

    }
}
