package riska.com.tpuradarmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseLoginUser;

public class LoginAdminActivity extends AppCompatActivity {

    @BindView(R.id.edt_email_login_admin)
    EditText edtEmailLoginAdmin;
    @BindView(R.id.edt_password_login_admin)
    EditText edtPasswordLoginAdmin;
    @BindView(R.id.btn_login_admin)
    Button btnLoginAdmin;
    @BindView(R.id.txtview_lupapassword_admin)
    TextView txtviewLupapasswordAdmin;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        if (preferencedConfig.getPreferenceIsLogin()){
            startActivity(new Intent(LoginAdminActivity.this, AdminActivity.class));
            finish();
        }

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAdmin();
            }
        });
    }

    private void LoginAdmin() {

        String emailAdmin = edtEmailLoginAdmin.getText().toString();
        String passwordAdmin = edtPasswordLoginAdmin.getText().toString();

        if (emailAdmin.isEmpty()){
            edtEmailLoginAdmin.setError("Email Tidak Boleh Kosong");
            edtEmailLoginAdmin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAdmin).matches()){
            edtEmailLoginAdmin.setError("Harap Masukan Email Yang Valid");
            edtEmailLoginAdmin.requestFocus();
            return;
        }

        if (passwordAdmin.isEmpty()){
            edtPasswordLoginAdmin.setError("Password Tidak Boleh Kosong");
            edtPasswordLoginAdmin.requestFocus();
            return;
        }

        final ProgressDialog progDialog = ProgressDialog.show(LoginAdminActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.LoginUser(emailAdmin, passwordAdmin).enqueue(new Callback<ResponseLoginUser>() {
            @Override
            public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                progDialog.dismiss();

                if (status==1){
                    String level= response.body().getDataLoginUser().getLevel();

                    if (level.equals("Admin")){
                        Toast.makeText(LoginAdminActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginAdminActivity.this, AdminActivity.class);

                        String namaLengkap = response.body().getDataLoginUser().getNama();
                        String emailUser = response.body().getDataLoginUser().getEmailUser();
                        String noTelpon = response.body().getDataLoginUser().getNoTelpon();
                        String userId = response.body().getDataLoginUser().getUserId();
                        String levelUser = response.body().getDataLoginUser().getLevel();
                        String imageUser = response.body().getDataLoginUser().getImageUser();

                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_USER, userId);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_LENGKAP, namaLengkap);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, emailUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE, imageUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, levelUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NO_TELPON, noTelpon);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                        startActivity(intent);
                        finish();
                    }else if (level.equals("User")){
                        Toast.makeText(LoginAdminActivity.this, "ANDA BUKAN ADMIN !!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginAdminActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
                Toast.makeText(LoginAdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
