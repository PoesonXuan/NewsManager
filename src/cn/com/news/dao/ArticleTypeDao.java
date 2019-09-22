package cn.com.news.dao;

import cn.com.news.dao.inter.IDao;
import cn.com.news.model.ArticleTypeModel;
import cn.com.news.model.inter.IModel;
import cn.com.news.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * 文章类型数据处理类 Dao层
 * 
 * @author XuanZP
 * 
 */
public class ArticleTypeDao implements IDao {
	public IModel save(IModel model) {
		return null;
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
	 * 批量查询文章类型
	 */
	public List<IModel> get(IModel model) {
		List ls = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " select id,code,name,description from "
					+ model.getTableName() + " where 1=1 ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				ArticleTypeModel m = new ArticleTypeModel();
				m.setId(id);
				m.setCode(code);
				m.setDescription(description);
				m.setName(name);
				ls.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public List<IModel> getByParams(IModel paramIModel,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
}