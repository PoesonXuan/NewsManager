package cn.com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 过滤器用于设置传送信息编码格式
 * @author XuanZP
 *
 */
public class EncodingFilter
  implements Filter
{
  private String encoding = null;

  public void init(FilterConfig filterConfig)
    throws ServletException
  {
    this.encoding = filterConfig.getInitParameter("encode");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest httpRequest = (HttpServletRequest)request;
    HttpServletResponse httpResponse = (HttpServletResponse)response;

    httpRequest.setCharacterEncoding(this.encoding);
    httpResponse.setCharacterEncoding(this.encoding);

    chain.doFilter(httpRequest, httpResponse);
  }

  public void destroy()
  {
  }
}