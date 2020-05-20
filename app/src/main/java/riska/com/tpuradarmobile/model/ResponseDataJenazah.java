package riska.com.tpuradarmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataJenazah{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("dataJenazah")
	private List<DataJenazahItem> dataJenazah;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataJenazah(List<DataJenazahItem> dataJenazah){
		this.dataJenazah = dataJenazah;
	}

	public List<DataJenazahItem> getDataJenazah(){
		return dataJenazah;
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
			"ResponseDataJenazah{" + 
			"pesan = '" + pesan + '\'' + 
			",dataJenazah = '" + dataJenazah + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}