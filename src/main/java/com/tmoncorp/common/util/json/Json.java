package com.tmoncorp.common.util.json;

import java.util.LinkedHashMap;

/**
 * DB에 Json string 을 넣는 경우 사용하기 위한 모델
 * myBatis 와 연동하기 위해서 {@link JsonTypeHandler} 를 등록해 줘야 한다
 */
public class Json extends LinkedHashMap<String, Object> {
}
