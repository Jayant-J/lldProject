package com.patni.lld.cache;

import com.patni.lld.cache.evictionPolicies.LRUPolicy;
import com.patni.lld.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUPolicy<>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}
