package com.dataart.selenium.framework;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

    public  void logToFile(String loggingMessage) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            // This block configure the logger with handler and formatter
//            ClassLoader classLoader = getClass().getClassLoader();
//            fh = new FileHandler(classLoader.getResource("Log.txt").getFile());
            fh = new FileHandler("d:\\Logs\\1.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info(loggingMessage);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
