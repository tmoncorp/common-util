package com.tmoncorp.common.util.spring.mvc;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 기본적인 REST api 의 인터페이스 통합을 위한 추상 클래스
 */
public abstract class CommonRestController<K, V extends CommonRestModel> {
    protected final CommonRestService<K, V> service;

    public CommonRestController(CommonRestService<K, V> service) {
        this.service = service;
    }


    /**
     * @return 전체 resource 목록
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<V> getAll() {
        return service.getAll();
    }

    /**
     * @param ids 조회 할 resource 의 id 목록
     * @return 전체 resource 목록
     */
    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public List<V> getByIds(@RequestParam("id") List<K> ids) {
        return service.getByIds(ids);
    }

    /**
     * @param objects 추가 할 resource (단일 resource 의 add 에도 addAll(List) 을 사용)
     * @return 추가된 resource 의 id 를 포함하는 resource 목록
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<V> addAll(@RequestBody List<V> objects) {
        return service.addAll(objects);
    }

    /**
     * @param objects 수정 할 resource 의 id, resource 맵
     * @return 업데이트된 resource 목록
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<V> updateAll(@RequestBody List<V> objects) {
        return service.updateAll(objects);
    }

    /**
     * @param ids 삭제 할 resource 의 id 목록
     * @return 삭제된 resource 의 id 목록
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public List<K> deleteAll(@RequestParam("id") List<K> ids) {
        return service.deleteAll(ids);
    }

    /**
     * @param id 조회 할 resource 의 id
     * @return 조회된 resource
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<V> get(@PathVariable K id) {
        return ResultWrapper.wrap(service.get(id));
    }

    /**
     * @param id     수정 할 resource 의 id
     * @param object 수정 할 resource
     * @return 수정 된 resource
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result<V> update(@PathVariable K id, @RequestBody V object) {
        return service.update(id, object) ? ResultWrapper.wrap(object) : null;
    }

    /**
     * @param id 삭제 할 resource 의 id
     * @return 삭제된 resource 의 id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<K> delete(@PathVariable K id) {
        return service.delete(id) ? ResultWrapper.wrap(id) : null;
    }
}
