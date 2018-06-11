package com.chosencraft.www.worldborder.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Set;
import java.util.UUID;

public class Utilities
{

    /** MAGIC NUMBER */
    private static int WARNING_DISTANCE = 5;
    /** Maximum size the border can be according to documentation, confirmed with NBT explorer */
    private static int MAX_BORDER_SIZE = 30000000;

    private static SettingsManager data = SettingsManager.getData();

    /**
     * Enforces the border on the world
     *
     * @param world World to enforce border on
     * @param radius Radius of the border to be set
     */
    public static void setBorder(World world,int radius)
    {
        world.getWorldBorder().setWarningDistance(WARNING_DISTANCE);
        world.getWorldBorder().setSize(radius);
        world.getWorldBorder().setCenter(0,0);
    }

    /**
     * "Removes" the world border by enforcing a massive border
     * @param world world to remove border from
     */
    public static void removeBorder(World world)
    {
        world.getWorldBorder().setWarningDistance(0);
        world.getWorldBorder().setSize(MAX_BORDER_SIZE);
        world.getWorldBorder().setCenter(0,0);
    }

    /**
     *  Enabled the world borders from the data file,
     *  only used onEnable
     */
    public static void initializeBorders()
    {
        // in form of <uuid> <int>
        Set borders = data.getKeys();
        for (Object uuid : borders)
        {
            // In reality this is already a string, but for object enforcement we
            // still use toString(), thankfully you can toString() a string
            int radius = data.get(uuid.toString());
            setBorder(Bukkit.getWorld( UUID.fromString(uuid.toString())), radius);
        }
    }

    /**
     *  Disables the world borders from from the data file,
     *  only used in onDisable
     */
    public static void deInitializeBorders()
    {
        Set borders = data.getKeys();
        for (Object uuid : borders)
        {
            removeBorder(Bukkit.getWorld( UUID.fromString(uuid.toString())));
        }

    }

}
