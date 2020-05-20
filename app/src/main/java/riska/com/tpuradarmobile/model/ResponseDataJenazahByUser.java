package riska.com.tpuradarmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataJenazahByUser{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("dataJenazahByUser")
	private List<DataJenazahByUserItem> dataJenazahByUser;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataJenazahByUser(List<DataJenazahByUserItem> dataJenazahByUser){
		this.dataJenazahByUser = dataJenazahByUser;
	}

	public List<DataJenazahByUserItem> getDataJenazahByUser(){
		return dataJenazahByUser;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataJenazahByUser{" + 
			"pesan = '" + pesan + '\'' + 
			",dataJenazahByUser = '" + dataJenazahByUser + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}