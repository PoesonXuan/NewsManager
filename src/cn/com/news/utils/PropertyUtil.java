package cn.com.news.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
/***
 * 配置信息工具类
 * @author XuanZP
 *
 */
public class PropertyUtil {
	private static Properties props;

	static {
		loadProps();
	}

	private static synchronized void loadProps() {
		System.out.print("开始加载properties文件内容.......");
		props = new Properties();
		InputStream in = null;
		try {
			in = PropertyUtil.class.getClassLoader().getResourceAsStream(
					"jdbc.properties");

			props.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("加载properties文件内容完成...........");
		System.out.println("properties文件内容：" + props);
	}

	public static String getProperty(String key) {
		if (props == null) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (props == null) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
}