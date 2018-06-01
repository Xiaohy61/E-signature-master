package com.venusic.handwrite.glide;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.bumptech.glide.load.data.StreamLocalUriFetcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @version 1.0
 * @auth wastrel
 * @date 2018/2/6 11:16
 * @copyRight 四川金信石信息技术有限公司
 * @since 1.0
 */
public class SignStreamLocalUriFetcher extends StreamLocalUriFetcher {

    public SignStreamLocalUriFetcher(Context context, Uri uri) {
        super(context, uri);
    }

    @Override
    protected InputStream loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())&&uri.getPath().endsWith(".sign")) {
            try {
                return new SignFileInputStream(uri.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.loadResource(uri, contentResolver);
    }

}
