package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class DataJenazahItem{

	@SerializedName("nama_jenazah")
	private String namaJenazah;

	@SerializedName("tanggal_meninggal")
	private String tanggalMeninggal;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("image_kk")
	private String imageKk;

	@SerializedName("image_iptm")
	private String imageIptm;

	@SerializedName("image_bukti_pembayaran")
	private String imageBuktiPembayaran;

	@SerializedName("id_jenazah")
	private String idJenazah;

	@SerializedName("no_ktp_jenazah")
	private String noKtpJenazah;

	@SerializedName("image_ktp")
	private String imageKtp;

	@SerializedName("validasi_bukti_pembayaran")
	private String validasiBuktiPembayaran;

	public void setNamaJenazah(String namaJenazah){
		this.namaJenazah = namaJenazah;
	}

	public String getNamaJenazah(){
		return namaJenazah;
	}

	public void setTanggalMeninggal(String tanggalMeninggal){
		this.tanggalMeninggal = tanggalMeninggal;
	}

	public String getTanggalMeninggal(){
		return tanggalMeninggal;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setImageKk(String imageKk){
		this.imageKk = imageKk;
	}

	public String getImageKk(){
		return imageKk;
	}

	public void setImageIptm(String imageIptm){
		this.imageIptm = imageIptm;
	}

	public String getImageIptm(){
		return imageIptm;
	}

	public void setImageBuktiPembayaran(String imageBuktiPembayaran){
		this.imageBuktiPembayaran = imageBuktiPembayaran;
	}

	public String getImageBuktiPembayaran(){
		return imageBuktiPembayaran;
	}

	public void setIdJenazah(String idJenazah){
		this.idJenazah = idJenazah;
	}

	public String getIdJenazah(){
		return idJenazah;
	}

	public void setNoKtpJenazah(String noKtpJenazah){
		this.noKtpJenazah = noKtpJenazah;
	}

	public String getNoKtpJenazah(){
		return noKtpJenazah;
	}

	public void setImageKtp(String imageKtp){
		this.imageKtp = imageKtp;
	}

	public String getImageKtp(){
		return imageKtp;
	}

	public void setValidasiBuktiPembayaran(String validasiBuktiPembayaran){
		this.validasiBuktiPembayaran = validasiBuktiPembayaran;
	}

	public String getValidasiBuktiPembayaran(){
		return validasiBuktiPembayaran;
	}

	@Override
 	public String toString(){
		return 
			"DataJenazahItem{" + 
			"nama_jenazah = '" + namaJenazah + '\'' + 
			",tanggal_meninggal = '" + tanggalMeninggal + '\'' + 
			",user_id = '" + userId + '\'' + 
			",image_kk = '" + imageKk + '\'' + 
			",image_iptm = '" + imageIptm + '\'' + 
			",image_bukti_pembayaran = '" + imageBuktiPembayaran + '\'' + 
			",id_jenazah = '" + idJenazah + '\'' + 
			",no_ktp_jenazah = '" + noKtpJenazah + '\'' + 
			",image_ktp = '" + imageKtp + '\'' + 
			",validasi_bukti_pembayaran = '" + validasiBuktiPembayaran + '\'' + 
			"}";
		}
}