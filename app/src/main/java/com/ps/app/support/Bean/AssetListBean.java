package com.ps.app.support.Bean;

import java.util.List;

public class AssetListBean {

    /**
     * code : 2000
     * data : {"lastPage":1,"navigatepageNums":[1],"startRow":1,"hasNextPage":false,"prePage":0,"nextPage":0,"endRow":1,"orderBy":"","pageSize":5,"list":[{"createTime":1464762254000,"id":1,"sn":"1"}],"pageNum":1,"navigatePages":8,"total":1,"pages":1,"firstPage":1,"size":1,"isLastPage":true,"hasPreviousPage":false,"isFirstPage":true}
     * error : 
     * desc : 成功!
     */

    private int code;
    /**
     * lastPage : 1
     * navigatepageNums : [1]
     * startRow : 1
     * hasNextPage : false
     * prePage : 0
     * nextPage : 0
     * endRow : 1
     * orderBy : 
     * pageSize : 5
     * list : [{"createTime":1464762254000,"id":1,"sn":"1"}]
     * pageNum : 1
     * navigatePages : 8
     * total : 1
     * pages : 1
     * firstPage : 1
     * size : 1
     * isLastPage : true
     * hasPreviousPage : false
     * isFirstPage : true
     */

    private DataBean data;
    private String error;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataBean {
        private int lastPage;
        private int startRow;
        private boolean hasNextPage;
        private int prePage;
        private int nextPage;
        private int endRow;
        private String orderBy;
        private int pageSize;
        private int pageNum;
        private int navigatePages;
        private int total;
        private int pages;
        private int firstPage;
        private int size;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private List<Integer> navigatepageNums;
        /**
         * createTime : 1464762254000
         * id : 1
         * sn : 1
         */

        private List<ListBean> list;

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private long createTime;
            private int id;
            private String sn;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}