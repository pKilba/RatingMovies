//package com.epam.ratingmovies.service.bot;
//
//import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Properties;
//import java.util.logging.Level;
//
//public class Bot extends TelegramLongPollingBot {
//
//    private static final Logger LOGGER = LogManager.getLogger();
//    private static String BOT_PROPERTIES = "bot.properties";
//    private static String BOT_TOKEN;
//    private static String CHANNEL_NAME;
//    private static String BOT_NAME;
//
//    static {
//        try (InputStream inputStream =
//                     ConnectionPool.class.getClassLoader().getResourceAsStream(BOT_PROPERTIES)) {
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            BOT_TOKEN = (String) properties.get("telegram.bot.token");
//            BOT_NAME = (String) properties.get("");
//            CHANNEL_NAME = (String) properties.get("telegram.channel.name");
//        } catch (IOException e) {
//            LOGGER.fatal("Error not upload config Bot");
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "PAshaКолбаса11";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "PAshaКолбаса";
//    }
//
//    @Override
//    public void onRegister() {
//        super.onRegister();
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), message);
//    }
//
//    public synchronized void sendMsg(String chatId, String s) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(s);
//        try {
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//                LOGGER.log(Level.SEVERE, "Exception: ", e.toString());
//        }
//
//
//        @Override
//        public void onUpdatesReceived (List < Update > updates) {
//            super.onUpdatesReceived(updates);
//        }
//    }
//}
