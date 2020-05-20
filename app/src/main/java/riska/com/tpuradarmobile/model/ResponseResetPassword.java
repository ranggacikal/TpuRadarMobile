package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class ResponseResetPassword{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseResetPassword{" + 
			"pesan = '" + pesan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}