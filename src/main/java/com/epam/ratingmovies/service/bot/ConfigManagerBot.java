package com.epam.ratingmovies.service.bot;

import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManagerBot {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BOT_PROPERTIES = "bot.properties";
    private static String BOT_TOKEN;
    private static String CHANNEL_NAME;
    private static String BOT_NAME;

    static {
        try (InputStream inputStream =
                     ConnectionPool.class.getClassLoader().getResourceAsStream(BOT_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            BOT_TOKEN = (String) properties.get("telegram.bot.token");
            BOT_NAME = (String) properties.get("");
            CHANNEL_NAME = (String) properties.get("telegram.channel.name");
        } catch (IOException e) {
            LOGGER.fatal("Error not upload config Bot");
        }
    }






    public static String getBotToken() {
        return BOT_TOKEN;
    }

    public static String getChannelName() {
        return CHANNEL_NAME;
    }

    public static String getBotName() {
        return BOT_NAME;
    }

    private ConfigManagerBot() {
    }

}
