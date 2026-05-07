package com.ruslk.pricetracker.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramNotificationService extends TelegramLongPollingBot {



    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botUsername;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            System.out.println("Your Chat ID: " + update.getMessage().getChatId());
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }


    public void sendNotification(String chatID, String message){
        SendMessage sm = new SendMessage();

        sm.setChatId(chatID);
        sm.setText(message);

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
