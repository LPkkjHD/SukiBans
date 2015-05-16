package me.suki.SukiBans.commands;

import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.UUID.UUIDNameConverter;
import me.suki.SukiBans.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Ban extends Command {
	public Ban() {
		super("ban");
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
        Runnable run = new Runnable(){
            @Override
            public void run() {
                if (!sender.hasPermission("SukiBans.ban")) return;
                if (args.length < 2) {
                    sender.sendMessage(new TextComponent(MessageManager.getPermamentBanSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                } else {
                    String toBan = UUIDNameConverter.getUUID(args[0]);
                    if (toBan.equals("NO_UUID_FOUND")) {
                        sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    if (BanSystem.isBanned(toBan)) {
                        sender.sendMessage(new TextComponent(MessageManager.getAlreadyBanned().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    String Reason = "";
                    for (int i = 1; i < args.length; i++) {
                        Reason = Reason + args[i] + " ";
                    }
                    sender.sendMessage(new TextComponent(MessageManager.getPermBanCreated().replaceAll("%PREFIX%", MessageManager.getPrefix()).replaceAll("%USER%", args[0]).replaceAll("%REASON%", Reason)));
                    ProxiedPlayer toBanP = ProxyServer.getInstance().getPlayer(args[0]);
                    if(sender instanceof ProxiedPlayer){
                        ProxiedPlayer p = (ProxiedPlayer) sender;
                        BanSystem.Ban(toBan, Reason, -1, p.getName());
                        if (toBanP != null) {
                            toBanP.disconnect(new TextComponent(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", p.getName())));
                        }
                    } else {
                        BanSystem.Ban(toBan, Reason, -1, "CONSOLE");
                        if (toBanP != null) {
                            toBanP.disconnect(new TextComponent(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", "CONSOLE")));
                        }
                    }
                }
            }
        };
        Utils.executeAsync(run);
	}
}
