package me.suki.SukiBans.Mutes;

/**
 * Created by DatSukii on 15.05.2015 at 13:20
 */
public class Mute {
    private String UUID, Reason, Mutedby;
    private Boolean Unmuted;
    private long MuteTime, MutedTime;
    private int MuteID;

    public Mute(int MuteID, String UUID, String Reason, String Mutedby, long Mutetime, long Mutedtime, Boolean unmuted){
        this.MuteID = MuteID;
        this.UUID = UUID;
        this.Reason = Reason;
        this.Mutedby = Mutedby;
        this.MuteTime = Mutetime;
        this.MutedTime = Mutedtime;
        this.Unmuted = unmuted;
    }

    @SuppressWarnings("unused")
    public int getMuteID() {
        return MuteID;
    }

    @SuppressWarnings("unused")
    public String getUUID(){
        return UUID;
    }

    @SuppressWarnings("unused")
    public String getReason(){
        return Reason;
    }

    @SuppressWarnings("unused")
    public String getMutedby(){
        return Mutedby;
    }

    @SuppressWarnings("unused")
    public boolean getUnmuted(){
        return Unmuted;
    }

    @SuppressWarnings("unused")
    public long getMuteTime(){
        return MuteTime;
    }

    @SuppressWarnings("unused")
    public long getMutedTime(){
        return MutedTime;
    }
}
