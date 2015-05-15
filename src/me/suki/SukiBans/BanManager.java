package me.suki.SukiBans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.Listener.JoinListener;
import me.suki.SukiBans.commands.Ban;
import me.suki.SukiBans.commands.TempBan;
import me.suki.SukiBans.commands.Unban;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BanManager extends Plugin{
	private static BanManager instance;
	@Override
	public void onEnable() {
		instance = this;
		loadMySQL();
		BanSystem.createTable();
		loadMessages();
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Ban());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Unban());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new TempBan());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new JoinListener());
	}
	public static BanManager getInstance(){
		return instance;
	}
	
	private void loadMessages(){
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		File cfgFile = new File(getDataFolder(), "Messages.yml");
		if(!cfgFile.exists()){
			try {
				System.out.println("Try to Copy default Messages..");
				Files.copy(getResourceAsStream("Messages.yml"), cfgFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Configuration cfg = null;
		try {
			cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(cfgFile);
		} catch (Exception e) {
			System.err.println("Could not load Messages.yml!");
			e.printStackTrace();
		}
		MessageManager.initMsg(cfg);
	}
	private void loadMySQL(){
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		File cfgFile = new File(getDataFolder(), "MySQL.yml");
		if(!cfgFile.exists()){
			try {
				System.out.println("Try to Copy default Config..");
				Files.copy(getResourceAsStream("MySQL.yml"), cfgFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Configuration cfg = null;
		try {
			cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "MySQL.yml"));
		} catch (Exception e) {
			System.err.println("Could not load MySQL.yml!");
			e.printStackTrace();
		}
		
		try {
			MySQL.connect(cfg.getString("Host"), cfg.getString("Username"), cfg.getString("Password"), cfg.getString("Database"), cfg.getInt("Port"));
		} catch (Exception e) {
			System.err.println("Could not connect to MySQL");
			e.printStackTrace();
		}
		
	}
}
