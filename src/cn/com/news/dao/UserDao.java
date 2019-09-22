package cn.com.news.dao;

import cn.com.news.dao.inter.IDao;
import cn.com.news.model.User;
import cn.com.news.model.inter.IModel;
import cn.com.news.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/***
 * 用户信息查询类 dao 层
 * @author XuanZP
 *
 */
public class UserDao implements IDao {
	/***
	 * 保存用户信息
	 */
	public IModel save(IModel model) {
		User user = (User) model;
		if (get(user).size() > 0) {
			user.setId(-1L);
			return user;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = " insert into " + user.getTableName()
					+ " (userName,loginName,password,phone,post,type) "
					+ " values " + " (?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql, 1);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getLoginName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getPost());
			ps.setInt(6, user.getType());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			long id = -1L;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			user.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return user;
	}

	public boolean delete(IModel model) {
		return false;
	}

	public IModel update(IModel model) {
		return null;
	}
	/***
	 * 根据主键查询用户信息
	 */
	public IModel getById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = new User();
		u.setId(-1L);
		try {
			conn = DBUtils.getConn();
			String sql = " select id,userName,loginname,password,phone,post,type from "
					+ u.getTableName() + " where 1=1 " + " AND  id  = ?   ";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				long id2 = rs.getLong("id");
				String userName = rs.getString("userName");
				String loginname = rs.getString("loginname");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String post = rs.getString("post");
				int type = rs.getInt("type");

				u.setId(id2);
				u.setLoginName(loginname);
				u.setPassword(password);
				u.setPhone(phone);
				u.setPost(post);
				u.setType(type);
				u.setUserName(userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
		}
		return u;
	}
	/***
	 * 获取用户列表
	 */
	public List<IModel> get(IModel model) {
		User user = (User) model;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List ls = new ArrayList();
		try {
			conn = DBUtils.getConn();
			String sql = " select id,userName,loginname,password,phone,post,type from "
					+ user.getTableName()
					+ " where 1=1 "
					+ " AND  loginname = ? " + " AND  password = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getLoginName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String userName = rs.getString("userName");
				String loginname = rs.getString("loginname");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String post = rs.getString("post");
				int type = rs.getInt("type");
				User u = new User();
				u.setId(id);
				u.setLoginName(loginname);
				u.setPassword(password);
				u.setPhone(phone);
				u.setPost(post);
				u.setType(type);
				u.setUserName(userName);
				ls.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
			DBUtils.closeConn(conn);
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