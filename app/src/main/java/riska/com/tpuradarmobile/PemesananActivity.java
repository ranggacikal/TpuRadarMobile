package riska.com.tpuradarmobile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseInsertPemesanan;

public class PemesananActivity extends AppCompatActivity {

    @BindView(R.id.edt_tanggal_pesanan)
    EditText edtTanggalPesanan;
    @BindView(R.id.edt_waktu_pesanan)
    EditText edtWaktuPesanan;
    @BindView(R.id.edt_panjang_pesan)
    EditText edtPanjangPesan;
    @BindView(R.id.edt_lebar_pesanan)
    EditText edtLebarPesanan;
    @BindView(R.id.edt_jumlah_petak)
    EditText edtJumlahPetak;
    @BindView(R.id.btn_pilih_bukti_kematian)
    Button btnPilihBuktiKematian;
    @BindView(R.id.img_pilih_bukti)
    ImageView imgPilihBukti;
    @BindView(R.id.btn_pemesanan)
    Button btnPemesanan;

    private SharedPreferencedConfig preferencedConfig;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        ButterKnife.bind(this);
        preferencedConfig = new SharedPreferencedConfig(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        edtTanggalPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnPilihBuktiKematian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        btnPemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerPesanan();
            }
        });

    }

    private void HandlerPesanan() {

        String tanggal_pemesanan = edtTanggalPesanan.getText().toString();
        String waktu_pemesanan = edtWaktuPesanan.getText().toString();
        String panjang = edtPanjangPesan.getText().toString();
        String lebar = edtLebarPesanan.getText().toString();
        String ukuran_petak = panjang+" X "+lebar;
        String jumlah_petak = edtJumlahPetak.getText().toString();
        String image_bukti_kematian = imageToString();
        String validasi_bukti_kematian = "Belum Valid";
        String nama_user = preferencedConfig.getPreferenceNamaLengkap();
        String user_id = preferencedConfig.getPreferenceIdUser();

        if (tanggal_pemesanan.isEmpty()){
            edtTanggalPesanan.setError("Tanggal Pemesanan Tidak Boleh Kosong");
            edtTanggalPesanan.requestFocus();
            return;
        }

        if (waktu_pemesanan.isEmpty()){
            edtWaktuPesanan.setError("Waktu Pemesanan Tidak Boleh Kosong");
            edtWaktuPesanan.requestFocus();
            return;
        }

        if (panjang.isEmpty()){
            edtPanjangPesan.setError("Panjang Petak Tidak Boleh Kosong");
            edtPanjangPesan.requestFocus();
            return;
        }

        if (lebar.isEmpty()){
            edtLebarPesanan.setError("Lebar Petak Tidak Boleh Kosong");
            edtLebarPesanan.requestFocus();
            return;
        }

        if (jumlah_petak.isEmpty()){
            edtJumlahPetak.setError("Jumlah Petak Tidak Boleh Kosong");
            edtJumlahPetak.requestFocus();
            return;
        }

        final ProgressDialog progDialog = ProgressDialog.show(PemesananActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.InsertPemesanan(tanggal_pemesanan, waktu_pemesanan, ukuran_petak, jumlah_petak, image_bukti_kematian, validasi_bukti_kematian, nama_user, user_id).enqueue(new Callback<ResponseInsertPemesanan>() {
            @Override
            public void onResponse(Call<ResponseInsertPemesanan> call, Response<ResponseInsertPemesanan> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                progDialog.dismiss();

                if (status == 1){
                    Toast.makeText(PemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    edtTanggalPesanan.setText("");
                    edtWaktuPesanan.setText("");
                    edtPanjangPesan.setText("");
                    edtLebarPesanan.setText("");
                    edtJumlahPetak.setText("");
                    imgPilihBukti.setImageResource(R.drawable.addimage);
                    startActivity(new Intent(PemesananActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(PemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertPemesanan> call, Throwable t) {

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

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihBukti.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        } else if (bitmap == null) {
            Toast.makeText(this, "Upload Bukti Kematian Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        }
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PemesananActivity.this, MainActivity.class));
        finish();
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggalPesanan.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
