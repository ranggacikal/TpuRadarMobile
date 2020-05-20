package riska.com.tpuradarmobile.api;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.BeritaAdminActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.model.ResponseEditBerita;
import riska.com.tpuradarmobile.model.ResponseHapusDataBerita;

public class EditHapusActivity extends AppCompatActivity {
    public static final String EXTRA_ID_BERITA = "extraIdBerita";
    public static final String EXTRA_JUDUL_BERITA = "extraJudulBerita";
    public static final String EXTRA_TANGGAL_BERITA = "extraTanggalBerita";
    public static final String EXTRA_ISI_BERITA = "extraIsiBerita";
    public static final String EXTRA_IMAGE_BERITA = "extraImageBerita";
    @BindView(R.id.img_edit_hapus_berita)
    ImageView imgEditHapusBerita;
    @BindView(R.id.edt_judul_edit_hapus_berita)
    EditText edtJudulEditHapusBerita;
    @BindView(R.id.edt_tanggal_edit_hapus_berita)
    EditText edtTanggalEditHapusBerita;
    @BindView(R.id.edt_isi_edit_hapus_berita)
    EditText edtIsiEditHapusBerita;
    @BindView(R.id.btn_edit_berita)
    Button btnEditBerita;
    @BindView(R.id.btn_hapus_berita)
    Button btnHapusBerita;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hapus);
        ButterKnife.bind(this);

        final String imageLink = getIntent().getStringExtra(EXTRA_IMAGE_BERITA);

        Glide.with(EditHapusActivity.this)
                .load(imageLink)
                .error(R.mipmap.ic_launcher_round)
                .into(imgEditHapusBerita);

        edtJudulEditHapusBerita.setText(getIntent().getStringExtra(EXTRA_JUDUL_BERITA));
        edtTanggalEditHapusBerita.setText(getIntent().getStringExtra(EXTRA_TANGGAL_BERITA));
        edtIsiEditHapusBerita.setText(getIntent().getStringExtra(EXTRA_ISI_BERITA));

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        edtTanggalEditHapusBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnEditBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditBerita();
            }
        });

        btnHapusBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusBerita();
            }
        });
    }

    private void HapusBerita() {
        String id_berita = getIntent().getStringExtra(EXTRA_ID_BERITA);

        ConfigRetrofit.service.HapusBerita(id_berita).enqueue(new Callback<ResponseHapusDataBerita>() {
            @Override
            public void onResponse(Call<ResponseHapusDataBerita> call, Response<ResponseHapusDataBerita> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Toast.makeText(EditHapusActivity.this, "Hapus Berita Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditHapusActivity.this, BeritaAdminActivity.class));
                    finish();
                }else{
                    Toast.makeText(EditHapusActivity.this, "Hapus Berita GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusDataBerita> call, Throwable t) {
                Toast.makeText(EditHapusActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EditBerita() {

        String id_berita = getIntent().getStringExtra(EXTRA_ID_BERITA);
        String judul_berita = edtJudulEditHapusBerita.getText().toString();
        String tanggal_berita = edtTanggalEditHapusBerita.getText().toString();
        String isi_berita = edtIsiEditHapusBerita.getText().toString();

        if (judul_berita.isEmpty()){
            edtJudulEditHapusBerita.setError("Judul Tidak Boleh Kosong");
            edtJudulEditHapusBerita.requestFocus();
            return;
        }

        if (tanggal_berita.isEmpty()){
            edtTanggalEditHapusBerita.setError("Tanggal Tidak Boleh Kosong");
            edtTanggalEditHapusBerita.requestFocus();
            return;
        }

        if (isi_berita.isEmpty()){
            edtIsiEditHapusBerita.setError("Isi Berita Tidak Boleh Kosong");
            edtIsiEditHapusBerita.requestFocus();
            return;
        }

        ConfigRetrofit.service.EditBerita(id_berita, judul_berita, tanggal_berita, isi_berita).enqueue(new Callback<ResponseEditBerita>() {
            @Override
            public void onResponse(Call<ResponseEditBerita> call, Response<ResponseEditBerita> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Toast.makeText(EditHapusActivity.this, "Berhasil Edit Berita", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditHapusActivity.this, BeritaAdminActivity.class));
                    finish();
                }else{
                    Toast.makeText(EditHapusActivity.this, "Edit Berita GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEditBerita> call, Throwable t) {
                Toast.makeText(EditHapusActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggalEditHapusBerita.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditHapusActivity.this, BeritaAdminActivity.class));
        finish();
    }
}
