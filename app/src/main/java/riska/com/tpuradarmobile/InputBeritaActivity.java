package riska.com.tpuradarmobile;

import android.app.DatePickerDialog;
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
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseInsertBerita;

public class InputBeritaActivity extends AppCompatActivity {

    @BindView(R.id.edt_judul_berita)
    EditText edtJudulBerita;
    @BindView(R.id.edt_tanggal_berita)
    EditText edtTanggalBerita;
    @BindView(R.id.edt_isi_berita)
    EditText edtIsiBerita;
    @BindView(R.id.img_pilih_berita)
    ImageView imgPilihBerita;
    @BindView(R.id.btn_pilih_berita)
    Button btnPilihBerita;
    @BindView(R.id.btn_input_berita)
    Button btnInputBerita;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_berita);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        edtTanggalBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnPilihBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        btnInputBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputBerita();
            }
        });
    }

    private void InputBerita() {

        String judul_berita = edtJudulBerita.getText().toString();
        String tanggal_berita = edtTanggalBerita.getText().toString();
        String isi_berita = edtIsiBerita.getText().toString();
        String image_berita = imageToString();

        if (judul_berita.isEmpty()){
            edtJudulBerita.setError("Judul Berita Tidak Boleh Kosong");
            edtJudulBerita.requestFocus();
            return;
        }

        if (tanggal_berita.isEmpty()){
            edtTanggalBerita.setError("Tanggal Berita Tidak Boleh Kosong");
            edtTanggalBerita.requestFocus();
            return;
        }

        if (isi_berita.isEmpty()){
            edtIsiBerita.setError("Judul Berita Tidak Boleh Kosong");
            edtIsiBerita.requestFocus();
            return;
        }

        ConfigRetrofit.service.InsertBerita(judul_berita, tanggal_berita, isi_berita, image_berita).enqueue(new Callback<ResponseInsertBerita>() {
            @Override
            public void onResponse(Call<ResponseInsertBerita> call, Response<ResponseInsertBerita> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(InputBeritaActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    edtJudulBerita.setText("");
                    edtIsiBerita.setText("");
                    edtTanggalBerita.setText("");
                    imgPilihBerita.setImageResource(R.drawable.addimage);
                }else{
                    Toast.makeText(InputBeritaActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertBerita> call, Throwable t) {
                Toast.makeText(InputBeritaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InputBeritaActivity.this, BeritaAdminActivity.class));
        finish();
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
                imgPilihBerita.setImageBitmap(bitmap);
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
            Toast.makeText(this, "Gambar Berita Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        }
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggalBerita.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }
}
