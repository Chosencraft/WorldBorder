package com.chosencraft.www.worldborder.commands;

import com.chosencraft.www.worldborder.utils.Permissions;
import com.chosencraft.www.worldborder.utils.SettingsManager;
import com.chosencraft.www.worldborder.utils.Utilities;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldBorder implements CommandExecutor
{
    private SettingsManager data = SettingsManager.getData();

    private String prefix = ChatColor.GOLD + "[" + ChatColor.AQUA + "WorldBorder" + ChatColor.GOLD + "] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)
    {

        if ( !(commandSender instanceof  Player))
        {
            // I'll implement full support for CLI later
            commandSender.sendMessage(prefix + "Only usable ingame!");
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission(Permissions.CMD_WORLD_BORDER))
        {
            badPermissionMessage(player);
            return true;
        }

        if (args.length == 0)
        {
            helpMessage(player);
        }
        else
        {
            switch (args[0])
            {
                case "help":
                    helpMessage(player);
                    break;
                case "set":
                    try
                    {
                        if (!player.hasPermission(Permissions.CMD_WORLD_BORDER_SET))
                        {
                            badPermissionMessage(player);
                            break;
                        }
                        if (args.length >= 2)
                        {
                            int radius = Integer.parseInt(args[1]);
                            if (setWorldBorder(player.getWorld(), radius))
                            {
                                player.sendMessage("World Border has been set!");
                            }
                            else
                            {
                                player.sendMessage(prefix + "World Border could already exists!");
                            }
                        }
                        else
                        {
                            player.sendMessage(prefix + "Please provide a valid radius!");
                        }

                    }
                    catch (NumberFormatException exception)
                    {
                        player.sendMessage(prefix + "radius argument " + args[1] +" is not a valid integer!");
                    }
                    break;
                case "clear":
                    if ( player.hasPermission(Permissions.CMD_WORLD_BORDER_DELETE) )
                    {
                        if (deleteWorldorder(player.getWorld()))
                        {
                            player.sendMessage(prefix + "World Border Deleted");
                        }
                        else
                        {
                            player.sendMessage(prefix + "World Border does not exist!");
                        }
                    }
                    else
                    {
                        badPermissionMessage(player);
                    }
                    break;
                default:
                    helpMessage(player);
            }
        }

        return true;
    }

    /**
     * Deletes the world border and removes it from the config
     */
    private boolean deleteWorldorder(World world)
    {

        String uuid = Utilities.stripHyphens(world.getUID());
        // sets config
        if ( data.contains(uuid))
        {
            data.set(uuid , null );
            Utilities.removeBorder(world);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Implements and saves the border for the world
     *
     * @param radius The square radius to set for the world border
     */
    private boolean setWorldBorder(World world, int radius)
    {
        String uuid = Utilities.stripHyphens(world.getUID());
        // sets config
        if ( data.contains(uuid))
        {
            // world border already exists.
            return false;
        }
        else
        {
            data.set(uuid , radius );
            Utilities.setBorder(world, radius);
            return true;

        }

    }

    /**
     * Sends the Receiver the plugin help message
     * @param reciever Receiver of the help message
     */
    private void helpMessage(Player reciever)
    {
        reciever.sendMessage(ChatColor.AQUA + "==========" + ChatColor.GOLD + "[ World Border ]" + ChatColor.AQUA + "==========");
        reciever.sendMessage(ChatColor.GOLD + "/worldborder help             :" + ChatColor.AQUA + "displays this message");
        reciever.sendMessage(ChatColor.GOLD + "/worldborder set <radius>     :" + ChatColor.AQUA + "sets the world border (square)");
        reciever.sendMessage(ChatColor.AQUA + "====================================");
    }

    /**
     * Sends the Receiver the bad permission message
     * @param reciever Receiver of the message
     */
    private void badPermissionMessage(Player reciever)
    {
        reciever.sendMessage(ChatColor.DARK_RED + "You don't have permission to perform this command!");
    }
}
