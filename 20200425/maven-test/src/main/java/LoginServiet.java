import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServiet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码及响应编码,响应的数据类型
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        //加入请求数据为username=xxx & password=xxx

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //req.getServletPath()获取uri的路径：/login
        //req.getContextPath()获取项目的部署名；/test
        System.out.println("servletpath:"+req.getServletPath()+",contextpath"+req.getContextPath());
        PrintWriter pw=resp.getWriter();
        //正确：重定向  错误：转发
        if("abc".equals(username) && "123".equals(password)){
            //pw.println("<p>登陆成功</p>");
            //req.getSession(boolean create):请求对象获取一个session对象，如果获取不到，根据create决定是否创建
            //默认创建，create=true，创建一个session对象，create=false，不创建，返回null
            HttpSession session=req.getSession();
            session.setAttribute("user","uesrname="+username);
            resp.sendRedirect(req.getContextPath()+"/main.html");//重定向
        }else {
            req.getRequestDispatcher("/error.html").forward(req,resp);//转发
            //pw.println("<p>用户名密码错误，登录不成功</p>");
        }
        pw.flush();
    }
}
