package me.suki.SukiBans;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

public class MessageManager {
	private static String 
	Prefix, 
	NoPerm, 
	PermBanCreated, 
	TempBanCreated, 
	PermBanKickMsg, 
	TempBanKickMsg, 
	PermamentBanSyntax, 
	NoUUID,
	UnbanSyntax,
	UnbanSucces,
	InvalidTimeFormat,
	TempBanSyntax,
	AlreadyBanned;
	
	
	public static void initMsg(Configuration cfg){
		if(cfg == null) return;
		Prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
		NoPerm = ChatColor.translateAlternateColorCodes('&', cfg.getString("NoPermission"));
		PermBanCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermamentBanSucces"));
		TempBanCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("TemporaryBanSucces"));
		PermBanKickMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickMessagePermanent"));
		TempBanKickMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickMessageTemporary"));
		PermamentBanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermanentBanSyntax"));
		TempBanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("TemporaryBanSyntax"));
		NoUUID = ChatColor.translateAlternateColorCodes('&', cfg.getString("NoUUIDFound"));
		UnbanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnBanSyntax"));
		UnbanSucces = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnbanSucces"));
		InvalidTimeFormat = ChatColor.translateAlternateColorCodes('&', cfg.getString("InvalidTime"));
		AlreadyBanned = ChatColor.translateAlternateColorCodes('&', cfg.getString("AlreadyBanned"));
	}

	public static String getPrefix() {
		return Prefix;
	}

	public static String getNoPerm() {
		return NoPerm;
	}

	public static String getPermBanCreated() {
		return PermBanCreated;
	}

	public static String getTempBanCreated() {
		return TempBanCreated;
	}

	public static String getPermBanKickMsg() {
		return PermBanKickMsg;
	}

	public static String getTempBanKickMsg() {
		return TempBanKickMsg;
	}

	public static String getPermamentBanSyntax() {
		return PermamentBanSyntax;
	}

	public static String getNoUUID() {
		return NoUUID;
	}

	public static String getUnbanSyntax() {
		return UnbanSyntax;
	}

	public static String getUnbanSucces() {
		return UnbanSucces;
	}

	public static String getInvalidTimeFormat() {
		return InvalidTimeFormat;
	}

	public static String getTempBanSyntax() {
		return TempBanSyntax;
	}

	public static String getAlreadyBanned() {
		return AlreadyBanned;
	}
}
