package com.vanstone.webframework.formobject;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author peng.shi
 */
public class LazyMap<K, V> implements Map<K, V> {

    private Map<K, V> nestedMap;
    private Class<V> valueClazz;

    public LazyMap(Class<V> clazz) {
        nestedMap = new LinkedHashMap<K, V>();
        this.valueClazz = clazz;
    }

    public void clear() {
        nestedMap.clear();
    }

    public boolean containsKey(Object key) {
        return nestedMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return nestedMap.containsValue(value);
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return nestedMap.entrySet();
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if(!containsKey(key)) {
            try {
                put((K) key, valueClazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nestedMap.get(key);
    }

    public boolean isEmpty() {
        return nestedMap.isEmpty();
    }

    public Set<K> keySet() {
        return nestedMap.keySet();
    }

    public V put(K key, V value) {
        return nestedMap.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        nestedMap.putAll(m);
    }

    public V remove(Object key) {
        return nestedMap.remove(key);
    }

    public int size() {
        return nestedMap.size();
    }

    public Collection<V> values() {
        return nestedMap.values();
    }

}
