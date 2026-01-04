package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line]
        // 그냥 200 넣는것보다는 HttpServletResponse 활용하기
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type","text/plain; charset=utf-8");
        response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
        //content(response);
        cookie(response);
        redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2);  생략 시 자동 생성
    }

    private void cookie(HttpServletResponse response) {

        // = response.setHeader("Set-Cookie, "myCookie=good; Max-age=600"); 와 같음
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600); // 600초

        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // 302 반환하면서 /basic/hello-form.html로 리다이렉트

        // 방법 1 (직접 세팅)
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location", "/basic/hello-form.html");

        // 방법 2 (편의 메서드)
        response.sendRedirect("/basic/hello-form.html");
    }
}
