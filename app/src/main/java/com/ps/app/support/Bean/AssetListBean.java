package com.ps.app.support.Bean;

import java.io.Serializable;
import java.util.List;

public class AssetListBean implements Serializable{


    /**
     * code : 2000
     * data : {"lastPage":1,"navigatepageNums":[1],"startRow":1,"hasNextPage":false,"prePage":0,"nextPage":0,"endRow":1,"orderBy":"","pageSize":5,"list":[{"policeGroup":"1","createTime":1464762254000,"handleType":1,"caseTo":{"createTime":1464762254000,"name":"A案子","id":1},"startTime":1464762258000,"endTime":1465107863000,"id":1,"sn":"1","type":1,"memberTo":{"phone":"18998765678","displayName":"嫌疑犯赵","name":"A案子","id":1,"type":"OUT_OF_SUSPECT"}}],"pageNum":1,"navigatePages":8,"total":1,"pages":1,"firstPage":1,"size":1,"isLastPage":true,"hasPreviousPage":false,"isFirstPage":true}
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
     * list : [{"policeGroup":"1","createTime":1464762254000,"handleType":1,"caseTo":{"createTime":1464762254000,"name":"A案子","id":1},"startTime":1464762258000,"endTime":1465107863000,"id":1,"sn":"1","type":1,"memberTo":{"phone":"18998765678","displayName":"嫌疑犯赵","name":"A案子","id":1,"type":"OUT_OF_SUSPECT"}}]
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

    public static class DataBean implements Serializable{
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
         * policeGroup : 1
         * createTime : 1464762254000
         * handleType : 1
         * caseTo : {"createTime":1464762254000,"name":"A案子","id":1}
         * startTime : 1464762258000
         * endTime : 1465107863000
         * id : 1
         * sn : 1
         * type : 1
         * memberTo : {"phone":"18998765678","displayName":"嫌疑犯赵","name":"A案子","id":1,"type":"OUT_OF_SUSPECT"}
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

        public static class ListBean implements Serializable{
            private String policeGroup;
            private long createTime;
            private int handleType;
            /**
             * createTime : 1464762254000
             * name : A案子
             * id : 1
             */

            private CaseToBean caseTo;
            private long startTime;
            private long endTime;
            private int id;
            private String sn;
            private int type;
            /**
             * phone : 18998765678
             * displayName : 嫌疑犯赵
             * name : A案子
             * id : 1
             * type : OUT_OF_SUSPECT
             */

            private MemberToBean memberTo;

            public String getPoliceGroup() {
                return policeGroup;
            }

            public void setPoliceGroup(String policeGroup) {
                this.policeGroup = policeGroup;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getHandleType() {
                return handleType;
            }

            public void setHandleType(int handleType) {
                this.handleType = handleType;
            }

            public CaseToBean getCaseTo() {
                return caseTo;
            }

            public void setCaseTo(CaseToBean caseTo) {
                this.caseTo = caseTo;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public MemberToBean getMemberTo() {
                return memberTo;
            }

            public void setMemberTo(MemberToBean memberTo) {
                this.memberTo = memberTo;
            }

            public static class CaseToBean implements Serializable{
                private long createTime;
                private String name;
                private int id;

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class MemberToBean implements Serializable{
                private String phone;
                private String displayName;
                private String name;
                private int id;
                private String type;

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getDisplayName() {
                    return displayName;
                }

                public void setDisplayName(String displayName) {
                    this.displayName = displayName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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