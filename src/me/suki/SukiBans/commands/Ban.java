package me.suki.SukiBans.commands;

import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.UUID.UUIDNameConverter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Ban extends Command{
	public Ban() {
		super("ban");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("SukiBans.ban")) return;
		if(sender instanceof ProxiedPlayer){
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length < 2){
				p.sendMessage(new TextComponent(MessageManager.getPermamentBanSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
			} else {
				String toBan = UUIDNameConverter.getUUID(args[0]);
				if(toBan == "NO_UUID_FOUND"){
					p.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
					return;
				}
				if(BanSystem.isBanned(toBan)){
					p.sendMessage(new TextComponent("This Player is already Banned!"));
					return;
				}
				String Reason = "";
				for(int i = 1; i < args.length; i++){
					Reason = Reason + args[i] + " ";
				}
				BanSystem.Ban(toBan, Reason, -1, p.getName());
				ProxiedPlayer toBanP = ProxyServer.getInstance().getPlayer(args[0]);
				if(toBanP != null){
					toBanP.disconnect(new TextComponent(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", p.getName())));
				}
			}
		} else {
			if(args.length < 2){
				sender.sendMessage(new TextComponent(MessageManager.getPermamentBanSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
			} else {
				String toBan = UUIDNameConverter.getUUID(args[0]);
//				String toBan = ProxyServer.getInstance().getPlayer(args[0]).getUniqueId().toString();
				if(toBan == "NO_UUID_FOUND"){
					sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
					return;
				}
				if(BanSystem.isBanned(toBan)){
					sender.sendMessage(new TextComponent("This Player is already Banned!"));
					return;
				}
				
				String Reason = "";
				for(int i = 1; i < args.length; i++){
					Reason = Reason + args[i] + " ";
				}
				BanSystem.Ban(toBan, Reason, -1, "CONSOLE");
				ProxyServer.getInstance().getPlayer(args[0]).disconnect(new TextComponent(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", "CONSOLE")));
			}
		}
	}
}
