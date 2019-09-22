package cn.com.news.model;
/***
 * 文章类型
 * @author XuanZP
 *
 */
public enum ArticleType {
	None(0),

	News(1),

	Sport(2),

	Economics(3),

	Amusement(4),

	Technology(5),

	Blog(6),

	Image(7),

	Car(8),

	Polity(9),

	Health(10);

	private int index;

	private ArticleType(int index) {
		this.index = index;
	}

	public final int index() {
		return this.index;
	}

	public static ArticleType indexOf(int index) {
		ArticleType result = null;
		for (ArticleType member : values()) {
			if (member.index == index) {
				result = member;
				break;
			}
		}
		return result;
	}
}