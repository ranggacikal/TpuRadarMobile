package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class ResponseRegisterUser{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public boolean isSukses(){
		return sukses;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegisterUser{" + 
			"pesan = '" + pesan + '\'' + 
			",sukses = '" + sukses + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}