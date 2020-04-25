package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.web.role.RoleController;
import ru.choicerestaurant.web.user.UserAdminController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserAdminController adminController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        adminController = applicationContext.getBean(UserAdminController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
        request.setAttribute("users", adminController.getAll());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
