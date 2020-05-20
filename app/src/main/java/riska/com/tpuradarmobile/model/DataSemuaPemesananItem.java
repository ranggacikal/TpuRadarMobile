package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class DataSemuaPemesananItem{

	@SerializedName("status_pemesanan")
	private String statusPemesanan;

	@SerializedName("id_pemesanan")
	private String idPemesanan;

	@SerializedName("tanggal_pemesanan")
	private String tanggalPemesanan;

	@SerializedName("validasi_bukti_kematian")
	private String validasiBuktiKematian;

	@SerializedName("jumlah_petak")
	private String jumlahPetak;

	@SerializedName("image_bukti_kematian")
	private String imageBuktiKematian;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("image_bukti_pembayaran")
	private String imageBuktiPembayaran;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("jam_pemesanan")
	private String jamPemesanan;

	@SerializedName("ukuran_petak")
	private String ukuranPetak;

	@SerializedName("validasi_bukti_pembayaran")
	private String validasiBuktiPembayaran;

	public String getStatusPemesanan(){
		return statusPemesanan;
	}

	public String getIdPemesanan(){
		return idPemesanan;
	}

	public String getTanggalPemesanan(){
		return tanggalPemesanan;
	}

	public String getValidasiBuktiKematian(){
		return validasiBuktiKematian;
	}

	public String getJumlahPetak(){
		return jumlahPetak;
	}

	public String getImageBuktiKematian(){
		return imageBuktiKematian;
	}

	public String getUserId(){
		return userId;
	}

	public String getImageBuktiPembayaran(){
		return imageBuktiPembayaran;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public String getJamPemesanan(){
		return jamPemesanan;
	}

	public String getUkuranPetak(){
		return ukuranPetak;
	}

	public String getValidasiBuktiPembayaran(){
		return validasiBuktiPembayaran;
	}

	@Override
 	public String toString(){
		return 
			"DataSemuaPemesananItem{" + 
			"status_pemesanan = '" + statusPemesanan + '\'' + 
			",id_pemesanan = '" + idPemesanan + '\'' + 
			",tanggal_pemesanan = '" + tanggalPemesanan + '\'' + 
			",validasi_bukti_kematian = '" + validasiBuktiKematian + '\'' + 
			",jumlah_petak = '" + jumlahPetak + '\'' + 
			",image_bukti_kematian = '" + imageBuktiKematian + '\'' + 
			",user_id = '" + userId + '\'' + 
			",image_bukti_pembayaran = '" + imageBuktiPembayaran + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",jam_pemesanan = '" + jamPemesanan + '\'' + 
			",ukuran_petak = '" + ukuranPetak + '\'' + 
			",validasi_bukti_pembayaran = '" + validasiBuktiPembayaran + '\'' + 
			"}";
		}
}