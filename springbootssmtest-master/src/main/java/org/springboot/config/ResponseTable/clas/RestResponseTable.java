package org.springboot.config.ResponseTable.clas;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import org.springboot.config.IRestResponse;
import org.springboot.config.ResponseData.clas.BaseRestResponse;

import java.util.Collections;
import java.util.List;


/**
 * @ClassName RestResponseTable
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 10:47
 * @Version 1.0
 */
public class RestResponseTable<T> extends BaseRestResponse {
    private long total;
    private long size;
    private long current;
    private long pages;
    private List<T> records = Collections.emptyList();

    public RestResponseTable(Integer code, String message) {
        super(code, message);
    }

    public RestResponseTable(IRestResponse response) {
        super(response);
    }

    public RestResponseTable(IPage<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }
    public RestResponseTable(PageInfo page) {
        this.records = page.getList();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getPageNum();
        this.pages = page.getPageSize();
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return this.current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPages() {
        return this.pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}