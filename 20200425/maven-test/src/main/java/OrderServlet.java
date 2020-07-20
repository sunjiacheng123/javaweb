import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码及响应编码,响应的数据类型
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        //

        //用户身份校验：登陆成功就可以获取到已有的session对象
        //如果没有登录，获取不到，create=false，也不创建
        HttpSession session=req.getSession(false);
        if(session==null){//登录过就不为null，重定向到登录界面
            resp.sendRedirect(req.getContextPath()+"/index.html");
        }else{
            PrintWriter pw=resp.getWriter();
            pw.println("<h2>已经登录，可以访问订单<h2>");
            pw.flush();
        }
    }
}
