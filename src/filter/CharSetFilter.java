package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Servlet Filter implementation class CharSetFilter
 */
@WebFilter("/CharSetFilter")
public class CharSetFilter extends AbstractInterceptor implements Filter {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6150196516337263499L;

	/**
     * Default constructor. 
     */
    public CharSetFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		Logger logger = (Logger)LogManager.getLogger();
		logger.debug("字符过滤器启动");
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		chain.doFilter(request, response);
		logger.debug("字符过滤器链启动结束");
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = invocation.invoke();
		// TODO Auto-generated method stub
		return result;
	}

}
