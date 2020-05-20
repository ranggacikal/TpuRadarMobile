package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseEditInformasi;

public class EditInformasiActivity extends AppCompatActivity {

    public static final String EXTRA_ID_INFORMASI = "extraIdInformasi";
    public static final String EXTRA_ISI_INFORMASI = "extraIsiInformasi";
    @BindView(R.id.edt_isi_informasi)
    EditText edtIsiInformasi;
    @BindView(R.id.btn_edit_informasi)
    Button btnEditInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_informasi);
        ButterKnife.bind(this);

        edtIsiInformasi.setText(getIntent().getStringExtra(EXTRA_ISI_INFORMASI));

        btnEditInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_informasi = getIntent().getStringExtra(EXTRA_ID_INFORMASI);
                Calendar calendar = Calendar.getInstance();
                String tanggal_informasi = DateFormat.getDateInstance().format(calendar.getTime());
                String isi_informasi = edtIsiInformasi.getText().toString();

                ConfigRetrofit.service.EditInformasi(id_informasi, tanggal_informasi, isi_informasi).enqueue(new Callback<ResponseEditInformasi>() {
                    @Override
                    public void onResponse(Call<ResponseEditInformasi> call, Response<ResponseEditInformasi> response) {
                        int status = response.body().getStatus();

                        if (status == 1){
                            Toast.makeText(EditInformasiActivity.this, "Edit Informasi Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditInformasiActivity.this, InformasiAdminActivity.class));
                            finish();
                        }else{
                            Toast.makeText(EditInformasiActivity.this, "Edit Informasi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEditInformasi> call, Throwable t) {
                        Toast.makeText(EditInformasiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditInformasiActivity.this, InformasiAdminActivity.class));
        finish();
    }
}
