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

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CloudHeightConfiguration {
    private final CloudHeightPlugin plugin;
    private FileConfiguration config;
    private String[] keys;
    private final Map<World, Integer> WORLDS = new HashMap<World, Integer>();

    public CloudHeightConfiguration(CloudHeightPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        // Read in default config.yml
        if (!new File(plugin.getDataFolder(), "config.yml").exists()) {
            plugin.saveDefaultConfig();
        }
        config = plugin.getConfig();
        load();
    }

    protected void load() {
        ConfigurationSection section = config.getConfigurationSection("worlds");
        Set<String> temp = section.getKeys(false);
        keys = temp.toArray(new String[temp.size()]);
        for (String worldName : keys) {
            final World world = Bukkit.getWorld(worldName);
            if (world == null) {
                continue;
            }
            WORLDS.put(world, section.getConfigurationSection(worldName).getInt("height", 170));
        }
    }

    public int get(World world){
        if (world == null) {
            throw new IllegalArgumentException("World cannot be null!");
        }
        return WORLDS.get(world);
    }

    public Map<World, Integer> getAll() {
        return Collections.unmodifiableMap(WORLDS);
    }
}