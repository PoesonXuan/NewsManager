package cn.com.news.servlet;

import cn.com.news.dao.ArticleDao;
import cn.com.news.dao.ArticleTypeDao;
import cn.com.news.dao.ContentDao;
import cn.com.news.dao.UserDao;
import cn.com.news.dao.inter.IDao;
import cn.com.news.model.Article;
import cn.com.news.model.ArticleType;
import cn.com.news.model.ArticleTypeModel;
import cn.com.news.model.Content;
import cn.com.news.model.User;
import cn.com.news.model.inter.IModel;
import cn.com.news.server.Server;
import cn.com.news.utils.Const;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/***
 * servlet 控制层用户接受用户请求并调用server，同时返回数据
 * @author XuanZP
 *
 */
public class AppServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op = req.getParameter("op");
		Server server = null;
		if ((op != null) && (!"".equals(op))) {
			if ("register".equals(op)) {
				String userName = req.getParameter("userName");
				String loginName = req.getParameter("loginName");
				String password = req.getParameter("password");
				String repassword = req.getParameter("repassword");
				String phone = req.getParameter("phone");
				String post = req.getParameter("post");

				User user = new User();
				user.setLoginName(loginName);
				user.setPassword(repassword);
				user.setPhone(phone);
				user.setPost(post);
				user.setUserName(userName);
				user.setType(3);
				
				
				
				if (server == null) {
					server = new Server();
				}
				IDao dao = new UserDao();
				server.setDao(dao);
				List users = server.get(user);
				if (users.size() > 1) {
					req.setAttribute("message", "注册失败，已存在相同用户信息！");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				}else{
					if (server == null) {
						server = new Server();
					}
					dao = new UserDao();
					server.setDao(dao);
					user = (User) server.save(user);
					if (user.getId() < 0L) {
						req.setAttribute("message", "注册失败，请核查信息！");
					} else {
						req.setAttribute("currentUser", user);
						req.setAttribute("message",
								"注册成功！<a href='login.jsp'>进入登录界面<a>");
					}
					req.getRequestDispatcher("/message.jsp").forward(req, resp);	
				}
				
				
				
				
				
				
			} else if ("login".equals(op)) {
				String userName = req.getParameter("userName");
				String password = req.getParameter("password");
				User user = new User();
				user.setPassword(password);
				user.setLoginName(userName);
				user.setType(3);
				if (server == null) {
					server = new Server();
				}
				IDao dao = new UserDao();
				server.setDao(dao);
				List users = server.get(user);
				if (users.size() > 1) {
					req.setAttribute("message", "登录失败，请核查信息！");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else if (users.size() == 1) {
					user = (User) users.get(0);
					if (user.getId() < 0L) {
						req.setAttribute("message", "登录失败，请核查信息！");
						req.getRequestDispatcher("/message.jsp").forward(req,
								resp);
					} else {
						Const.USER_CURRENT = user;
						req.getSession().setAttribute("currentUser", user);
						req.getRequestDispatcher("/main.jsp")
								.forward(req, resp);
					}
				} else {
					req.setAttribute("message", "登录失败，请核查信息！");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				}
			} else if ("myInfo".equals(op)) {
				User user = (User) req.getSession().getAttribute("currentUser");
				if (user == null) {
					req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else if (user.getType() == 0) {
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					List data = server.get(user);
					req.setAttribute("list", data);
					req.getRequestDispatcher("/admin.jsp").forward(req, resp);
				} else {
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					List data = server.get(user);
					req.setAttribute("list", data);
					req.getRequestDispatcher("/myInfo.jsp").forward(req, resp);
				}
			} else if ("write".equals(op)) {
				User user = (User) req.getSession().getAttribute("currentUser");
				if (user == null) {
					req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else if (1 == user.getType()) {
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleTypeDao();
					server.setDao(dao);
					List models = server.get(new ArticleTypeModel());
					req.setAttribute("types", models);
					req.getRequestDispatcher("/write.jsp").forward(req, resp);
				} else {
					req.setAttribute("message", "用户不具备写权限");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				}
			} else if ("writeUpload".equals(op)) {
				User user = (User) req.getSession().getAttribute("currentUser");
				if (user == null) {
					req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else {
					String title = req.getParameter("title");
					String type = req.getParameter("newsType");
					String content = req.getParameter("TextArea");

					Article article = new Article();
					article.setTitle(title);
					article.setContent(content);

					article.setArticleType(Const.getArticleType(type));

					article.setDate(Const.dateToString(new Date()));
					article.setIschecked(0);
					article.setUserid(user.getId());

					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					article = (Article) server.save(article);
					String info = "";
					if (article.getId() < 0L)
						info = "保存失败";
					else {
						info = "保存成功";
					}
					req.setAttribute("message", info);
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				}

			} else if ("articleInfo".equals(op)) {
				String idStr = req.getParameter("articleId");
				Long id = Long.valueOf(Long.parseLong(idStr));
				if (server == null) {
					server = new Server();
				}
				IDao dao = new ArticleDao();
				server.setDao(dao);
				Article article = (Article) server.getById(id.longValue());

				StringBuffer info = new StringBuffer();
				info.append("<h1>" + article.getTitle() + "</h1>");
				info.append("<h6>时间:" + article.getDate() + "</h6>");
				info.append(article.getContent());

				req.setAttribute("message", info);
				req.getRequestDispatcher("/message.jsp").forward(req, resp);
			} else if ("articleDelete".equals(op)) {
				User user = (User) req.getSession().getAttribute("currentUser");
				String idStr = req.getParameter("articleid");
				/*String checkInfo = req.getParameter("checkInfo");*/
				if (user == null) {
					req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else if ((idStr != null) && (!"".equals(idStr))) {
					long id = Long.parseLong(idStr);
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					
					Article article1 = (Article) server.getById(id);
					
					Article article = new Article();
					article.setId(id);
					/*article.setCheckInfo(checkInfo);*/
					if (server.delete(article)){
						if(article1.getIschecked() == Const.ARTICLE_ISCHECKED_TRUE){
							req.setAttribute("message",
									"删除完成，<a href='appServlet?op=articleChecked'>是否继续<a>?");
						} else{
							req.setAttribute("message",
									"删除完成，<a href='appServlet?op=articleCheck'>继续审核<a>?");
						}
					}else {
						if(article1.getIschecked() == Const.ARTICLE_ISCHECKED_TRUE){

							req.setAttribute("message",
									"删除失败，<a href='appServlet?op=articleChecked'>是否继续<a>?");
						}else{
							req.setAttribute("message",
									"删除失败，<a href='appServlet?op=articleCheck'>继续审核<a>?");	
						}
						
					}
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				}
			} else {
				Article article;
				if ("articleChecked".equals(op)) {
					User user = (User) req.getSession().getAttribute(
							"currentUser");
					if (user == null) {
						req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
						req.getRequestDispatcher("/message.jsp").forward(req,
								resp);
					}else{
						if (server == null) {
							server = new Server();
						}
						IDao dao = new ArticleDao();
						server.setDao(dao);
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("ischecked", Const.ARTICLE_ISCHECKED_TRUE);
						List data = server.getByParams(new Article(), params );/*server.get(user);*/
						req.setAttribute("articles", data);
						req.getRequestDispatcher("/articleList.jsp").forward(
								req, resp);
					}
				}else if ("articleCheck".equals(op)) {
				
					User user = (User) req.getSession().getAttribute(
							"currentUser");
					String idStr = req.getParameter("articleid");

					if (user == null) {
						req.setAttribute("message", "未查询到登录信息！请先登录，谢谢合作");
						req.getRequestDispatcher("/message.jsp").forward(req,
								resp);
					} else if ((idStr != null) && (!"".equals(idStr))) {
						String resultStr = req.getParameter("result");
						
						if ((resultStr == null) || ("".equals(resultStr))) {
							long id = Long.parseLong(idStr);
							if (server == null) {
								server = new Server();
							}
							IDao dao = new ArticleDao();
							server.setDao(dao);
							article = (Article) server.getById(id);
							req.setAttribute("article", article);
							req.getRequestDispatcher("/check.jsp").forward(req,
									resp);
						} else {
							
							String checkInfo = req.getParameter("checkInfo");
							checkInfo = new String(checkInfo.getBytes("ISO-8859-1"), "UTF-8");
							
							long id = Long.parseLong(idStr);
							int check = Integer.parseInt(resultStr);
							if (server == null) {
								server = new Server();
							}
							IDao dao = new ArticleDao();
							server.setDao(dao);
							article = new Article();
							article.setId(id);
							article.setIschecked(check);
							article.setCheckInfo(checkInfo);
							article = (Article) server.update(article);
							if (article.getIschecked() > 0)
								req.setAttribute("message",
										"审核完成，<a href='appServlet?op=articleCheck'>继续审核<a>?");
							else {
								req.setAttribute("message",
										"审核失败，<a href='appServlet?op=articleCheck'>继续审核<a>?");
							}
							req.getRequestDispatcher("/message.jsp").forward(
									req, resp);
						}
					} else {
						if (server == null) {
							server = new Server();
						}
						IDao dao = new ArticleDao();
						server.setDao(dao);
						List data = server.get(user);
						req.setAttribute("articles", data);
						req.getRequestDispatcher("/articleList.jsp").forward(
								req, resp);
					}

				} else if ("userPermission".equals(op)) {
					req.setAttribute("message", "用户权限管理");
					req.getRequestDispatcher("/message.jsp").forward(req, resp);
				} else if ("articleList".equals(op)) {
					String typeStr = req.getParameter("articleType");
					int type = Integer.parseInt(typeStr);
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					article = new Article();
					article.setArticleType(ArticleType.indexOf(type));
					List<IModel> data = server.get(article);

					StringBuffer sb = new StringBuffer();
					sb.append("{result:'success',info:'',error:'',data:[ ");
					for (IModel iModel : data) {
						article = (Article) iModel;
						sb.append("{ id : " + article.getId() + " , "
								+ " title : '" + article.getTitle() + "' , "
								+ " articleType : "
								+ article.getArticleType().index() + " , "
								+ " date : '" + article.getDate() + "' , "
								+ " checkedResult : '" + article.getCheckedResult() + "' , "
								+ " checkInfo : '" + article.getCheckInfo() + "' , "
										
								+ " userid : " + article.getUserid() + " , "
								+ " ischecked : " + article.getIschecked()
								+ "},");
					}

					sb.replace(sb.lastIndexOf(","), sb.length(), "");
					sb.append("]}");

					PrintWriter out = null;
					out = resp.getWriter();
					out.write(sb.toString());
					out.flush();
					out.close();
				} else if ("articleDetail".equals(op)) {
					String idStr = req.getParameter("articleid");
					long id = Long.parseLong(idStr);
					if (server == null) {
						server = new Server();
					}
					IDao dao = new ArticleDao();
					server.setDao(dao);
					article = new Article();
					article.setId(id);
					article = (Article) server.getById(id);
					req.setAttribute("article", article);
					req.getRequestDispatcher("/article.jsp").forward(req, resp);
				} else if ("sendContent".equals(op)) {
					StringBuffer sb = new StringBuffer();
					String content = req.getParameter("content");
					String idStr = req.getParameter("articleId");
					if ((Const.USER_CURRENT == null) && (content != null)
							&& (!"".equals(content))) {
						sb.append("{result:'failure',info:'login',error:'',data:[ ]}");
					} else {
						long articleId = Long.parseLong(idStr);
						article = new Article();
						article.setId(articleId);
						Content contentObj = new Content();
						if ((content != null) && (!"".equals(content))) {
							contentObj.setArticleId(articleId);
							contentObj.setContent(content);
							contentObj.setDate(Const.sdf.format(new Date()));
							contentObj.setIschecked(0);
							contentObj.setUserId(Const.USER_CURRENT.getId());

							if (server == null) {
								server = new Server();
							}
							IDao dao = new ContentDao();
							server.setDao(dao);
							contentObj = (Content) server.save(contentObj);
						}
						if ((contentObj.getId() < 0L) && (content != null)
								&& (!"".equals(content))) {
							sb.append("{result:'failure',info:'bad',error:'',data:[ ]}");
						} else {
							if (server == null) {
								server = new Server();
							}
							IDao dao = new ContentDao();
							server.setDao(dao);
							sb.append("{result:'success',info:'',error:'',data:[ ");
							List<IModel> data = server.get(article);
							server.setDao(new UserDao());
							boolean have = false;
							for (IModel iModel : data) {
								Content c = (Content) iModel;
								User u = (User) server.getById(c.getUserId());
								sb.append("{ ");
								sb.append(" id : " + c.getId() + " , ");
								sb.append(" content : '" + c.getContent()
										+ "' , ");
								sb.append(" date : '" + c.getDate() + "' , ");
								sb.append(" articleId : " + c.getArticleId()
										+ " , ");
								sb.append(" ischecked : " + c.getIschecked()
										+ " , ");
								
								sb.append(" UserId : " + c.getUserId() + " , ");
								sb.append(" userName : '" + u.getUserName()
										+ "'  ");
								sb.append(" } , ");
								have = true;
							}
							if (have)
								sb.replace(sb.lastIndexOf(","), sb.length(), "");
							sb.append("]}");
						}
					}

					PrintWriter out = null;
					out = resp.getWriter();
					out.write(sb.toString());
					out.flush();
					out.close();
				}
			}
		}
	}
}