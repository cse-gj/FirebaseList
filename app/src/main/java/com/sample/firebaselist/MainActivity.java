package com.sample.firebaselist;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sample.firebaselist.fragment.ChatFragment;
import com.sample.firebaselist.fragment.PeopleFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainactivity_bottomnavigationview);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_m1:
                       // getFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout,new PeopleFragment()).commit();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainactivity_framelayout, new PeopleFragment()).commit();
                        return true; // 이벤트가 바로 작동을 해줍니다.
                    case R.id.action_m2:
                        //getFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout,new ChatFragment()).commit();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mainactivity_framelayout, new ChatFragment()).commit();
                        return true;
                }

                return false;
            }
        });

        //푸시 알림부분
        passPushTokenToServer();

    }
    void passPushTokenToServer(){ //토큰 값을 서버러부터 받아온다.

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); //받아오기
        String token = FirebaseInstanceId.getInstance().getToken(); //토큰 만들기
        Map<String,Object> map = new HashMap<>();
        map.put("pushToken",token);

        FirebaseDatabase.getInstance().getReference().child("users").child(uid).updateChildren(map); //생성되면서 바로 서버로 올리기
 //업데이트를 주기적으로 해주어야 한다.

    }
}