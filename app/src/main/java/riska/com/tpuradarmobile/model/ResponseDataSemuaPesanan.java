package riska.com.tpuradarmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataSemuaPesanan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("dataSemuaPemesanan")
	private List<DataSemuaPemesananItem> dataSemuaPemesanan;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public List<DataSemuaPemesananItem> getDataSemuaPemesanan(){
		return dataSemuaPemesanan;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataSemuaPesanan{" + 
			"pesan = '" + pesan + '\'' + 
			",dataSemuaPemesanan = '" + dataSemuaPemesanan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}