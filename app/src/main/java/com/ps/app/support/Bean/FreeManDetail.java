package com.ps.app.support.Bean;

public class FreeManDetail {

    /**
     * code : 2000
     * data : {"area":"","gender":"UNKNOW","createTime":1462488712000,"phone":"18692885801","displayName":"","idCard":"","name":"王警官","id":17,"type":"OUT_OF_SUSPECT","group":{"id":1,"type":"POLICE","desc":"A分局"}}
     * error : 
     * desc : 成功!
     */

    private int code;
    /**
     * area : 
     * gender : UNKNOW
     * createTime : 1462488712000
     * phone : 18692885801
     * displayName : 
     * idCard : 
     * name : 王警官
     * id : 17
     * type : OUT_OF_SUSPECT
     * group : {"id":1,"type":"POLICE","desc":"A分局"}
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
        private String area;
        private String gender;
        private long createTime;
        private String phone;
        private String displayName;
        private String idCard;
        private String name;
        private int id;
        private String type;
        /**
         * id : 1
         * type : POLICE
         * desc : A分局
         */

        private GroupBean group;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
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

        public GroupBean getGroup() {
            return group;
        }

        public void setGroup(GroupBean group) {
            this.group = group;
        }

        public static class GroupBean {
            private int id;
            private String type;
            private String desc;

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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}