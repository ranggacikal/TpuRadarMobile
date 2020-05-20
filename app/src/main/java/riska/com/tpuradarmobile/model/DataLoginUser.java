package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class DataLoginUser{

	@SerializedName("nama")
	private String nama;

	@SerializedName("email_user")
	private String emailUser;

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("level")
	private String level;

	@SerializedName("no_telpon")
	private String noTelpon;

	@SerializedName("image_user")
	private String imageUser;

	public String getNama(){
		return nama;
	}

	public String getEmailUser(){
		return emailUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public String getUserId(){
		return userId;
	}

	public String getLevel(){
		return level;
	}

	public String getNoTelpon(){
		return noTelpon;
	}

	public String getImageUser(){
		return imageUser;
	}

	@Override
 	public String toString(){
		return 
			"DataLoginUser{" + 
			"nama = '" + nama + '\'' + 
			",email_user = '" + emailUser + '\'' + 
			",password_user = '" + passwordUser + '\'' + 
			",user_id = '" + userId + '\'' + 
			",level = '" + level + '\'' + 
			",no_telpon = '" + noTelpon + '\'' + 
			",image_user = '" + imageUser + '\'' + 
			"}";
		}
}