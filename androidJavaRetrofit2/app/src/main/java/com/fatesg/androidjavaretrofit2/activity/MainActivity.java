package com.fatesg.androidjavaretrofit2.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.fatesg.androidjavaretrofit2.R;
import com.fatesg.androidjavaretrofit2.bootstrap.APIClient;
import com.fatesg.androidjavaretrofit2.model.User;
import com.fatesg.androidjavaretrofit2.resourse.UserResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    protected UserResource apiUserResource;
    List<HashMap<String,String>> colecao =new ArrayList<>();

    protected List<User> listaUser;
    protected ListView listUser;
    protected User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiUserResource = APIClient.getClient().create(UserResource.class);

        retrofit2.Call<List<User>> get = apiUserResource.get();

        get.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                listUser = findViewById(R.id.lista);

                listaUser = response.body();

                for(int i = 0; i < listaUser.size(); i++) {

                    HashMap<String, String> listaMap = new HashMap<>();
                    listaMap.put("id", String.valueOf(user.getId()));
                    listaMap.put("name", user.getName());
                    listaMap.put("username", user.getUsername());
                    listaMap.put("email", user.getEmail());
                    listaMap.put("street", user.getAddress().getStreet());
                    listaMap.put("suite", user.getAddress().getSuite());
                    listaMap.put("city", user.getAddress().getCity());
                    listaMap.put("zipcode", user.getAddress().getZipcode());
                    listaMap.put("lat", user.getAddress().getGeo().getLat());
                    listaMap.put("lng", user.getAddress().getGeo().getLng());
                    listaMap.put("phone", user.getPhone());
                    listaMap.put("website", user.getWebsite());
                    listaMap.put("companyName", user.getCompany().getName());
                    listaMap.put("catchPhrase", user.getCompany().getCatchPhrase());
                    listaMap.put("bs", user.getCompany().getBs());

                    colecao.add(listaMap);
                }

                String[] from = {"id","name","username","email","street","suite","city","zipcode",
                        "lat","lng","phone","website","companyName","catchPhrase","bs"};
                int[] to = {R.id.id,R.id.name,R.id.username,R.id.email,R.id.email,R.id.street,R.id.suite,R.id.city,
                        R.id.zipcode,R.id.lat,R.id.lng,R.id.phone,R.id.website,R.id.cname,R.id.catchPhrase,R.id.bs};


                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),colecao,R.layout.content_main,from,to);

                listUser.setAdapter(simpleAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
