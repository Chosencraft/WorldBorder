package com.chosencraft.www.worldborder.utils;

import com.chosencraft.www.worldborder.WorldBorderMain;
import net.md_5.bungee.api.ChatColor;

import java.util.logging.Level;

public class Logger
{
    private static WorldBorderMain main = new WorldBorderMain();

    private static java.util.logging.Logger logger = main.getLogger();

    private String logMessage =
            ChatColor.YELLOW + "[" +
                    ChatColor.AQUA + "WorldBorder" +
                    ChatColor.YELLOW + "]" +
                    ChatColor.RED +  " [%s]" +
                    ChatColor.AQUA  + " %s" +
                    ChatColor.RESET;

    private Logger()
    {
        logger.setLevel(Level.ALL);
    }

    private static Logger instance = new Logger();

    public static Logger getInstance()
    {
        return instance;
    }


    public void logWarning(String... message)
    {
        for (String line : message)
        {
            logger.log(Level.WARNING, String.format(logMessage, "WARNING", line));
        }
    }

    public void logError(String... message)
    {
        for (String line : message)
        {
            logger.log(Level.SEVERE, String.format(logMessage, "ERROR", line));
        }
    }

    public void logInfo(String... message)
    {
        for (String line : message)
        {
            logger.log(Level.INFO, String.format(logMessage, "INFO", line));
        }
    }
}
