package cn.com.news.utils;

import cn.com.news.model.ArticleType;
import cn.com.news.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Const {
	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	// 当前登录用户
	public static User USER_CURRENT = null;
	// 当前用户存储标签
	public static final String CURRENT_USER = "currentUser";
	// 
	public static final String MESSAGE = "message";
	// 操作参数传递标签
	public static final String REQUEST_OP_PARAMS = "op";
	// 文章类型标签
	public static final String REQUEST_OP_ARTICLE_TYPE = "articleType";
	// 用户注册标签
	public static final String REQUEST_OP_PARAMS_REGISTER = "register";
	// 用户登录标签
	public static final String REQUEST_OP_PARAMS_LOGIN = "login";
	// 文章录入标签
	public static final String REQUEST_OP_PARAMS_WRITE = "write";
	// 用户信息界面标签
	public static final String REQUEST_OP_PARAMS_INFO = "myInfo";
	// 文章详细信息查看标签
	public static final String REQUEST_OP_PARAMS_ARTICLE_INFO = "articleInfo";
	// 保存文章标签
	public static final String REQUEST_OP_PARAMS_WRITE_UPLOAD = "writeUpload";
	// 用户权限控制标签
	public static final String REQUEST_OP_PARAMS_PERMISSION = "userPermission";
	// 管理员审核标签
	public static final String REQUEST_OP_PARAMS_ARTICLE_CHECK = "articleCheck";
	// 文章删除标签
	public static final String REQUEST_OP_PARAMS_ARTICLE_DELETE = "articleDelete";
	//  文章批量查询标签
	public static final String REQUEST_OP_PARAMS_ARTICLE_LIST = "articleList";
	// 文章详细查询标签
	public static final String REQUEST_OP_PARAMS_ARTICLE_DETAIL = "articleDetail";
	// 评论查询标签
	public static final String REQUEST_OP_PARAMS_SEND_CONTENT = "sendContent";
	// 用户类型  0 超级用户  1 新闻创作者 3 普通用户
	public static final int USER_LOGIN_TYPE_ROOT = 0;
	public static final int USER_LOGIN_TYPE_AUTHOR = 1;
	public static final int USER_LOGIN_TYPE_COMMON = 3;
	// 是否核查 0 未核查 1 通核查  99 未通过核查
	public static final int ARTICLE_ISCHECKED_FALSE = 0;
	public static final int ARTICLE_ISCHECKED_TRUE = 1;
	public static final int ARTICLE_ISCHECKED_TRUE_BAD = 99;
	
	/***
	 * 日期转化
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return sdf.format(date);
	}
	/***
	 * 日期转化
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String date) throws ParseException {
		return sdf.parse(date);
	}
	/***
	 * 文章类型转化
	 * @param type
	 * @return
	 */
	public static ArticleType getArticleType(String type) {
		type = type.split(" ")[0];
		if (ArticleType.Amusement.name().toUpperCase().equals(type))
			return ArticleType.Amusement;
		if (ArticleType.Blog.name().toUpperCase().equals(type))
			return ArticleType.Blog;
		if (ArticleType.Car.name().toUpperCase().equals(type))
			return ArticleType.Car;
		if (ArticleType.Economics.name().toUpperCase().equals(type))
			return ArticleType.Economics;
		if (ArticleType.Health.name().toUpperCase().equals(type))
			return ArticleType.Health;
		if (ArticleType.Image.name().toUpperCase().equals(type))
			return ArticleType.Image;
		if (ArticleType.News.name().toUpperCase().equals(type))
			return ArticleType.News;
		if (ArticleType.Polity.name().toUpperCase().equals(type))
			return ArticleType.Polity;
		if (ArticleType.Sport.name().toUpperCase().equals(type))
			return ArticleType.Sport;
		if (ArticleType.Technology.name().toUpperCase().equals(type))
			return ArticleType.Technology;
		return ArticleType.None;
	}
}