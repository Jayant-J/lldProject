package com.patni.lld.cache.evictionPolicies;

public interface Policy<Key> {

    void keyAccessed(Key key);

    Key evictKey();
}
