package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CamouflagedReptilians plugin enabled!");
        // Initialize plugin components
    }

    @Override
    public void onDisable() {
        getLogger().info("CamouflagedReptilians plugin disabled!");
        // Clean up plugin components
    }
}