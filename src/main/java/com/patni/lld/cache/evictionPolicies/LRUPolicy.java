package com.patni.lld.cache.evictionPolicies;

import com.patni.lld.algorithms.DLL;
import com.patni.lld.algorithms.DLLNode;

import java.util.HashMap;
import java.util.Map;

public class LRUPolicy<Key> implements Policy {

    private DLL<Key> dll;
    private Map<Key, DLLNode<Key>> mapper;

    public LRUPolicy() {
        this.dll = new DLL<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Object key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DLLNode<Key> newNode = dll.addElementAtLast((Key) key);
            mapper.put((Key) key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DLLNode<Key> first = dll.getFirstNode();
        if (first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}
