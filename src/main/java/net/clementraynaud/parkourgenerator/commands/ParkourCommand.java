package net.clementraynaud.parkourgenerator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.clementraynaud.parkourgenerator.ParkourGenerator.PREFIX;

public class ParkourCommand implements CommandExecutor, TabCompleter {

    private static final String[] DIFFICULTIES = new String[]{"normal", "hard", "expert", "master", "insane"};
    private static final String[] TYPES = new String[]{"downward", "flat", "upward"};

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(PREFIX + "§7Usage: /parkour <difficulty> <type> [size]");
        sender.sendMessage("§7Difficulties: " + String.join(", ", DIFFICULTIES));
        sender.sendMessage("§7Types: " + String.join(", ", TYPES));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cThis command is not executable from the console.");
            return true;
        }
        if (args.length != 2 || Stream.of(DIFFICULTIES).noneMatch(s -> s.equalsIgnoreCase(args[0])) && Stream.of(TYPES).noneMatch(s -> s.equalsIgnoreCase(args[1]))) {
            sendUsage(sender);
            return true;
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Stream.of(DIFFICULTIES).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        } else if (args.length == 2 && Stream.of(DIFFICULTIES).anyMatch(s -> s.equalsIgnoreCase(args[0]))) {
            return Stream.of(TYPES).filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
