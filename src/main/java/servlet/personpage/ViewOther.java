package servlet.personpage;

import been.Message;
import been.User;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.impl.UserServiceimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/attention")
public class ViewOther extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();
//        String otherId= (String) session.getAttribute("OtherId");
        String otherId = request.getParameter("id");
        System.out.println(otherId);
        User user= (User) session.getAttribute("user");
        int OId= Integer.parseInt(otherId);
        boolean flag=true;
        if(user!=null){
             flag=userdao.select_solo_fans(user,OId);
        }
        User user1=new User();
        user1.setId(OId);
        user1=userdao.select_all(user1);
        user1.setProfile(userdao.select_profile(user1));
        user1.setMessageList(new Messagedaoimpl().select(user1));
        if(flag){
            user1.setStatus(1);
            JSONObject jsonObject=new JSONObject(user1);
            writer.write(jsonObject.toString());
        }else {
            user1.setStatus(0);
            JSONObject jsonObject=new JSONObject(user1);
            writer.write(jsonObject.toString());
        }
    }
}
