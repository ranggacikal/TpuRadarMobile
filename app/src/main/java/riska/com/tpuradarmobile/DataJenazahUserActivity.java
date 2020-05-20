package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;
import riska.com.tpuradarmobile.adapter.JenazahUserAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataJenazahByUserItem;
import riska.com.tpuradarmobile.model.ResponseDataJenazahByUser;

public class DataJenazahUserActivity extends AppCompatActivity {

    @BindView(R.id.recycler_jenazah_user)
    RecyclerView recyclerJenazahUser;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jenazah_user);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        LoadJenazahUser();
    }

    private void LoadJenazahUser() {

        String user_id = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.GetDataJenazahByUser(user_id).enqueue(new Callback<ResponseDataJenazahByUser>() {
            @Override
            public void onResponse(Call<ResponseDataJenazahByUser> call, Response<ResponseDataJenazahByUser> response) {
                int status = response.body().getStatus();

                if (status == 1){

                    List<DataJenazahByUserItem> jenazahUserList = response.body().getDataJenazahByUser();
                    JenazahUserAdapter adapter = new JenazahUserAdapter(DataJenazahUserActivity.this, jenazahUserList);
                    recyclerJenazahUser.setAdapter(adapter);
                    recyclerJenazahUser.setLayoutManager(new LinearLayoutManager(DataJenazahUserActivity.this));
                }else{
                    Toast.makeText(DataJenazahUserActivity.this, "DATA TIDAK ADA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataJenazahByUser> call, Throwable t) {
                Toast.makeText(DataJenazahUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataJenazahUserActivity.this, DataPesananActivity.class));
        finish();
    }
}
