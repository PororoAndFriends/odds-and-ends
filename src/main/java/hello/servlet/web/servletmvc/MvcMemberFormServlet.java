package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // webapp 의 WEB-INF 디렉토리 밑에 있는 파일들은 브라우저에서 url경로를 통해 바로 호출할 수 없도록 막아놓음
        // jsp와 같은 방법을 통해서만 호출할 수 있음
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // dispatcher를 통해 jsp에 request와 response를 보내줄 수 있음
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
