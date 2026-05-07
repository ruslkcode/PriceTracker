package com.ruslk.pricetracker.service;

import com.ruslk.pricetracker.model.Product;
import com.ruslk.pricetracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceUpdaterService {

    @Autowired
    private TelegramNotificationService notificationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ScraperService scraperService;

    @Value("${telegram.bot.chatId}")
    private String chatId;

    @Scheduled(fixedDelay = 60000)
    public void checkAndUpdatePrices(){
        List<Product> products = productRepository.findAll();


        for (Product product : products){
            System.out.println("Checking price for: " + product.getName());

            Double newPrice = scraperService.getPriceByUrl(product.getUrl());

            if (newPrice != null && newPrice <= product.getTargetPrice()){
                String message = "PRICE DROP!" + product.getName()
                        + " now costs" + newPrice+ "\n URL:" + product.getUrl();

                notificationService.sendNotification(chatId, message);
            }

            if (newPrice != null && newPrice > 0){
                if (newPrice != product.getCurrentPrice()){
                    System.out.println("ALERT: Price for " + product.getName() + " has changed");
                    product.setCurrentPrice(newPrice);
                    productRepository.save(product);
                } else {
                    System.out.println("Price for " + product.getName() + " has NOT changed");
                }
            }


        }
    }
}
