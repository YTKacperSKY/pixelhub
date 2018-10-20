package de.ytkacpersky.bungeehub;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {


    HubCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if(!(cs instanceof ProxiedPlayer)) {
            cs.sendMessage(TextComponent.fromLegacyText("Please use this command only as a Player!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) cs;
        ServerInfo server = BungeeCord.getInstance().getServerInfo(Utils.hubServer);
        if(server == null) {
            System.out.println("The server '" + Utils.hubServer + "' could not be found!");
            return;
        }

        if(server.getName().equalsIgnoreCase(p.getServer().getInfo().getName())) {
            if(!Utils.alreadyConnectedMessage.equals("")) {
                p.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Utils.alreadyConnectedMessage)));
            }
            return;
        }

        p.connect(server);
        if(!Utils.message.equals("")) {
            p.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Utils.message)));
        }
    }
}
