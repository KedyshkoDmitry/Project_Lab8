package servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ChatMessage;
import entity.ChatUser;

@WebServlet(name = "NewMessageServlet")
public class NewMessageServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;

    String[] words = new String[]{"java", "html", "php"};
    // добавим в список ряд элементов
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // По умолчанию используется кодировка ISO-8859. Так как мы
// передаѐм данные в кодировке UTF-8
// то необходимо установить соответствующую кодировку HTTP-запроса
        request.setCharacterEncoding("UTF-8");
        // Извлечь из HTTP-запроса параметр 'message'
        String message = (String)request.getParameter("message");
        // Если сообщение не пустое, то
        if (message!=null && !"".equals(message)) {
            // По имени из сессии получить ссылку на объект ChatUser
            ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));
            for(int i=0;i<words.length;i++) {
                message = message.replaceAll(words[i],"BIP");
            }
            synchronized (messages) {
                // Добавить в список сообщений новое
                messages.add(new ChatMessage(message, author, Calendar.getInstance().getTimeInMillis()));
            }
        }
        // Перенаправить пользователя на страницу с формой сообщения
        response.sendRedirect("/lab8/message.html");
    }
}
