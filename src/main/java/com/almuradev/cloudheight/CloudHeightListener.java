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

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
        SpoutPlayer player = event.getPlayer();
        SkyManager sky = SpoutManager.getSkyManager();
        if (!player.hasPermission("cloudheight.ignore")) {
            if (plugin.getConfiguration().getAll().containsKey(event.getPlayer().getWorld())) {
                sky.setCloudHeight(player, plugin.getConfiguration().get(event.getPlayer().getWorld()));
            }
        }
    }
}
