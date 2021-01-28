package org.redcraft.redcraftmedals;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.scheduler.BukkitScheduler;
import org.redcraft.redcraftmedals.database.DatabaseManager;

import net.luckperms.api.LuckPerms;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class RedCraftMedals extends JavaPlugin {

    public RedCraftMedals() {
        super();
    }

    protected RedCraftMedals(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

   
    private static RedCraftMedals instance;

    static public RedCraftMedals getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }
        instance = this;

        // Setup
        Config.readConfig(this);
        DatabaseManager.connect();

        // Schedulers
        BukkitScheduler scheduler = getServer().getScheduler();

        // Game listeners
        PluginManager pluginManager = this.getServer().getPluginManager();

       
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }
}