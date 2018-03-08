package com.tmoncorp.common.util.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tmoncorp.common.util.json.BaseModelSupport;

import java.util.Collections;
import java.util.List;

/**
 * 단순히 페이징된 결과를 담기 위한 container
 * 페이징에 대한 오류등에 대하여 관여하지 않는다
 * itemCount와 hasNext는 나머지 값에의해 자동으로 계산된다
 * pageIndex는 0부터 시작한다
 * paging 자체에 순서를 갖는다는 의미가 있기 때문에 items는 List로 한다
 */
@JsonPropertyOrder({"totalCount", "pageIndex", "itemCount", "hasNextPage", "items"})
public class PageContainer<T> extends BaseModelSupport {
    private int pageSize;
    private int totalCount;
    private int pageIndex;
    private List<T> items;

    public PageContainer(int pageSize, int totalCount, int index, List<T> items) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageIndex = index;
        this.items = items;
    }

    public PageContainer() {
    }

    private PageContainer(int pageSize, int index) {
        this.pageSize = pageSize;
        this.pageIndex = index;
        this.items = Collections.emptyList();
    }

    public static <T> PageContainer<T> getEmpty(int pageSize, int index) {
        return new PageContainer<>(pageSize, index);
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageContainer<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public PageContainer<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public PageContainer<T> setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public List<T> getItems() {
        return items;
    }

    public PageContainer<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    @JsonProperty("hasNextPage")
    public boolean hasNextPage() {
        return this.pageSize * (this.pageIndex + 1) < this.totalCount;
    }
}
