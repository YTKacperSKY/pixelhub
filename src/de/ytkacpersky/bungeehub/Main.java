package de.ytkacpersky.bungeehub;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;

public class Main extends Plugin{

    @Override
    public void onEnable() {
        Utils.loadProperties();
        Utils.message = Utils.properties.getProperty("message", "&c&lPixelwarp &8&l> &7You are now at the hub.");
        Utils.alreadyConnectedMessage = Utils.properties.getProperty("message.already.connected", "&c&lPixelhub &8&l> &7You are already at the hub.");
        Utils.hubServer = Utils.properties.getProperty("hubserver", "lobby");
        Utils.permission = Utils.properties.getProperty("permission", "");
        String[] aliases = Utils.properties.getProperty("aliases", "").split(",");
        ArrayList<String> aliasesList = new ArrayList<>();
        for(int i = 0; i < aliases.length; i++) {
            if(i == 0) {
                Utils.command = aliases[i].toLowerCase();
                continue;
            }
            aliasesList.add(aliases[i].toLowerCase());
        }
        Utils.aliases = aliasesList.stream().toArray(String[]::new);

        getProxy().getPluginManager().registerCommand(this, new HubCommand(Utils.command.equals("") ? "hub" : Utils.command, Utils.permission, Utils.aliases));
    }

    @Override
    public void onDisable() {

    }

}
