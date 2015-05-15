package me.suki.SukiBans.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class UUIDNameConverter {
	static HashMap<String, String> uuidCache = new HashMap<String, String>();
    private static JSONParser jsonparser = new JSONParser();

	public static JSONObject getJSONObject(String name){
		JSONObject array = null;
		try{
		      HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/"+name).openConnection();
		      array = (JSONObject)new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
		} catch(Exception err){
            err.printStackTrace();
        }
		return array;
	}

	public static String getUUID(String name){
		if(!uuidCache.containsKey(name)){
			HttpsURLConnection connection;
			JSONObject object = null;
			try {
				connection = (HttpsURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/"+name).openConnection();
			    object = (JSONObject)new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
			} catch (Exception err){
                err.printStackTrace();
            }
			if(object != null){
				if(object.get("id") !=  null){
					return (String) object.get("id");
				}
			}
		} else {
			return uuidCache.get(name);
		}
		return "NO_UUID_FOUND";
	}

    private static JSONArray getPreviousNamesAsJSONArray(String UUID){
		JSONArray array = null;
		try {
			HttpsURLConnection conn = (HttpsURLConnection) new URL("https://api.mojang.com/user/profiles/"+UUID.replaceAll("-", "")+"/names").openConnection();
			array = (JSONArray) jsonparser.parse(new InputStreamReader(conn.getInputStream()));
		} catch (Exception err){
			err.printStackTrace();
		}
		return array;
	}
	@SuppressWarnings("unchecked")
	public static String getName(String uuid){
		String nameLatest = "NO_NAME_FOUND";
	      try
	      {
	        JSONArray array = getPreviousNamesAsJSONArray(uuid.toString());
	        if (array != null)
	        {
	          Iterator<JSONObject> iterator = array.iterator();
	          while (iterator.hasNext())
	          {
                  JSONObject obj = iterator.next();
                  nameLatest = (String)obj.get("name");
	          }
	        }
	      }
	      catch (Exception ioe)
	      {
	    	  ioe.printStackTrace();
	      }
	      return nameLatest;
	  }
}
