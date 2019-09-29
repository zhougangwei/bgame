package com.pay.administrator.bgame.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author wjy on 2019/9/23/023.
 */
public class NoticeBean extends BaseBean{
    /**
     * data : {"total":1,"list":[{"id":16,"type":"ALL","title":"你好啊","content":"我真帅","titleEn":"hello","contentEn":"i am shy","titleAl":"hellohellohello","contentAl":"hellohello","publishState":1,"publishUserId":1,"publishTime":"2019-09-23 20:01:00","noticeUserId":null,"createUserId":1,"createTime":"1569240058457"}],"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
     * userid : null
     */

    @SerializedName("data")
    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * total : 1
         * list : [{"id":16,"type":"ALL","title":"你好啊","content":"我真帅","titleEn":"hello","contentEn":"i am shy","titleAl":"hellohellohello","contentAl":"hellohello","publishState":1,"publishUserId":1,"publishTime":"2019-09-23 20:01:00","noticeUserId":null,"createUserId":1,"createTime":"1569240058457"}]
         * pageNum : 1
         * pageSize : 10
         * size : 1
         * startRow : 1
         * endRow : 1
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        @SerializedName("total")
        private int total;
        @SerializedName("pageNum")
        private int pageNum;
        @SerializedName("pageSize")
        private int pageSize;
        @SerializedName("size")
        private int size;
        @SerializedName("startRow")
        private int startRow;
        @SerializedName("endRow")
        private int endRow;
        @SerializedName("pages")
        private int pages;
        @SerializedName("prePage")
        private int prePage;
        @SerializedName("nextPage")
        private int nextPage;
        @SerializedName("isFirstPage")
        private boolean isFirstPage;
        @SerializedName("isLastPage")
        private boolean isLastPage;
        @SerializedName("hasPreviousPage")
        private boolean hasPreviousPage;
        @SerializedName("hasNextPage")
        private boolean hasNextPage;
        @SerializedName("navigatePages")
        private int navigatePages;
        @SerializedName("navigateFirstPage")
        private int navigateFirstPage;
        @SerializedName("navigateLastPage")
        private int navigateLastPage;
        @SerializedName("firstPage")
        private int firstPage;
        @SerializedName("lastPage")
        private int lastPage;
        @SerializedName("list")
        private List<ListBean> list;
        @SerializedName("navigatepageNums")
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
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

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
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

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
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

        public static class ListBean implements Serializable {
            /**
             * id : 16
             * type : ALL
             * title : 你好啊
             * content : 我真帅
             * titleEn : hello
             * contentEn : i am shy
             * titleAl : hellohellohello
             * contentAl : hellohello
             * publishState : 1
             * publishUserId : 1
             * publishTime : 2019-09-23 20:01:00
             * noticeUserId : null
             * createUserId : 1
             * createTime : 1569240058457
             */

            @SerializedName("id")
            private int id;
            @SerializedName("type")
            private String type;
            @SerializedName("title")
            private String title;
            @SerializedName("content")
            private String content;
            @SerializedName("titleEn")
            private String titleEn;
            @SerializedName("contentEn")
            private String contentEn;
            @SerializedName("titleAl")
            private String titleAl;
            @SerializedName("contentAl")
            private String contentAl;
            @SerializedName("publishState")
            private int publishState;
            @SerializedName("publishUserId")
            private int publishUserId;
            @SerializedName("publishTime")
            private String publishTime;
            @SerializedName("noticeUserId")
            private Object noticeUserId;
            @SerializedName("createUserId")
            private int createUserId;
            @SerializedName("createTime")
            private String createTime;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitleEn() {
                return titleEn;
            }

            public void setTitleEn(String titleEn) {
                this.titleEn = titleEn;
            }

            public String getContentEn() {
                return contentEn;
            }

            public void setContentEn(String contentEn) {
                this.contentEn = contentEn;
            }

            public String getTitleAl() {
                return titleAl;
            }

            public void setTitleAl(String titleAl) {
                this.titleAl = titleAl;
            }

            public String getContentAl() {
                return contentAl;
            }

            public void setContentAl(String contentAl) {
                this.contentAl = contentAl;
            }

            public int getPublishState() {
                return publishState;
            }

            public void setPublishState(int publishState) {
                this.publishState = publishState;
            }

            public int getPublishUserId() {
                return publishUserId;
            }

            public void setPublishUserId(int publishUserId) {
                this.publishUserId = publishUserId;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public Object getNoticeUserId() {
                return noticeUserId;
            }

            public void setNoticeUserId(Object noticeUserId) {
                this.noticeUserId = noticeUserId;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
