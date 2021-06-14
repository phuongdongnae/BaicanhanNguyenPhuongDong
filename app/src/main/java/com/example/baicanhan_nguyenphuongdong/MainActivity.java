package com.example.baicanhan_nguyenphuongdong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.baicanhan_nguyenphuongdong.fragment.FragmentBottomNavigationAdapter;
import com.example.baicanhan_nguyenphuongdong.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL_1";
    private TextView tv;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private BottomNavigationView navigation;
    private FragmentBottomNavigationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createNotificationChannel();

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("account");
        tv.setText("Hello "+user.getUsername().toString()+", ENJOY SHOPPING !!!");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
                Intent intent2 = new Intent(MainActivity.this, AddProductActivity.class);
                Intent intent3 = new Intent(MainActivity.this, InfomationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent, 0);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent2, 0);
                PendingIntent pendingIntent3 = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent3, 0);
                NotificationCompat.Builder notify  = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setContentTitle("Check the Product List")
                        .setContentText("Click here to go to All Product Activity")
                        .setSmallIcon(R.drawable.ic_email)
                        .setContentIntent(pendingIntent) //nhấn vào Noti chuyển sang AllProductActivity
                        .addAction(R.drawable.clothesicon, "Add Product", pendingIntent2) //chuyển qua Add Activity
                        .addAction(R.drawable.fb, "Infomation", pendingIntent3); //chuyển qua InfoActi
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(001, notify.build());
            }
        });

        viewPager = findViewById(R.id.viewPager);
        navigation = findViewById(R.id.navigation);
        adapter = new FragmentBottomNavigationAdapter(getSupportFragmentManager(), FragmentBottomNavigationAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mWomen: viewPager.setCurrentItem(0);
                        break;
                    case R.id.mMen: viewPager.setCurrentItem(1);
                        break;
                    case R.id.mKid: viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigation.getMenu().findItem(R.id.mWomen).setChecked(true);
                        break;
                    case 1: navigation.getMenu().findItem(R.id.mMen).setChecked(true);
                        break;
                    case 2: navigation.getMenu().findItem(R.id.mKid).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.listPro:
                Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
                startActivity(intent);
                break;
            case R.id.addPro:
                Intent i = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(i);
                break;
            case R.id.info:
                Intent i2 = new Intent(MainActivity.this, InfomationActivity.class);
                startActivity(i2);
                break;
            case R.id.exit:
                System.exit(0);

        }
        return super.onOptionsItemSelected(item);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initView() {
        tv = findViewById(R.id.texttv);
        fab = findViewById(R.id.fab);
    }
}