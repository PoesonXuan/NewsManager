package cn.com.news.model;

import cn.com.news.model.inter.IModel;
/***
 * 评论数据模型
 * @author XuanZP
 *
 */
public class Content implements IModel {
	private long id;
	private String content;
	private String date;
	private int ischecked;
	private long articleId;
	private long userId;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIschecked() {
		return this.ischecked;
	}

	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}

	public long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTableName() {
		return "CONTENT";
	}
}