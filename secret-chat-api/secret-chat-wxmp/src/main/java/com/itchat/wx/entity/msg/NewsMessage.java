package com.itchat.wx.entity.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 17:49
 * @Description 图文消息
 * @Version V1.0
 */
@XStreamAlias("xml")
public class NewsMessage {

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("ArticleCount")
    private Integer articleCount = 1;

    @XStreamAlias("Articles")
    private List<ArticlesItem> articles;


    @XStreamAlias("item")
    public static class ArticlesItem {
        @XStreamAlias("Title")
        private String title;

        @XStreamAlias("Description")
        private String description;

        @XStreamAlias("PicUrl")
        private String picUrl;

        @XStreamAlias("Url")
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public List<ArticlesItem> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesItem> articles) {
        this.articles = articles;
    }
}
