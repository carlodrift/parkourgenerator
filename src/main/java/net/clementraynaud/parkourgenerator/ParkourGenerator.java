/*
 * Copyright 2022 Clément "carlodrift" Raynaud and contributors
 *
 * This file is part of ParkourGenerator.
 *
 * ParkourGenerator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ParkourGenerator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ParkourGenerator.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.clementraynaud.parkourgenerator;

import net.clementraynaud.parkourgenerator.commands.ParkourCommand;
import net.clementraynaud.parkourgenerator.util.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class ParkourGenerator extends JavaPlugin {

    public static final String PREFIX = "§dParkourGenerator §8• ";
    private static ParkourGenerator instance;

    public static ParkourGenerator getInstance() {
        return instance;
    }

    public static void setInstance(ParkourGenerator instance) {
        ParkourGenerator.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        saveDefaultConfig();
        this.getCommand("parkour").setExecutor(new ParkourCommand());
        this.getCommand("parkour").setTabCompleter(new ParkourCommand());
        new Metrics(this, 14005);
        new UpdateChecker(this, 83295).getVersion(version -> {
            if (!this.getDescription().getVersion().equals(version)) {
                getLogger().warning("An update is available: https://www.spigotmc.org/resources/parkourgenerator.83295/");
            }
        });
    }
}
