package com.enderdevelopment.plugin;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ReptilianListener implements Listener {

    private final ReptilianManager reptilianManager;

    public ReptilianListener(ReptilianManager reptilianManager) {
        this.reptilianManager = reptilianManager;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager)) {
            return;
        }

        Player player = event.getPlayer();
        Villager villager = (Villager) event.getRightClicked();

        if (reptilianManager.isReptilian(villager) && reptilianManager.isCamouflaged(villager)) {
            if (reptilianManager.canImmobilize(player)) {
                reptilianManager.revealVillager(villager);
                player.sendMessage("You revealed the camouflaged reptilian!");
            } else {
                player.sendMessage("You need a special item to reveal the reptilian.");
            }
        }
    }
}