package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseDataResetPassword;

public class ValidasiResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.edt_no_telepon_resetpassword)
    EditText edtNoTeleponResetpassword;
    @BindView(R.id.btn_valid_reset)
    Button btnValidReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_reset_password);
        ButterKnife.bind(this);

        btnValidReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidasiNomerTelepon();
            }
        });
    }

    private void ValidasiNomerTelepon() {

        String No_telpon = edtNoTeleponResetpassword.getText().toString();

        if (No_telpon.isEmpty()){
            edtNoTeleponResetpassword.setError("Nomer Telepon Tidak Boleh Kosong");
            edtNoTeleponResetpassword.requestFocus();
            return;
        }

        ConfigRetrofit.service.DataUserChangePassword(No_telpon).enqueue(new Callback<ResponseDataResetPassword>() {
            @Override
            public void onResponse(Call<ResponseDataResetPassword> call, Response<ResponseDataResetPassword> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Intent intent = new Intent(ValidasiResetPasswordActivity.this, ResetPasswordActivity.class);
                    intent.putExtra(ResetPasswordActivity.EXTRA_ID_USER, response.body().getDataResetPassword().getUserId());
                    intent.putExtra(ResetPasswordActivity.EXTRA_NAMA_LENGKAP, response.body().getDataResetPassword().getNama());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ValidasiResetPasswordActivity.this, "Akun Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataResetPassword> call, Throwable t) {
                Toast.makeText(ValidasiResetPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ValidasiResetPasswordActivity.this, LoginActivity.class));
        finish();
    }
}
