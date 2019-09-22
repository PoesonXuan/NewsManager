package cn.com.news.dao;

import cn.com.news.dao.inter.IDao;
import cn.com.news.model.Article;
import cn.com.news.model.ArticleType;
import cn.com.news.model.User;
import cn.com.news.model.inter.IModel;
import cn.com.news.utils.Const;
import cn.com.news.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 文章数据处理类 Dao层
 * 
 * @author XuanZP
 * 
 */
public class ArticleDao implements IDao {
	/***
	 * 保存文章
	 */
	public IModel save(IModel model) {
		Article article = (Article) model;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " insert into "
					+ article.getTableName()
					+ " ( title , content , ARTICLETYPE , date  , userid , ischecked ) "
					+ " values " + " (?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql, 1);
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getContent());
			ps.setInt(3, article.getArticleType().index());
			ps.setString(4, article.getDate());
			ps.setLong(5, article.getUserid());
			ps.setInt(6, article.getIschecked());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			long id = -1L;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			article.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return article;
	}
	/***
	 * 删除文章
	 */
	public boolean delete(IModel model) {
		Article article = (Article) model;
		boolean result = false;
		String sql = " delete from " + model.getTableName() + " where id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			String sql0 = "delete from  content   where  ARTICLEID= ? ";
			

			conn = DBUtils.getConn();
			
			ps = conn.prepareStatement(sql0);
			ps.setLong(1, article.getId());
			int ss = ps.executeUpdate();

			
			
			
			
			
			
			
			ps = conn.prepareStatement(sql);
			ps.setLong(1, article.getId());
			int num = ps.executeUpdate();
			if (num > 0)
				result = true;
			else
				result = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return result;
	}
	/***
	 * 更新文章状态
	 */
	public IModel update(IModel model) {
		Article article = (Article) model;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " update " + article.getTableName()
				+ " set ischecked = ?,checkInfo = ? ";
		if (Const.USER_CURRENT != null) {
			sql = sql + " , admin = " + Const.USER_CURRENT.getId();
		}
		sql = sql + " where 1=1 and  id =  ?  ";
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, article.getIschecked());
			ps.setString(2, article.getCheckInfo());
			ps.setLong(3, article.getId());
			int num = ps.executeUpdate();
			article.setIschecked(num);
		} catch (SQLException e) {
			e.printStackTrace();
			article.setIschecked(0);
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return article;
	}
	/***
	 * 根据主键ID查询文章
	 */
	public IModel getById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List ls = new ArrayList();
		Article article = new Article();
		article.setId(-1L);
		try {
			conn = DBUtils.getConn();
			String sql = " select id,title , content , ARTICLETYPE , date  , ischecked,userid from "
					+ new Article().getTableName()
					+ " where 1=1 "
					+ " AND  id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getLong("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int articleType = rs.getInt("ARTICLETYPE");
				String date = rs.getString("date");
				int ischecked = rs.getInt("ischecked");
				long userId = rs.getLong("userid");
				article.setId(id);
				article.setTitle(title);
				article.setContent(content);
				article.setArticleType(ArticleType.indexOf(articleType));
				article.setDate(date);
				article.setIschecked(ischecked);
				article.setUserid(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return article;
	}
	/***
	 * 获取文章集合
	 */
	public List<IModel> get(IModel model) {
		User user = null;
		Article article = null;
		if ((model instanceof User))
			user = (User) model;
		else if ((model instanceof Article)) {
			article = (Article) model;
		}

		List data = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List ls = new ArrayList();
		try {
			conn = DBUtils.getConn();
			String sql = " select id,title , content , ARTICLETYPE , date  , ischecked,checkInfo,userid from "
					+ new Article().getTableName() + " where 1=1 ";
			if (article != null)
				sql = sql + "  and ischecked = 1  and ARTICLETYPE = ? ";
			else if (user.getType() == 0)
				sql = sql + " and ischecked = 0 ";
			else {
				sql = sql + " AND  userid = ? ";
			}
			ps = conn.prepareStatement(sql);

			if (article != null)
				ps.setInt(1, article.getArticleType().index());
			else if (user.getType() != 0)
				ps.setLong(1, user.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int articleType = rs.getInt("ARTICLETYPE");
				String date = rs.getString("date");
				int ischecked = rs.getInt("ischecked");
				long userId = rs.getLong("userid");
				String checkInfo = rs.getString("checkInfo");
				
				article = new Article();
				article.setId(id);
				article.setTitle(title);
				article.setContent(content);
				article.setArticleType(ArticleType.indexOf(articleType));
				article.setDate(date);
				article.setIschecked(ischecked);
				article.setUserid(userId);
				article.setCheckInfo(checkInfo);
				
				data.add(article);
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
	public List<IModel> getByParams(IModel model,
			Map<String, Object> params) {
		User user = null;
		Article article = null;
		if ((model instanceof Article)) {
			article = (Article) model;
		}

		List data = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List ls = new ArrayList();
		try {
			conn = DBUtils.getConn();
			StringBuffer sb = new StringBuffer();
			
			sb.append(" select id,title , content , ARTICLETYPE , date  , ischecked,userid from "
					+ new Article().getTableName() + " where 1=1 ");
			
			if(params != null && params.size()>0){
				Set st = params.keySet();
				for (Object key : st) {
					sb.append(" and "+key+" = "+params.get(key)+" ");
				}
			}
			
			ps = conn.prepareStatement(sb.toString());

			/*if (article != null)
				ps.setInt(1, article.getArticleType().index());
			else if (user.getType() != 0)
				ps.setLong(1, user.getId());*/
			rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int articleType = rs.getInt("ARTICLETYPE");
				String date = rs.getString("date");
				int ischecked = rs.getInt("ischecked");
				long userId = rs.getLong("userid");
				article = new Article();
				article.setId(id);
				article.setTitle(title);
				article.setContent(content);
				article.setArticleType(ArticleType.indexOf(articleType));
				article.setDate(date);
				article.setIschecked(ischecked);
				article.setUserid(userId);
				data.add(article);
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
}