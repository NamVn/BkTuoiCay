package namvn.controller;


import namvn.model.Admin;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import namvn.repository.TaiKhoanDao;
import namvn.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class AuthorizationFilter implements Filter {

    private static final int INVALID_ROLE = -1;
    private static final int USER_ROLE = 0;
    private static final int ADMIN_ROLE = 1;

    @Autowired
    private TaiKhoanDao userRepository;

    @Autowired
    private AdminDao mAdministratorRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // CORS site
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Origin, Content-Type, Accept, au-token");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chainFilter(req, res, chain);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().close();
            return;
        }

        String requestURI = request.getRequestURI().toLowerCase();

        // Filter API by permissions
        // Non-require permission
        if (requestURI.contains("/account/login") || requestURI.contains("/admin/login") || requestURI.contains("/acoount/register")) {
            chainFilter(req, res, chain);
        } else {

            // Check permission

            int role = getUserRole(request);

            String c = request.getHeader("au-token");
            Utils utils = new Utils();
            // String username=utils.decodeToken(c);
            TaiKhoan user = userRepository.findByToken(c);
            Admin admin = mAdministratorRepository.findByToken(c);
            if (user != null) {
                role = USER_ROLE;
            } else if (admin != null) {
                role = ADMIN_ROLE;
            }
            if (role != INVALID_ROLE) {

                if (role == USER_ROLE) {
                    // TODO: Check if requestURI is in user's role APIs
                    if (requestURI.contains("/cayphien/nhap") || requestURI.contains("/cay/all") || requestURI.contains("/cay/nhucau") || requestURI.contains("/cay/duongdi") || requestURI.contains("/cay/trangthai") ||
                            requestURI.contains("/cay/list") || requestURI.startsWith("/congviec/see") || requestURI.contains("/phanhoi/send")||requestURI.contains("/voi/all")
                            ||requestURI.contains("/thongbao/get")||requestURI.contains("/thongbao/update")
                            ||requestURI.contains("voiphien/nhap")) {
                        chainFilter(req, res, chain);
                    } else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().append("{\"error\" : \"401\"}");
                        // Flush response
                        response.getWriter().flush();
                    }
                } else if (role == ADMIN_ROLE) {
                    // TODO: Check if requestURI is in admin's role APIs
                    if (requestURI.contains("/account/register") || requestURI.contains("/account/delete") || requestURI.contains("/phanhoi/trangthai") || requestURI.startsWith("/phanhoi/detail")||requestURI.contains("/phanhoi/day")
                            ||requestURI.contains("/phanhoi/week")||requestURI.contains("/phanhoi/month")||requestURI.contains("/congviec/phancong")||requestURI.contains("/congviec/list")
                            ||requestURI.contains("/cayphien/nhap")||requestURI.contains("/cayphien/chamcong")||requestURI.contains("/cay/datuoi")||requestURI.contains("/cay/thieunuoc")
                            ||requestURI.contains("/voi/makes")||requestURI.contains("voi/delete")) {
                        chainFilter(req, res, chain);
                    } else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().append("{\"error\" : \"401\"}");
                        // Flush response
                        response.getWriter().flush();
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().append("{\"error\" : \"401\"}");
                    // Flush response
                    response.getWriter().flush();
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().append("{\"error\" : \"401\"}");
                // Flush response
                response.getWriter().flush();
            }
        }
    }


    private int getUserRole(HttpServletRequest request) {
        List<String> listHeaderParams = Collections.list(request.getHeaderNames());
        if (!listHeaderParams.contains("au-token")) {
            return INVALID_ROLE;
        }
        String strToken = request.getHeader("au-token");

        if (strToken == null || strToken.length() <= 0) {
            return INVALID_ROLE;
        }

        int role = INVALID_ROLE;
        // Get role here
        // TODO: get role based on strToken (When login successful, store strToken on user or admin table)

        return role;
    }

    private void chainFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        try {
            chain.doFilter(req, res);
        } catch (Exception e) {

        }
    }

    @Override
    public void destroy() {

    }
}


