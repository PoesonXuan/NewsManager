package cn.com.news.model;

import cn.com.news.model.inter.IModel;
/***
 * 文章类型数据模型
 * @author XuanZP
 *
 */
public class ArticleTypeModel implements IModel {
	private long id;
	private int code;
	private String name;
	private String description;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTableName() {
		return "ARTICLETYPE";
	}
}