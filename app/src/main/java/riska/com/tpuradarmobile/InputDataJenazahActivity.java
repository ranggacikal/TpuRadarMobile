package riska.com.tpuradarmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import riska.com.tpuradarmobile.model.ResponseInsertDataJenazah;

public class InputDataJenazahActivity extends AppCompatActivity {

    @BindView(R.id.text_nama_jenazah)
    EditText textNamaJenazah;
    @BindView(R.id.text_noktp_jenazah)
    EditText textNoktpJenazah;
    @BindView(R.id.text_tanggalmeninggal_jenazah)
    EditText textTanggalmeninggalJenazah;
    @BindView(R.id.btn_submit_data)
    Button btnSubmitData;

    private SharedPreferencedConfig preferencedConfig;


    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_jenazah);
        ButterKnife.bind(this);
        preferencedConfig = new SharedPreferencedConfig(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        textTanggalmeninggalJenazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnSubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDataJenazah();
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

                textTanggalmeninggalJenazah.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    private void InputDataJenazah() {

        String nama_jenazah = textNamaJenazah.getText().toString();
        String no_ktp_jenazah = textNoktpJenazah.getText().toString();
        String tanggal_meninggal = textTanggalmeninggalJenazah.getText().toString();
        String user_id = preferencedConfig.getPreferenceIdUser();

        if (nama_jenazah.isEmpty()){
            textNamaJenazah.setError("Nama Jenazah Tidak Boleh Kosong");
            textNamaJenazah.requestFocus();
            return;
        }

        if (no_ktp_jenazah.isEmpty()){
            textNoktpJenazah.setError("No KTP Tidak Boleh Kosong");
            textNoktpJenazah.requestFocus();
            return;
        }

        if (tanggal_meninggal.isEmpty()){
            textTanggalmeninggalJenazah.setError("Tanggal Tidak Boleh Kosong");
            textTanggalmeninggalJenazah.requestFocus();
            return;
        }

        ConfigRetrofit.service.InsertDataJenazah(nama_jenazah, no_ktp_jenazah, tanggal_meninggal, user_id).enqueue(new Callback<ResponseInsertDataJenazah>() {
            @Override
            public void onResponse(Call<ResponseInsertDataJenazah> call, Response<ResponseInsertDataJenazah> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(InputDataJenazahActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputDataJenazahActivity.this, DataPesananActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertDataJenazah> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InputDataJenazahActivity.this, DataPesananActivity.class));
        finish();
    }
}
