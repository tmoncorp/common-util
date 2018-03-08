package com.tmoncorp.common.util.spring.mvc;

import java.util.List;

/**
 * Repository 들의 인터페이스 통일을 위해 기본적인 CRUD 의 interface 를 정의
 */
public interface CommonRestRepository<K, V extends CommonRestModel> {
    /**
     * @return 전체 resource 목록
     */
    default List<V> selectAll() {
        throw new UnsupportedOperationException("selectAll is not supported");
    }

    /**
     * @param ids 조회 할 resource 의 PK 목록
     * @return 조회된 resource 목록
     */
    default List<V> selectByIds(List<K> ids) {
        throw new UnsupportedOperationException("selectByIds is not supported");
    }

    /**
     * @param object 추가 할 resource
     * @return PK 가 추가된 resource
     */
    default V insert(V object) {
        throw new UnsupportedOperationException("insert is not supported");
    }

    /**
     * @param id 조회 할 resource 의 PK
     * @return 조회된 resource
     */
    default V select(K id) {
        throw new UnsupportedOperationException("select is not supported");
    }

    /**
     * @param object PK를 포함한 수정 할 resource
     * @return 변경된 row 수
     */
    default int update(V object) {
        throw new UnsupportedOperationException("update is not supported");
    }

    /**
     * @param id 삭제 할 resource 의 PK
     * @return 변경된 row 수
     */
    default int delete(K id) {
        throw new UnsupportedOperationException("delete is not supported");
    }

    /**
     * @param id 검색 할 resource 의 PK
     * @return 존재여부
     */
    default boolean exist(K id) {
        return select(id) != null;
    }
}
