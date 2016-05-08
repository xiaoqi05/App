package com.ps.app.support.Bean;

public class ListBean {
    private String createTime;
    private int id;
    /**
     * content : 即将到期
     * id : 1
     * type : ASSET_MANAGEMENT_MSG
     */

    private MessageBean message;
    private int mid;
    private int successed;
    private int unread;

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

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
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

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public static class MessageBean {
        private String content;
        private int id;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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