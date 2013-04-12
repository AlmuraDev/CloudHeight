/*
 * This file is part of CloudHeight.
 *
 * © 2013 AlmuraDev <http://www.almuradev.com/>
 * CloudHeight is licensed under the GNU General Public License.
 *
 * CloudHeight is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CloudHeight is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License. If not,
 * see <http://www.gnu.org/licenses/> for the GNU General Public License.
 */
package com.almuradev.cloudheight;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SkyManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CloudHeightListener implements Listener {
	private final CloudHeightPlugin plugin;

	public CloudHeightListener(CloudHeightPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onSpoutCraftEnable(final SpoutCraftEnableEvent event) {
		setSky(event.getPlayer(), event.getPlayer().getWorld());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(final PlayerTeleportEvent event) {
		if (!event.getFrom().getWorld().equals(event.getTo().getWorld())) {
			setSky(event.getPlayer(), event.getPlayer().getWorld());
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		setSky(event.getPlayer(), event.getPlayer().getWorld());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPortal(final PlayerPortalEvent event) {
		setSky(event.getPlayer(), event.getPlayer().getWorld());
	}

	public void setSky(Player player, World world) { 
		SpoutPlayer sPlayer = (SpoutPlayer) player;
		if (sPlayer.isSpoutCraftEnabled()) { // Skip if user isn't using Spoutcraft
			SkyManager sky = SpoutManager.getSkyManager();
			if (!VaultUtil.hasPermission(player.getName(), player.getWorld().getName(), "cloudheight.ignore")) {
				if (plugin.getConfiguration().getAll().containsKey(world)) {
					sky.setCloudHeight(sPlayer, plugin.getConfiguration().get(world));    				
				}
			}	
		}
	}
}
