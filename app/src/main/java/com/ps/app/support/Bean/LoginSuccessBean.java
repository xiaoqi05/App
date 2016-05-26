package com.ps.app.support.Bean;

public class LoginSuccessBean {

    /**
     * code : 2000
     * data : {"member":{"area":"四川省,成都市,锦江区","pnum":"5236456","gender":"UNKNOW","phone":"18607807538","name":"王晓明","id":57,"type":"POLICE","group":{"id":4,"type":"POLICE","desc":"内保大队"}},"token":{"signature":"3f85edbe71ba07de8f4d8bbc1e064981","timestamp":1464138835137}}
     * desc : 成功
     * error : 
     */

    private int code;
    /**
     * member : {"area":"四川省,成都市,锦江区","pnum":"5236456","gender":"UNKNOW","phone":"18607807538","name":"王晓明","id":57,"type":"POLICE","group":{"id":4,"type":"POLICE","desc":"内保大队"}}
     * token : {"signature":"3f85edbe71ba07de8f4d8bbc1e064981","timestamp":1464138835137}
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

    public static class DataBean {
        /**
         * area : 四川省,成都市,锦江区
         * pnum : 5236456
         * gender : UNKNOW
         * phone : 18607807538
         * name : 王晓明
         * id : 57
         * type : POLICE
         * group : {"id":4,"type":"POLICE","desc":"内保大队"}
         */

        private MemberBean member;
        /**
         * signature : 3f85edbe71ba07de8f4d8bbc1e064981
         * timestamp : 1464138835137
         */

        private TokenBean token;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public TokenBean getToken() {
            return token;
        }

        public void setToken(TokenBean token) {
            this.token = token;
        }

        public static class MemberBean {
            private String area;
            private String pnum;
            private String gender;
            private String phone;
            private String name;
            private int id;
            private String type;
            /**
             * id : 4
             * type : POLICE
             * desc : 内保大队
             */

            private GroupBean group;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getPnum() {
                return pnum;
            }

            public void setPnum(String pnum) {
                this.pnum = pnum;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
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

        public static class TokenBean {
            private String signature;
            private long timestamp;

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}