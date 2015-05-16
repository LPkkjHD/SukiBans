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

public class TempBan extends Command{

	public TempBan() {
		super("tempban");
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if(!sender.hasPermission("SukiBans.Tempban")) { sender.sendMessage(new TextComponent(MessageManager.getNoPerm().replaceAll("%PREFIX%", MessageManager.getPrefix()))); return;}
                if(args.length >= 3){
                    String toBan = UUIDNameConverter.getUUID(args[0]);
                    if(toBan.equals("NO_UUID_FOUND")){
                        sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    long time = Utils.parseDuration(args[1]);
                    if(time == Long.MIN_VALUE){
                        sender.sendMessage(new TextComponent(MessageManager.getInvalidTimeFormat().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    String Reason = "";
                    for(int i = 2; i < args.length; i++){
                        Reason = Reason + args[i] + " ";
                    }
                    if(sender instanceof ProxiedPlayer){
                        BanSystem.Ban(toBan, Reason, time, sender.getName());
                    } else {
                        BanSystem.Ban(toBan, Reason, time, "CONSOLE");
                    }
                    long[] units = convertMillis(time);
                    sender.sendMessage(new TextComponent(MessageManager.getTempBanCreated().replaceAll("%PREFIX%", MessageManager.getPrefix()).replaceAll("%USER%", args[0]).replaceAll("%TIME%", units[4] + " Days " + units[3] + " Hours " + units[2] + " Minutes " + units[1] + " Seconds").replaceAll("%REASON%", Reason)));
                    ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(args[0]);
                    if(pp != null){
                        if(sender instanceof ProxiedPlayer){
                            pp.disconnect(new TextComponent(MessageManager.getTempBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", sender.getName()).replaceAll("%BANTIME%", Utils.translateLongToString(System.currentTimeMillis() + time))));
                        } else {
                            pp.disconnect(new TextComponent(MessageManager.getTempBanKickMsg().replaceAll("%REASON%", Reason).replaceAll("%BANNER%", "CONSOLE").replaceAll("%BANTIME%", Utils.translateLongToString(System.currentTimeMillis() + time))));
                        }
                    }
                } else {
                    sender.sendMessage(new TextComponent(MessageManager.getTempBanSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                }
            }
        };
        Utils.executeAsync(run);
	}
	private long[] convertMillis(long millis){
		long[] toReturn = new long[5];
		toReturn[0] = millis % 1000;		//Millis
		toReturn[1] = millis / 1000 % 60;	//Seconds
		toReturn[2] = millis / 60000 % 60;	//Minutes
		toReturn[3] = millis / 3600000 % 60;//Hours
		toReturn[4] = millis / 864000000;	//Months
		return toReturn;
	}
}
