package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseResetPassword;

public class ResetPasswordActivity extends AppCompatActivity {

    public static final String EXTRA_ID_USER = "extraIdUser";
    public static final String EXTRA_NAMA_LENGKAP = "extraNamaLengkap";
    public static final String EXTRA_EMAIL_USER = "extraEmailUser";
    public static final String EXTRA_NO_TELEPON = "extraNoTelpon";
    public static final String EXTRA_PASSWORD_USER = "extraPassword";
    @BindView(R.id.text_nama_lengkap)
    TextView textNamaLengkap;
    @BindView(R.id.edt_password_reset)
    EditText edtPasswordReset;
    @BindView(R.id.edt_repassword_reset)
    EditText edtRepasswordReset;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        textNamaLengkap.setText(getIntent().getStringExtra(EXTRA_NAMA_LENGKAP));
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword();
            }
        });
    }

    private void ResetPassword() {

        String user_id = getIntent().getStringExtra(EXTRA_ID_USER);
        String password_new = edtPasswordReset.getText().toString();
        String repassword_new = edtRepasswordReset.getText().toString();

        if (password_new.isEmpty()){
            edtPasswordReset.setError("Password Tidak Boleh Kosong");
            edtPasswordReset.requestFocus();
            return;
        }

        if (repassword_new.isEmpty()){
            edtRepasswordReset.setError("Repassword Tidak Boleh Kosong");
            edtRepasswordReset.requestFocus();
            return;
        }

        if (!repassword_new.equals(password_new)){
            edtRepasswordReset.setError("Password Tidak SAMA");
            edtRepasswordReset.requestFocus();
            return;
        }

        ConfigRetrofit.service.ResetPassword(user_id, password_new).enqueue(new Callback<ResponseResetPassword>() {
            @Override
            public void onResponse(Call<ResponseResetPassword> call, Response<ResponseResetPassword> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(ResetPasswordActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ResetPasswordActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResetPassword> call, Throwable t) {
                Toast.makeText(ResetPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
