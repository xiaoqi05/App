package com.ps.app.support.Bean;

public class AssetDetailBean {

    /**
     * code : 2000
     * data : {"policeGroup":"1","createTime":1464762254000,"handleType":1,"caseTo":{"createTime":1464762254000,"name":"A案子","id":1},"startTime":1464762258000,"endTime":1465107863000,"id":1,"sn":"1","type":1,"memberTo":{"phone":"18998765678","displayName":"嫌疑犯赵","name":"A案子","id":1,"type":"OUT_OF_SUSPECT"}}
     * error : 
     * desc : 成功!
     */

    private int code;
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

        public static class CaseToBean {
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

        public static class MemberToBean {
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