package com.enderdevelopment.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class ReptilianCommand implements CommandExecutor {

    private final ReptilianManager reptilianManager;

    public ReptilianCommand(ReptilianManager reptilianManager) {
        this.reptilianManager = reptilianManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Usage: /reptilian <command>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "camouflage":
                handleCamouflage(player, args);
                break;
            case "reveal":
                handleReveal(player);
                break;
            default:
                player.sendMessage("Unknown command. Usage: /reptilian <camouflage|reveal>");
        }

        return true;
    }

    private void handleCamouflage(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /reptilian camouflage <type>");
            return;
        }

        try {
            ReptilianManager.ReptilianType type = ReptilianManager.ReptilianType.valueOf(args[1].toUpperCase());
            Villager villager = (Villager) player.getTargetEntity(10);
            if (villager == null) {
                player.sendMessage("No villager targeted.");
                return;
            }

            reptilianManager.camouflageVillager(villager, type);
            player.sendMessage("Villager camouflaged as " + type.name());
        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid reptilian type. Available types: SERPENT, CROCODILE, GECKO, TORTOISE, KOMODO_DRAGON, CHAMELEON, BASILISK, ALPHA_PRIMAL_SPECIES");
        }
    }

    private void handleReveal(Player player) {
        Villager villager = (Villager) player.getTargetEntity(10);
        if (villager == null) {
            player.sendMessage("No villager targeted.");
            return;
        }

        reptilianManager.revealVillager(villager);
        player.sendMessage("Villager revealed.");
    }
}