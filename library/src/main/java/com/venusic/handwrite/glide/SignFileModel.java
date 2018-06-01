package com.venusic.handwrite.glide;

import android.content.Context;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * @version 1.0
 * @auth wastrel
 * @date 2018/2/6 11:09
 * @copyRight 四川金信石信息技术有限公司
 * @since 1.0
 */
public class SignFileModel implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(Uri.class, InputStream.class, new SignModelLoader.Factory());
    }
}
