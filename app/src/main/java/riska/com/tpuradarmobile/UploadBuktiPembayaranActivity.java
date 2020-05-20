package riska.com.tpuradarmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseUploadBuktiPembayaran;

public class UploadBuktiPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.card_pesanan_upload)
    CardView cardPesananUpload;
    @BindView(R.id.card_bayar_upload)
    CardView cardBayarUpload;
    @BindView(R.id.card_berhasil_upload)
    CardView cardBerhasilUpload;
    @BindView(R.id.img_upload_bukti_pembayaran)
    ImageView imgUploadBuktiPembayaran;
    @BindView(R.id.btn_pilih_bukti_transfer)
    Button btnPilihBuktiTransfer;
    @BindView(R.id.btn_upload_bukti_transfer)
    Button btnUploadBuktiTransfer;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    public static final String EXTRA_ID_PEMESANAN = "extraIdPemesanan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti_pembayaran);
        ButterKnife.bind(this);

        btnPilihBuktiTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        btnUploadBuktiTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadBuktiPembayaran();
            }
        });
    }

    private void UploadBuktiPembayaran() {

        String id_pemesanan = getIntent().getStringExtra(EXTRA_ID_PEMESANAN);
        String image_bukti_pembayaran = imageToString();

        ConfigRetrofit.service.UploadBuktiPembayaran(id_pemesanan, image_bukti_pembayaran).enqueue(new Callback<ResponseUploadBuktiPembayaran>() {
            @Override
            public void onResponse(Call<ResponseUploadBuktiPembayaran> call, Response<ResponseUploadBuktiPembayaran> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Toast.makeText(UploadBuktiPembayaranActivity.this, "Upload Bukti Pembayaran Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UploadBuktiPembayaranActivity.this, InputDataJenazahActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseUploadBuktiPembayaran> call, Throwable t) {

            }
        });
    }

    private void PilihGambar() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgUploadBuktiPembayaran.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }else if (bitmap==null){
            Toast.makeText(this, "Gambar Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
