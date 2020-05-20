package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class DataInformasiItem{

	@SerializedName("isi_informasi")
	private String isiInformasi;

	@SerializedName("tanggal_informasi")
	private String tanggalInformasi;

	@SerializedName("id_informasi")
	private String idInformasi;

	public void setIsiInformasi(String isiInformasi){
		this.isiInformasi = isiInformasi;
	}

	public String getIsiInformasi(){
		return isiInformasi;
	}

	public void setTanggalInformasi(String tanggalInformasi){
		this.tanggalInformasi = tanggalInformasi;
	}

	public String getTanggalInformasi(){
		return tanggalInformasi;
	}

	public void setIdInformasi(String idInformasi){
		this.idInformasi = idInformasi;
	}

	public String getIdInformasi(){
		return idInformasi;
	}

	@Override
 	public String toString(){
		return 
			"DataInformasiItem{" + 
			"isi_informasi = '" + isiInformasi + '\'' + 
			",tanggal_informasi = '" + tanggalInformasi + '\'' + 
			",id_informasi = '" + idInformasi + '\'' + 
			"}";
		}
}