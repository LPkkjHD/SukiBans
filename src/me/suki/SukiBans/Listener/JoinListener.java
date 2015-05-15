package me.suki.SukiBans.Listener;

import me.suki.SukiBans.Bans.Ban;
import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.Bans.BanSystem.BanType;
import me.suki.SukiBans.MessageManager;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class JoinListener implements Listener{
	@EventHandler
	public void onJoin(LoginEvent e){
		String UUID = e.getConnection().getUniqueId().toString();
		if(BanSystem.isBanned(UUID)){
			Ban ban = BanSystem.getBan(UUID);
			if(ban == null){
				return;
			}
			e.setCancelled(true);
			if(BanSystem.getBanType(UUID) == BanType.PERMANENT){
				e.setCancelReason(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", ban.getReason()).replaceAll("%BANNER%", ban.getBannedby()));
			} else if(BanSystem.getBanType(UUID) == BanType.TEMPORARY){
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				Date unban = new Date((ban.getBanTime() + ban.getBannedTime()));
				e.setCancelReason(MessageManager.getTempBanKickMsg().replaceAll("%REASON%", ban.getReason()).replaceAll("%BANNER%", ban.getBannedby()).replaceAll("%UNBANTIME%", formatter.format(unban)));
			}
		}
	}
}
