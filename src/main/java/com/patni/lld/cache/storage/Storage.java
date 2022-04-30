package com.patni.lld.cache.storage;

import com.patni.lld.cache.exceptions.NotFoundException;
import com.patni.lld.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {

    public void add(Key key, Value value) throws StorageFullException;

    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;
}
