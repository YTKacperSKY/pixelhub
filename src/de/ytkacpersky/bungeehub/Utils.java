package de.ytkacpersky.bungeehub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

class Utils {

    static Properties properties = new Properties();
    static String hubServer;
    static String permission;
    static String command;
    static String[] aliases;
    static String message;
    static String alreadyConnectedMessage;

    static void loadProperties() {
        try(FileReader reader = new FileReader("plugins/Pixelhub/config.properties")) {
            properties.load(reader);
        } catch(FileNotFoundException e) {
            File f = new File("plugins/Pixelhub");
            f.mkdirs();
            f = new File("plugins/Pixelhub/config.properties");
            try {
                Files.copy(Main.class.getResourceAsStream("/config.properties"), f.toPath());
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }


}
