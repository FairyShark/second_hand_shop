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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(description = "µÇÂ¼¹ýÂËÆ÷", filterName = "loginFilter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "loginPage", value = "login.jsp")})
public class LoginFilter implements Filter {

    private FilterConfig config;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String loginPage = config.getInitParameter("loginPage");
        HttpSession session = ((HttpServletRequest) request).getSession();
        String requestPath = ((HttpServletRequest) request).getServletPath();
        if (session.getAttribute("uname") == null
                && (requestPath.endsWith("alreadyBuy.jsp") || requestPath.endsWith("shoppingCart.jsp")
                || requestPath.endsWith("addGoods.jsp") || requestPath.endsWith("addPhoto.jsp")
                || requestPath.endsWith("saleGoods.jsp") || requestPath.endsWith("alreadySale.jsp")
                || requestPath.endsWith("showMessage.jsp")
                || requestPath.endsWith("adminGoods.jsp") || requestPath.endsWith("adminUser.jsp")
                || requestPath.endsWith("adminOpe.jsp") || requestPath.endsWith("adminLog.jsp")
                || requestPath.endsWith("adminVis.jsp") || requestPath.endsWith("collectionGoods.jsp"))) {
            request.getRequestDispatcher(loginPage).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}