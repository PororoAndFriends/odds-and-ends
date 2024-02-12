package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("파라미터 전체 조회");
        Iterator<String> paramIter = req.getParameterNames().asIterator();
        paramIter.forEachRemaining(value -> System.out.println(value + " : " + req.getParameter(value)));

        System.out.println("----------------");
        System.out.println("파라미터 단일 조회");
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("username = " + username);
        System.out.println("age = " + age);


        System.out.println("----------------");
        System.out.println("복수 파라미터 조회");
        String[] usernames = req.getParameterValues("username");

        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        resp.getWriter().write("ok");
    }
}
