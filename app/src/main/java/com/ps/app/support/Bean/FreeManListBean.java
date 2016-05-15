package com.ps.app.support.Bean;

import java.io.Serializable;
import java.util.List;

public class FreeManListBean implements Serializable{


    /**
     * code : 2000
     * data : {"total":12,"lastPage":2,"hasNextPage":true,"nextPage":2,"pages":2,"pageSize":10,"navigatePages":8,"hasPreviousPage":false,"list":[{"releaseStartTime":1464963698000,"createTime":1463258633000,"arrestEndTime":1465136535000,"memberTo":{"id":48,"phone":"13682070860","name":"赵中华","gender":"UNKNOW","group":{"id":4,"desc":"法制大队","type":"POLICE"}},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":1,"level":1,"name":"A案子","arrestStartTime":1464877331000,"memberName":"张三"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":54,"phone":"18681223692","idCard":"254568458555","name":"ps普通版","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":2,"level":1,"name":"B案子","arrestStartTime":1464877331000,"memberName":"李四"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":55,"phone":"13067845436","idCard":"52375665","name":"p普通版","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":3,"level":1,"name":"C案子","arrestStartTime":1464877331000,"memberName":"王五"},{"releaseStartTime":1464963698000,"createTime":1463289237000,"arrestEndTime":1465136535000,"memberTo":{"id":56,"phone":"13255800712","idCard":"411422198704100039","name":"周杰伦","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":4,"level":1,"name":"D案子","arrestStartTime":1464877331000,"memberName":"A张麻子"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":66,"phone":"15692885817","idCard":"411422198704100039","name":"李武","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":5,"level":1,"name":"E案子","arrestStartTime":1464877331000,"memberName":"李武"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":61,"phone":"13682070890","idCard":"5108241991028412323","area":"四川省靖江区","name":"赵伟","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":6,"level":1,"name":"F案子","arrestStartTime":1464877331000,"memberName":"黄鑫"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":7,"level":1,"name":"A1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":8,"level":1,"name":"B1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258637000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":9,"level":1,"name":"C1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258637000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":10,"level":1,"name":"D1案子","arrestStartTime":1464877331000,"memberName":"3"}],"navigatepageNums":[1,2],"size":10,"pageNum":1,"prePage":0,"endRow":10,"isLastPage":false,"startRow":1,"firstPage":1,"isFirstPage":true}
     * desc : 成功!
     * error : 
     */

    private int code;
    /**
     * total : 12
     * lastPage : 2
     * hasNextPage : true
     * nextPage : 2
     * pages : 2
     * pageSize : 10
     * navigatePages : 8
     * hasPreviousPage : false
     * list : [{"releaseStartTime":1464963698000,"createTime":1463258633000,"arrestEndTime":1465136535000,"memberTo":{"id":48,"phone":"13682070860","name":"赵中华","gender":"UNKNOW","group":{"id":4,"desc":"法制大队","type":"POLICE"}},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":1,"level":1,"name":"A案子","arrestStartTime":1464877331000,"memberName":"张三"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":54,"phone":"18681223692","idCard":"254568458555","name":"ps普通版","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":2,"level":1,"name":"B案子","arrestStartTime":1464877331000,"memberName":"李四"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":55,"phone":"13067845436","idCard":"52375665","name":"p普通版","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":3,"level":1,"name":"C案子","arrestStartTime":1464877331000,"memberName":"王五"},{"releaseStartTime":1464963698000,"createTime":1463289237000,"arrestEndTime":1465136535000,"memberTo":{"id":56,"phone":"13255800712","idCard":"411422198704100039","name":"周杰伦","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":4,"level":1,"name":"D案子","arrestStartTime":1464877331000,"memberName":"A张麻子"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":66,"phone":"15692885817","idCard":"411422198704100039","name":"李武","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":5,"level":1,"name":"E案子","arrestStartTime":1464877331000,"memberName":"李武"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"id":61,"phone":"13682070890","idCard":"5108241991028412323","area":"四川省靖江区","name":"赵伟","gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":6,"level":1,"name":"F案子","arrestStartTime":1464877331000,"memberName":"黄鑫"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":7,"level":1,"name":"A1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258636000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":8,"level":1,"name":"B1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258637000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":9,"level":1,"name":"C1案子","arrestStartTime":1464877331000,"memberName":"3"},{"releaseStartTime":1464963698000,"createTime":1463258637000,"arrestEndTime":1465136535000,"memberTo":{"gender":"UNKNOW"},"remark":"备注","detainEndTime":1465136324000,"shiftTime":1465136538000,"prisonedEndTime":1465136527000,"releaseEndTime":1465050114000,"detainStartTime":1464877121000,"prisonedStartTime":1464877317000,"policeName":"张警官","id":10,"level":1,"name":"D1案子","arrestStartTime":1464877331000,"memberName":"3"}]
     * navigatepageNums : [1,2]
     * size : 10
     * pageNum : 1
     * prePage : 0
     * endRow : 10
     * isLastPage : false
     * startRow : 1
     * firstPage : 1
     * isFirstPage : true
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
        private int total;
        private int lastPage;
        private boolean hasNextPage;
        private int nextPage;
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
         * releaseStartTime : 1464963698000
         * createTime : 1463258633000
         * arrestEndTime : 1465136535000
         * memberTo : {"id":48,"phone":"13682070860","name":"赵中华","gender":"UNKNOW","group":{"id":4,"desc":"法制大队","type":"POLICE"}}
         * remark : 备注
         * detainEndTime : 1465136324000
         * shiftTime : 1465136538000
         * prisonedEndTime : 1465136527000
         * releaseEndTime : 1465050114000
         * detainStartTime : 1464877121000
         * prisonedStartTime : 1464877317000
         * policeName : 张警官
         * id : 1
         * level : 1
         * name : A案子
         * arrestStartTime : 1464877331000
         * memberName : 张三
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
            private long releaseStartTime;
            private long createTime;
            private long arrestEndTime;
            /**
             * id : 48
             * phone : 13682070860
             * name : 赵中华
             * gender : UNKNOW
             * group : {"id":4,"desc":"法制大队","type":"POLICE"}
             */

