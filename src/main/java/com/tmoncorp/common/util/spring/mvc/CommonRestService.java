package com.tmoncorp.common.util.spring.mvc;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link CommonRestController} 에서 사용되는 Service 의 인터페이스 통합을 위한 추상 클래스
 */
public class CommonRestService<K, V extends CommonRestModel> {
    protected final CommonRestRepository<K, V> repository;

    public CommonRestService(CommonRestRepository<K, V> repository) {
        this.repository = repository;
    }


    public List<V> getAll() {
        return repository.selectAll();
    }

    public List<V> getByIds(List<K> ids) {
        return repository.selectByIds(ids);
    }

    public List<V> addAll(List<V> objects) {
        return objects.stream().map(this::add).collect(Collectors.toList());
    }

    public List<V> updateAll(List<V> objects) {
        return objects.stream().filter(object -> update((K) object.getId(), object) == false).collect(Collectors.toList());
    }

    public List<K> deleteAll(List<K> id) {
        return id.stream().filter(this::delete).collect(Collectors.toList());
    }

    public V get(K id) {
        return repository.select(id);
    }

    public V add(V object) {
        if (object.getId() != null && update((K) object.getId(), object)) {
            return object;
        }

        return repository.insert(object);
    }

    public boolean update(K id, V object) {
        if (id.equals(object.getId()) == false) {
            throw new IllegalArgumentException("id 와 object 의 id 가 일치하지 않습니다.");
        }
        return exist(id) ? repository.update(object) >= 0 : false;
    }

    public boolean delete(K id) {
        return exist(id) ? repository.delete(id) == 1 : true;
    }

    public boolean exist(K id) {
        return repository.exist(id);
    }
}
