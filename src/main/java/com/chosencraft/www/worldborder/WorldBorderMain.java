package com.chosencraft.www.worldborder;

import com.chosencraft.www.worldborder.commands.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderMain extends JavaPlugin
{

    public void onEnable()
    {
        initalizeCommands();
    }

    public void onDisable()
    {

    }


    /**
     * Register Listeners to Bukkit API
     */
    private void initializeListeners()
    {

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
        return Bukkit.getPluginManager().getPlugin("WorldBorderMain");
    }
}