            private MemberToBean memberTo;
            private String remark;
            private long detainEndTime;
            private long shiftTime;
            private long prisonedEndTime;
            private long releaseEndTime;
            private long detainStartTime;
            private long prisonedStartTime;
            private String policeName;
            private int id;
            private int level;
            private String name;
            private long arrestStartTime;
            private String memberName;

            public long getReleaseStartTime() {
                return releaseStartTime;
            }

            public void setReleaseStartTime(long releaseStartTime) {
                this.releaseStartTime = releaseStartTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getArrestEndTime() {
                return arrestEndTime;
            }

            public void setArrestEndTime(long arrestEndTime) {
                this.arrestEndTime = arrestEndTime;
            }

            public MemberToBean getMemberTo() {
                return memberTo;
            }

            public void setMemberTo(MemberToBean memberTo) {
                this.memberTo = memberTo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getDetainEndTime() {
                return detainEndTime;
            }

            public void setDetainEndTime(long detainEndTime) {
                this.detainEndTime = detainEndTime;
            }

            public long getShiftTime() {
                return shiftTime;
            }

            public void setShiftTime(long shiftTime) {
                this.shiftTime = shiftTime;
            }

            public long getPrisonedEndTime() {
                return prisonedEndTime;
            }

            public void setPrisonedEndTime(long prisonedEndTime) {
                this.prisonedEndTime = prisonedEndTime;
            }

            public long getReleaseEndTime() {
                return releaseEndTime;
            }

            public void setReleaseEndTime(long releaseEndTime) {
                this.releaseEndTime = releaseEndTime;
            }

            public long getDetainStartTime() {
                return detainStartTime;
            }

            public void setDetainStartTime(long detainStartTime) {
                this.detainStartTime = detainStartTime;
            }

            public long getPrisonedStartTime() {
                return prisonedStartTime;
            }

            public void setPrisonedStartTime(long prisonedStartTime) {
                this.prisonedStartTime = prisonedStartTime;
            }

            public String getPoliceName() {
                return policeName;
            }

            public void setPoliceName(String policeName) {
                this.policeName = policeName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getArrestStartTime() {
                return arrestStartTime;
            }

            public void setArrestStartTime(long arrestStartTime) {
                this.arrestStartTime = arrestStartTime;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public static class MemberToBean implements Serializable{
                private int id;
                private String phone;
                private String name;
                private String idCard;

                private String gender;

                public String getIdCard() {
                    return idCard;
                }

                public void setIdCard(String idCard) {
                    this.idCard = idCard;
                }
                /**
                 * id : 4
                 * desc : 法制大队
                 * type : POLICE
                 */

                private GroupBean group;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
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

                public GroupBean getGroup() {
                    return group;
                }

                public void setGroup(GroupBean group) {
                    this.group = group;
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
}