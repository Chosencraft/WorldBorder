package com.chosencraft.www.worldborder;

import com.chosencraft.www.worldborder.commands.WorldBorder;
import com.chosencraft.www.worldborder.listeners.EntityGuard;
import com.chosencraft.www.worldborder.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderMain extends JavaPlugin
{

    public void onEnable()
    {
        initalizeCommands();
        initializeListeners();
        Utilities.initializeBorders();
    }

    public void onDisable()
    {
        Utilities.deInitializeBorders();

    }


    /**
     * Register Listeners to Bukkit API
     */
    private void initializeListeners()
    {
        PluginManager manager = Bukkit.getServer().getPluginManager();

        manager.registerEvents(new EntityGuard(), this);

    }

    /**
     * Register commands to Bukkit API
     */
    private void initalizeCommands()
    {
        this.getCommand("worldborder").setExecutor(new WorldBorder());

    }

    /**
     * Retriever for singular plugin instance
     * @return Instance of the plugin
     */
    public static Plugin getThisPlugin()
    {
        return Bukkit.getPluginManager().getPlugin("WorldBorder");
    }
}
