package com.example.app_music_little.API;

import com.example.app_music_little.Model.Quangcao;

import java.util.List;
import android.Manifest;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
  @GET("server2.php")
  Call<List<Quangcao>> GetDataQuangcao();
}
