package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(description = "×Ö·û±àÂë¹ýÂËÆ÷", filterName = "encodingFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "ENCODING", value = "UTF-8") })
public class EncodingFilter implements Filter {

	private String encoding = "";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("ENCODING");
		if (encoding == null || "".equals(encoding)) {
			encoding = "utf-8";
		}
	}

}
