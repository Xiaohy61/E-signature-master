package com.venusic.handwritedemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.venusic.handwritedemo.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qm.png";
    public static String path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ls.png";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startHandPlatService();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, NewActivity.class), 1);
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("isCrop", true);
                startActivityForResult(intent, 1);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x555);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            binding.img1.setImageBitmap(bm);
        } else if (resultCode == 101) {
            Glide.with(this).load(path1 + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.img2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "您拒绝了读写存储权限！", Toast.LENGTH_LONG).show();
        }
    }
    private void startHandPlatService() {
        Intent intent = new Intent(this, HandPlatService.class);
        startService(intent);}
}
