package cn.com.news.dao;

import cn.com.news.dao.inter.IDao;
import cn.com.news.model.Article;
import cn.com.news.model.Content;
import cn.com.news.model.inter.IModel;
import cn.com.news.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/***
 * 评论处理类
 * @author XuanZP
 *
 */
public class ContentDao implements IDao {
	/***
	 * 保存用户评论信息
	 */
	public IModel save(IModel model) {
		Content content = (Content) model;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " insert into " + content.getTableName()
					+ " ( CONTENT,DATE,ISCHECKED,ARTICLEID,USERID ) "
					+ " values " + " (?,?,?,?,?) ";
			ps = conn.prepareStatement(sql, 1);
			ps.setString(1, content.getContent());
			ps.setString(2, content.getDate());
			ps.setInt(3, content.getIschecked());
			ps.setLong(4, content.getArticleId());
			ps.setLong(5, content.getUserId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			long id = -1L;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			content.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return content;
	}

	public boolean delete(IModel model) {
		return false;
	}

	public IModel update(IModel model) {
		return null;
	}

	public IModel getById(long id) {
		return null;
	}
	/***
	 * 查询文章评论信息
	 */
	public List<IModel> get(IModel model) {
		List data = new ArrayList();
		Article article = (Article) model;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select \tID, CONTENT, DATE, ISCHECKED, ARTICLEID, USERID from "
					+ new Content().getTableName()
					+ " where 1=1 and ARTICLEID = ? order by id ";

			ps = conn.prepareStatement(sql);
			ps.setLong(1, article.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Content content = new Content();
				long id = rs.getLong("ID");
				String contents = rs.getString("CONTENT");
				String date = rs.getString("DATE");
				int isChecked = rs.getInt("ISCHECKED");
				long articleId = rs.getLong("ARTICLEID");
				long userId = rs.getLong("USERID");
				content.setId(id);
				content.setContent(contents);
				content.setDate(date);
				content.setIschecked(isChecked);
				content.setArticleId(articleId);
				content.setUserId(userId);
				data.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return data;
	}

	@Override
	public List<IModel> getByParams(IModel paramIModel,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
}