package servlet.personpage;

import been.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/service/viewother")
public class TiaoZhuanID extends HttpServlet {
    @Override
    public void init(){

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        String other_id=request.getParameter("OtherId");
        System.out.println(other_id);
        session.setAttribute("OtherId",other_id);
    }
}
