package riska.com.tpuradarmobile;

import android.app.ProgressDialog;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseInsertPerpanjangMakam;

public class PerpanjangIzinMakamActivity extends AppCompatActivity {

    public static final String EXTRA_ID_JENAZAH = "extraIdJenazah";
    @BindView(R.id.img_pilih_ktp)
    ImageView imgPilihKtp;
    @BindView(R.id.btn_pilih_ktp)
    Button btnPilihKtp;
    @BindView(R.id.img_pilih_kk)
    ImageView imgPilihKk;
    @BindView(R.id.btn_pilih_kk)
    Button btnPilihKk;
    @BindView(R.id.img_pilih_iptm)
    ImageView imgPilihIptm;
    @BindView(R.id.btn_pilih_iptm)
    Button btnPilihIptm;
    @BindView(R.id.img_pilih_buktibayar_izin)
    ImageView imgPilihBuktibayarIzin;
    @BindView(R.id.btn_pilih_buktibayar_izin)
    Button btnPilihBuktibayarIzin;
    @BindView(R.id.btn_perpanjang_izin_makam)
    Button btnPerpanjangIzinMakam;

    private static final int IMG_REQUEST = 1;
    private static final int IMG_REQUEST2 = 2;
    private static final int IMG_REQUEST3 = 3;
    private static final int IMG_REQUEST4  = 4;
    private Bitmap bitmapKtp,bitmapKk, bitmapIptm, bitmapBuktibayar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpanjang_izin_makam);
        ButterKnife.bind(this);

        btnPilihKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihKtp();
            }
        });

        btnPilihKk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihKk();
            }
        });

        btnPilihIptm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihIptm();
            }
        });

        btnPilihBuktibayarIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihBuktiBayar();
            }
        });

        btnPerpanjangIzinMakam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPerpanjangMakam();
            }
        });
    }

    private void EditPerpanjangMakam() {

        String id_jenazah = getIntent().getStringExtra(EXTRA_ID_JENAZAH);
        String image_ktp = imageKtpToString();
        String image_kk = imageKkToString();
        String image_iptm = imageIptmToString();
        String image_bukti_pembayaran = imageBuktiBayarToString();

        final ProgressDialog progDialog = ProgressDialog.show(PerpanjangIzinMakamActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.EditPerpanjangIzinMakam(id_jenazah, image_ktp, image_kk, image_iptm, image_bukti_pembayaran).enqueue(new Callback<ResponseInsertPerpanjangMakam>() {
            @Override
            public void onResponse(Call<ResponseInsertPerpanjangMakam> call, Response<ResponseInsertPerpanjangMakam> response) {
                int status = response.body().getStatus();
                progDialog.dismiss();

                if (status == 1){
                    Toast.makeText(PerpanjangIzinMakamActivity.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(PerpanjangIzinMakamActivity.this, "Insert Data GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertPerpanjangMakam> call, Throwable t) {
                Toast.makeText(PerpanjangIzinMakamActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pilihBuktiBayar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST4);
    }

    private void pilihIptm() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST3);
    }

    private void PilihKk() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST2);
    }

    private void PilihKtp() {

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
                bitmapKtp = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihKtp.setImageBitmap(bitmapKtp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode==IMG_REQUEST2 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmapKk = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihKk.setImageBitmap(bitmapKk);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode==IMG_REQUEST3 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmapIptm = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihIptm.setImageBitmap(bitmapIptm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode==IMG_REQUEST4 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmapBuktibayar = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihBuktibayarIzin.setImageBitmap(bitmapBuktibayar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private String imageKtpToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapKtp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private String imageKkToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapKk.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private String imageIptmToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapIptm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private String imageBuktiBayarToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapBuktibayar.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PerpanjangIzinMakamActivity.this, DataPesananActivity.class));
        finish();
    }
}
