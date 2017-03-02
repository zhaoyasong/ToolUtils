package com.billy.androidutils.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MyLurCache extends LruCache<String, Bitmap> {

	public MyLurCache(int maxSize) {
		super(4 * 1024 * 1024);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		// TODO
		return value.getRowBytes() * value.getHeight() / 1024;
	}

}
