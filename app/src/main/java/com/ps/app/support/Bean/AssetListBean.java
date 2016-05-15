package com.ps.app.support.Bean;

import java.io.Serializable;
import java.util.List;

public class AssetListBean implements Serializable{

    /**
     * code : 2000
     * data : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":2,"list":[{"caseName":"A案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":1,"name":"A嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"100","sn2":"21","type":"资金"},{"caseName":"B案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":2,"name":"B嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"101","sn2":"11","type":"资金"},{"caseName":"C案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":3,"name":"C嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"122","sn2":"12","type":"资金"},{"caseName":"D案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":4,"name":"D嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"123","sn2":"13","type":"资金"},{"caseName":"E案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":5,"name":"E嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"124","sn2":"3","type":"资金"},{"caseName":"F案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":6,"name":"F嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"125","sn2":"14","type":"资金"},{"caseName":"G案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":7,"name":"G嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"126","sn2":"15","type":"房产"},{"caseName":"H案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":8,"name":"H嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"127","sn2":"16","type":"房产"},{"caseName":"I案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":9,"name":"I嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"136","sn2":"17","type":"房产"},{"caseName":"J案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":10,"name":"J嫌疑犯","policeGroup":"青年派出所","remark":"","sn1":"145","sn2":"18","type":"房产"}],"navigatePages":8,"navigatepageNums":[1,2],"nextPage":2,"orderBy":"","pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":12}
     * desc : 成功!
     * error : 
     */

    private int code;
    /**
     * endRow : 10
     * firstPage : 1
     * hasNextPage : true
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : false
     * lastPage : 2
     * list : [{"caseName":"A案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":1,"name":"A嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"100","sn2":"21","type":"资金"},{"caseName":"B案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":2,"name":"B嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"101","sn2":"11","type":"资金"},{"caseName":"C案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":3,"name":"C嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"122","sn2":"12","type":"资金"},{"caseName":"D案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":4,"name":"D嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"123","sn2":"13","type":"资金"},{"caseName":"E案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":5,"name":"E嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"124","sn2":"3","type":"资金"},{"caseName":"F案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":6,"name":"F嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"125","sn2":"14","type":"资金"},{"caseName":"G案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":7,"name":"G嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"126","sn2":"15","type":"房产"},{"caseName":"H案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":8,"name":"H嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"127","sn2":"16","type":"房产"},{"caseName":"I案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":9,"name":"I嫌疑犯","policeGroup":"云龙分局","remark":"","sn1":"136","sn2":"17","type":"房产"},{"caseName":"J案子","closeEndTime":"2016-06-05 14:24:23","closeStartTime":"2016-06-01 14:24:18","createTime":"2016-06-01 14:24:14","freezeEndTime":"2016-05-15 04:54:34","freezeStartTime":"2016-05-15 04:54:32","handleType":"交由法院处理","id":10,"name":"J嫌疑犯","policeGroup":"青年派出所","remark":"","sn1":"145","sn2":"18","type":"房产"}]
     * navigatePages : 8
     * navigatepageNums : [1,2]
     * nextPage : 2
     * orderBy : 
     * pageNum : 1
     * pageSize : 10
     * pages : 2
     * prePage : 0
     * size : 10
     * startRow : 1
     * total : 12
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
         * caseName : A案子
         * closeEndTime : 2016-06-05 14:24:23
         * closeStartTime : 2016-06-01 14:24:18
         * createTime : 2016-06-01 14:24:14
         * freezeEndTime : 2016-05-15 04:54:34
         * freezeStartTime : 2016-05-15 04:54:32
         * handleType : 交由法院处理
         * id : 1
         * name : A嫌疑犯
         * policeGroup : 云龙分局
         * remark : 
         * sn1 : 100
         * sn2 : 21
         * type : 资金
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
            private String caseName;
            private String closeEndTime;
            private String closeStartTime;
            private String createTime;
            private String freezeEndTime;
            private String freezeStartTime;
            private String handleType;
            private int id;
            private String name;
            private String policeGroup;
            private String remark;
            private String sn1;
            private String sn2;
            private String type;

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public String getCloseEndTime() {
                return closeEndTime;
            }

            public void setCloseEndTime(String closeEndTime) {
                this.closeEndTime = closeEndTime;
            }

            public String getCloseStartTime() {
                return closeStartTime;
            }

            public void setCloseStartTime(String closeStartTime) {
                this.closeStartTime = closeStartTime;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getFreezeEndTime() {
                return freezeEndTime;
            }

            public void setFreezeEndTime(String freezeEndTime) {
                this.freezeEndTime = freezeEndTime;
            }

            public String getFreezeStartTime() {
                return freezeStartTime;
            }

            public void setFreezeStartTime(String freezeStartTime) {
                this.freezeStartTime = freezeStartTime;
            }

            public String getHandleType() {
                return handleType;
            }

            public void setHandleType(String handleType) {
                this.handleType = handleType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoliceGroup() {
                return policeGroup;
            }

            public void setPoliceGroup(String policeGroup) {
                this.policeGroup = policeGroup;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSn1() {
                return sn1;
            }

            public void setSn1(String sn1) {
                this.sn1 = sn1;
            }

            public String getSn2() {
                return sn2;
            }

            public void setSn2(String sn2) {
                this.sn2 = sn2;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}