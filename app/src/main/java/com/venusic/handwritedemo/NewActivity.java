package com.venusic.handwritedemo;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.venusic.handwritedemo.databinding.ActivityNewBinding;

import java.io.File;
import java.io.IOException;

/**
 * @version 1.0
 * @auth wastrel
 * @date 2018/1/10 10:50
 * @copyRight 四川金信石信息技术有限公司
 * @since 1.0
 */
public class NewActivity extends AppCompatActivity {
    ActivityNewBinding binding;
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "signfile";
    public static String path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ls.png";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSignFile();
        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final boolean isCrop = getIntent().getBooleanExtra("isCrop", false);
        binding.changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.view.setPaintColor(Color.RED);
            }
        });
        binding.changeWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.view.setPaintWidth(5, 20);
            }
        });
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.view.clear();
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.view.isSign())
                    try {
                        if (isCrop) {
                            binding.view.save(path1, true, 10,true);
                            setResult(101);
                            finish();
                        } else {
                            File file=new File(path,"gc.png");
                            binding.view.save(file.getAbsolutePath());


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                else {
                    Toast.makeText(NewActivity.this, "还没有签名！", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private void createSignFile(){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
