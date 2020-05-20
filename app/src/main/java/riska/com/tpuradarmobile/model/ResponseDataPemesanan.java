package riska.com.tpuradarmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataPemesanan{

	@SerializedName("dataPemesananUser")
	private List<DataPemesananUserItem> dataPemesananUser;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public List<DataPemesananUserItem> getDataPemesananUser(){
		return dataPemesananUser;
	}

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataPemesanan{" + 
			"dataPemesananUser = '" + dataPemesananUser + '\'' + 
			",pesan = '" + pesan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}