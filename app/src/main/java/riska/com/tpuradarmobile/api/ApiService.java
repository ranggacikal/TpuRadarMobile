package riska.com.tpuradarmobile.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import riska.com.tpuradarmobile.model.ResponseDataBerita;
import riska.com.tpuradarmobile.model.ResponseDataInformasi;
import riska.com.tpuradarmobile.model.ResponseDataJenazah;
import riska.com.tpuradarmobile.model.ResponseDataJenazahByUser;
import riska.com.tpuradarmobile.model.ResponseDataPemesanan;
import riska.com.tpuradarmobile.model.ResponseDataResetPassword;
import riska.com.tpuradarmobile.model.ResponseDataSemuaPesanan;
import riska.com.tpuradarmobile.model.ResponseEditBerita;
import riska.com.tpuradarmobile.model.ResponseEditInformasi;
import riska.com.tpuradarmobile.model.ResponseHapusDataBerita;
import riska.com.tpuradarmobile.model.ResponseInsertBerita;
import riska.com.tpuradarmobile.model.ResponseInsertDataJenazah;
import riska.com.tpuradarmobile.model.ResponseInsertPemesanan;
import riska.com.tpuradarmobile.model.ResponseInsertPerpanjangMakam;
import riska.com.tpuradarmobile.model.ResponseLoginUser;
import riska.com.tpuradarmobile.model.ResponseRegisterUser;
import riska.com.tpuradarmobile.model.ResponseResetPassword;
import riska.com.tpuradarmobile.model.ResponseUploadBuktiPembayaran;
import riska.com.tpuradarmobile.model.ResponseValidasiBuktiKematian;
import riska.com.tpuradarmobile.model.ResponseValidasiBuktiPembayaran;
import riska.com.tpuradarmobile.model.ResponseValidasiPerpanjangMakam;

public interface ApiService {

    @FormUrlEncoded
    @POST("Register_user")
    Call<ResponseRegisterUser> RegisterUser(@Field("nama") String nama,
                                            @Field("no_telpon") String no_telpon,
                                            @Field("email_user") String email_user,
                                            @Field("password_user") String password_user,
                                            @Field("level") String level,
                                            @Field("image_user") String image_user);

    @FormUrlEncoded
    @POST("Login_user")
    Call<ResponseLoginUser> LoginUser(@Field("email_user") String email_user,
                                      @Field("password_user") String password_user);

    @GET("DataUserChangePassword/{no_telpon}")
    Call<ResponseDataResetPassword> DataUserChangePassword(@Path("no_telpon") String no_telpon);

    @FormUrlEncoded
    @POST("ChangePassword")
    Call<ResponseResetPassword> ResetPassword(@Field("user_id") String user_id,
                                              @Field("password_user") String password_user);

    @FormUrlEncoded
    @POST("insertPemesanan")
    Call<ResponseInsertPemesanan> InsertPemesanan(@Field("tanggal_pemesanan") String tanggal_pemesanan,
                                                  @Field("jam_pemesanan") String jam_pemesanan,
                                                  @Field("ukuran_petak") String ukuran_petak,
                                                  @Field("jumlah_petak") String jumlah_petak,
                                                  @Field("image_bukti_kematian") String image_bukti_kematian,
                                                  @Field("validasi_bukti_kematian") String validasi_bukti_kematian,
                                                  @Field("nama_user") String nama_user,
                                                  @Field("user_id") String user_id);

    @GET("GetDataPemesanan/{user_id}")
    Call<ResponseDataPemesanan> GetDataPemesanan(@Path("user_id") String user_id);

    @GET("GetAllDataPemesanan")
    Call<ResponseDataSemuaPesanan> GetSemuaPesanan();

    @FormUrlEncoded
    @POST("ValidasiBuktiKematian")
    Call<ResponseValidasiBuktiKematian> ValidasiBuktiKematian(@Field("id_pemesanan") String id_pemesanan,
                                                              @Field("validasi_bukti_kematian") String validasi_bukti_kematian);

    @FormUrlEncoded
    @POST("UploadBuktiPembayaran")
    Call<ResponseUploadBuktiPembayaran> UploadBuktiPembayaran(@Field("id_pemesanan") String id_pemesanan,
                                                              @Field("image_bukti_pembayaran") String image_bukti_pembayaran);

    @FormUrlEncoded
    @POST("insertDataJenazah")
    Call<ResponseInsertDataJenazah> InsertDataJenazah(@Field("nama_jenazah") String nama_jenazah,
                                                      @Field("no_ktp_jenazah") String no_ktp_jenazah,
                                                      @Field("tanggal_meninggal") String tanggal_meninggal,
                                                      @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("ValidasiBuktiPembayaran")
    Call<ResponseValidasiBuktiPembayaran> ValidasiBuktiPembayaran(@Field("id_pemesanan") String id_pemesanan,
                                                                  @Field("validasi_bukti_pembayaran") String validasi_bukti_pembayaran,
                                                                  @Field("status_pemesanan") String status_pemesanan);

    @FormUrlEncoded
    @POST("insertBerita")
    Call<ResponseInsertBerita> InsertBerita(@Field("judul_berita") String judul_berita,
                                            @Field("tanggal_berita") String tanggal_berita,
                                            @Field("isi_berita") String isi_berita,
                                            @Field("image_berita") String image_berita);

    @GET("GetDataBerita")
    Call<ResponseDataBerita> GetBerita();

    @FormUrlEncoded
    @POST("DeleteBerita")
    Call<ResponseHapusDataBerita> HapusBerita(@Field("id_berita") String id_berita);

    @FormUrlEncoded
    @POST("EditBerita")
    Call<ResponseEditBerita> EditBerita(@Field("id_berita") String id_berita,
                                        @Field("judul_berita") String judul_berita,
                                        @Field("tanggal_berita") String tanggal_berita,
                                        @Field("isi_berita") String isi_berita);

    @GET("GetDataJenazah")
    Call<ResponseDataJenazah> GetDataJenazah();

    @GET("GetDataJenazahByUser/{user_id}")
    Call<ResponseDataJenazahByUser> GetDataJenazahByUser(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST("editPerpanjangIzinMakam")
    Call<ResponseInsertPerpanjangMakam> EditPerpanjangIzinMakam(@Field("id_jenazah") String id_jenazah,
                                                                @Field("image_ktp") String image_ktp,
                                                               @Field("image_kk") String image_kk,
                                                               @Field("image_iptm") String image_iptm,
                                                               @Field("image_bukti_pembayaran") String image_bukti_pembayaran);

    @FormUrlEncoded
    @POST("ValidasiPerpanjangMakam")
    Call<ResponseValidasiPerpanjangMakam> ValidasiPerpanjang(@Field("id_jenazah") String id_jenazah,
                                                             @Field("validasi_bukti_pembayaran") String validasi_bukti_pembayaran);

    @GET("GetDataInformasi")
    Call<ResponseDataInformasi> DataInformasi();

    @FormUrlEncoded
    @POST("EditInformasi")
    Call<ResponseEditInformasi> EditInformasi(@Field("id_informasi") String id_informasi,
                                              @Field("tanggal_informasi") String tanggal_informasi,
                                              @Field("isi_informasi") String isi_informasi);
}
