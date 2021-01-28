package org.redcraft.redcraftmedals.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dieselpoint.norm.Database;

import org.redcraft.redcraftmedals.Config;
import org.redcraft.redcraftmedals.RedCraftMedals;
import org.redcraft.redcraftmedals.models.database.PlayerPreferences;

public class DatabaseManager {
    private static Database database;

    public static void connect() {
        database = new Database();
        database.setJdbcUrl(Config.databaseUri);

        database.setUser(Config.databaseUsername);
        database.setPassword(Config.databasePassword);

        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(PlayerPreferences.class);
        createStructure(classes);

        RedCraftMedals.getInstance().getLogger().info("Connected to database!");
    }

    public static void createStructure(List<Class<?>> classes) {
        Iterator<Class<?>> it = classes.iterator();
        while (it.hasNext()) {
            Class<?> classToCreate = it.next();
            try {
                database.createTable(classToCreate);
            } catch (Exception ex) {
                // TODO handle exeptions
                // PS: it's normal some are thrown if the table already exists
            }
        }
    }

    public static Database getDatabase() {
        return database;
    }

}
