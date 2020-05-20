package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class ResponseInsertBerita{

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
			"ResponseInsertBerita{" + 
			"pesan = '" + pesan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}