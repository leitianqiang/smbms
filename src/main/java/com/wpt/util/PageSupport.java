package com.wpt.util;

import org.springframework.stereotype.Component;

/**
 * @Author ltq
 * @Date 2022/2/21 9:33
 */
@Component
public class PageSupport {
    /*//当前页面 来自于用户输入
    private int currentPageNo = 1;
    //总数量(表)
    private int totalCount = 0;
    //页面容量(大小)
    private int pageSize = 0;
    //总共显示的页数，为总表数量/单页容量 + 1
    private int totalPageCount = 1;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo > 0){
            this.currentPageNo = currentPageNo;
        }

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0){
            this.totalCount = totalCount;
            this.setTotalPageCountByRs();
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0){
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalPageCountByRs(){
        if (this.totalCount % this.pageSize == 0){
            this.totalPageCount = this.totalCount / this.pageSize;
        }else if (this.totalCount % this.pageSize > 0){
            this.totalPageCount = this.totalCount / this.pageSize;
        }else {
            this.totalPageCount = 0;
        }
    }*/

    private int currentPageNo;  //当前页
    private int pageSize;  //页大小
    private int totalCount;  //总记录
    private int totalPageCount;  //总页数

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            this.totalPageCount = this.totalCount % this.pageSize == 0 ?
                    this.totalCount / this.pageSize :
                    this.totalCount / this.pageSize + 1;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

}
