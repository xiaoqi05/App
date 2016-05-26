package com.ps.app.support.Bean;

import java.io.Serializable;
import java.util.List;

public class PushMsgListBean implements Serializable {


    /**
     * code : 2000
     * data : {"endRow":5,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":3,"list":[{"content":"嫌疑犯李四今日还未签到","createTime":"2016-05-22 15:10:43","id":1,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"嫌疑犯张三今日还未签到","createTime":"2016-05-22 15:10:35","id":2,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"嫌疑犯王三风今日还未签到","createTime":"2016-05-22 15:12:12","id":3,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"2","createTime":"2016-05-17 14:48:17","id":4,"mid":52,"successed":0,"type":null,"unread":1},{"content":"1","createTime":"2016-05-17 15:34:53","id":5,"mid":52,"successed":0,"type":null,"unread":1}],"navigatePages":8,"navigatepageNums":[1,2,3],"nextPage":2,"orderBy":"","pageNum":1,"pageSize":5,"pages":3,"prePage":0,"size":5,"startRow":1,"total":15}
     * desc : 成功!
     * error : 
     */

    private int code;
    /**
     * endRow : 5
     * firstPage : 1
     * hasNextPage : true
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : false
     * lastPage : 3
     * list : [{"content":"嫌疑犯李四今日还未签到","createTime":"2016-05-22 15:10:43","id":1,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"嫌疑犯张三今日还未签到","createTime":"2016-05-22 15:10:35","id":2,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"嫌疑犯王三风今日还未签到","createTime":"2016-05-22 15:12:12","id":3,"mid":52,"successed":0,"type":"SIGN_IN_TIP","unread":1},{"content":"2","createTime":"2016-05-17 14:48:17","id":4,"mid":52,"successed":0,"type":null,"unread":1},{"content":"1","createTime":"2016-05-17 15:34:53","id":5,"mid":52,"successed":0,"type":null,"unread":1}]
     * navigatePages : 8
     * navigatepageNums : [1,2,3]
     * nextPage : 2
     * orderBy : 
     * pageNum : 1
     * pageSize : 5
     * pages : 3
     * prePage : 0
     * size : 5
     * startRow : 1
     * total : 15
     */

    private DataBean data;
    private String desc;
    private String error;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean implements Serializable{
        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigatePages;
        private int nextPage;
        private String orderBy;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        /**
         * content : 嫌疑犯李四今日还未签到
         * createTime : 2016-05-22 15:10:43
         * id : 1
         * mid : 52
         * successed : 0
         * type : SIGN_IN_TIP
         * unread : 1
         */

        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
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

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable{
            private String content;
            private String createTime;
            private int id;
            private int mid;
            private int successed;
            private String type;
            private int unread;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getSuccessed() {
                return successed;
            }

            public void setSuccessed(int successed) {
                this.successed = successed;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getUnread() {
                return unread;
            }

            public void setUnread(int unread) {
                this.unread = unread;
            }
        }
    }
}