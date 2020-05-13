package com.example.app_music_little;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_music_little.Fragment.CaNhanFragment;
import com.example.app_music_little.Fragment.TimKiemFragment;
import com.example.app_music_little.Fragment.TrangChuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrangChuFragment()).commit();
    }

    /**
     * Em làm giao diện như thế này không hopwj lý.
     * Nếu như em viết như này thì chạy là chạy được, nhưng về performance thì quá tệ.
     * Mỗi lần em click navigation bottom thì nó new 1 fragment (selectedFragment)
     * Chổ này sao mình không dùng viewpager? (không thì show hide fragment)
     * */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new TrangChuFragment();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new TimKiemFragment();
                            break;

                        case R.id.nav_personal:
////                            selectedFragment = new CaNhanFragment();
////                            break;
                        default:
                            selectedFragment = new CaNhanFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
