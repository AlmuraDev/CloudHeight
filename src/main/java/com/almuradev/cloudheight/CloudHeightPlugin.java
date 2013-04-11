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
import org.bukkit.plugin.java.JavaPlugin;

public class CloudHeightPlugin extends JavaPlugin {
    private CloudHeightConfiguration config;

	@Override
	public void onEnable() {
        // Handle configuration
        config = new CloudHeightConfiguration(this);
        config.onEnable();

        // Handle events
		Bukkit.getPluginManager().registerEvents(new CloudHeightListener(this), this);
	}

    public CloudHeightConfiguration getConfiguration() {
        return config;
    }
}