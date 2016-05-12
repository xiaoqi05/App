package com.ps.app.support.Bean;

import java.io.Serializable;
import java.util.List;

public class FreeManListBean implements Serializable{


    /**
     * desc : 成功
     * error : 
     * data : {"total":2,"lastPage":1,"hasNextPage":false,"nextPage":0,"orderBy":"","pages":1,"pageSize":10,"navigatePages":8,"hasPreviousPage":false,"list":[{"id":3,"createTime":1463041379000,"phone":"13389899871","idCard":"311411199909099909","area":"A区域","name":"嫌疑犯王","gender":"FEMALE","type":"OUT_OF_SUSPECT","group":{"id":2,"desc":"B分局","type":"POLICE"},"displayName":"嫌疑犯王"},{"id":48,"createTime":1463041389000,"phone":"13682070860","idCard":"","area":"","name":"赵中华","gender":"UNKNOW","type":"OUT_OF_SUSPECT","group":"","displayName":""}],"navigatepageNums":[1],"size":2,"pageNum":1,"prePage":0,"endRow":2,"isLastPage":true,"startRow":1,"firstPage":1,"isFirstPage":true}
     * code : 2000
     */

    private String desc;
    private String error;
    /**
     * total : 2
     * lastPage : 1
     * hasNextPage : false
     * nextPage : 0
     * orderBy : 
     * pages : 1
     * pageSize : 10
     * navigatePages : 8
     * hasPreviousPage : false
     * list : [{"id":3,"createTime":1463041379000,"phone":"13389899871","idCard":"311411199909099909","area":"A区域","name":"嫌疑犯王","gender":"FEMALE","type":"OUT_OF_SUSPECT","group":{"id":2,"desc":"B分局","type":"POLICE"},"displayName":"嫌疑犯王"},{"id":48,"createTime":1463041389000,"phone":"13682070860","idCard":"","area":"","name":"赵中华","gender":"UNKNOW","type":"OUT_OF_SUSPECT","group":"","displayName":""}]
     * navigatepageNums : [1]
     * size : 2
     * pageNum : 1
     * prePage : 0
     * endRow : 2
     * isLastPage : true
     * startRow : 1
     * firstPage : 1
     * isFirstPage : true
     */

    private DataBean data;
    private int code;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean implements Serializable{
        private int total;
        private int lastPage;
        private boolean hasNextPage;
        private int nextPage;
        private String orderBy;
        private int pages;
        private int pageSize;
        private int navigatePages;
        private boolean hasPreviousPage;
        private int size;
        private int pageNum;
        private int prePage;
        private int endRow;
        private boolean isLastPage;
        private int startRow;
        private int firstPage;
        private boolean isFirstPage;
        /**
         * id : 3
         * createTime : 1463041379000
         * phone : 13389899871
         * idCard : 311411199909099909
         * area : A区域
         * name : 嫌疑犯王
         * gender : FEMALE
         * type : OUT_OF_SUSPECT
         * group : {"id":2,"desc":"B分局","type":"POLICE"}
         * displayName : 嫌疑犯王
         */

        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
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

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
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
            private int id;
            private long createTime;
            private String phone;
            private String idCard;
            private String area;
            private String name;
            private String gender;
            private String type;
            /**
             * id : 2
             * desc : B分局
             * type : POLICE
             */

            private GroupBean group;
            private String displayName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public static class GroupBean implements Serializable{
                private int id;
                private String desc;
                private String type;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
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
}