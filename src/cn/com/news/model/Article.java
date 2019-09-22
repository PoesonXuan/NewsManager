package cn.com.news.model;

import cn.com.news.model.inter.IModel;
import cn.com.news.utils.Const;
/***
 * 文章模型
 * @author XuanZP
 *
 */
public class Article implements IModel {
	private long id;
	private String title;
	private String content;
	private ArticleType articleType;
	private String date;
	private long userid;
	private long admin;
	private int ischecked;
	private String checkInfo;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleType getArticleType() {
		return this.articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getIschecked() {
		return this.ischecked;
	}

	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}

	public String getTableName() {
		return "ARTICLE";
	}

	public long getAdmin() {
		return this.admin;
	}

	public void setAdmin(long admin) {
		this.admin = admin;
	}

	public String getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	public String getCheckedResult(){
		if(Const.ARTICLE_ISCHECKED_TRUE_BAD == this.ischecked)
			return "审核未同过";
		else if(Const.ARTICLE_ISCHECKED_FALSE == this.ischecked)
			return "未审核";
		else if(Const.ARTICLE_ISCHECKED_TRUE == this.ischecked)
			return "已审核";
		return "";
	}
	
}