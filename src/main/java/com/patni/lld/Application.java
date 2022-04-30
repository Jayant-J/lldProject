package com.patni.lld;

import com.patni.lld.cache.Cache;
import com.patni.lld.cache.CacheFactory;

public class Application {
    public static void main(String[] args) {
        CacheFactory<String, String> cacheFactory = new CacheFactory<>();
        Cache cache = cacheFactory.defaultCache(100);
    }
}
