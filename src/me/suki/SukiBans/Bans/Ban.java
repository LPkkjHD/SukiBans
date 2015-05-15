package me.suki.SukiBans.Bans;


public class Ban {
	private String UUID, Reason, Bannedby;
	private Boolean Unbanned;
	private long BanTime, BannedTime;
	
	public Ban(String UUID, String Reason, String Bannedby, long BanTime, long BannedTime, Boolean unbanned){
		this.UUID = UUID;
		this.Reason = Reason;
		this.Bannedby = Bannedby;
		this.BanTime = BanTime;
		this.BannedTime = BannedTime;
		this.Unbanned = unbanned;
	}
	
	public String getUUID(){
		return UUID;
	}
	public String getReason(){
		return Reason;
	}
	public String getBannedby(){
		return Bannedby;
	}
	public boolean getUnbanned(){
		return Unbanned;
	}
	public long getBanTime(){
		return BanTime;
	}
	public long getBannedTime(){
		return BannedTime;
	}
}
