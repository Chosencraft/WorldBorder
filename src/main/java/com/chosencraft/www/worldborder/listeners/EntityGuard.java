package com.chosencraft.www.worldborder.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.List;

public class EntityGuard implements Listener
{

    @EventHandler
    public void onEntityWallCollision(ProjectileHitEvent hitEvent)
    {
        Location loc = hitEvent.getHitBlock().getLocation();

        if (!loc.getWorld().getWorldBorder().isInside(loc))
        {
            hitEvent.getEntity().remove();
        }
    }

    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent spawnEvent)
    {
        Location loc = spawnEvent.getLocation();

        if (!loc.getWorld().getWorldBorder().isInside(loc))
        {
            spawnEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent teleportEvent)
    {
        Location loc = teleportEvent.getTo();

        if (!loc.getWorld().getWorldBorder().isInside(loc))
        {
            teleportEvent.setCancelled(true);
        }

    }
}
