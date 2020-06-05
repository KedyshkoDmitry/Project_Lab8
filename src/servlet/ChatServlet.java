package servlet;

import entity.ChatMessage;
import entity.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    // Карта текущих пользователей
    protected HashMap<String, ChatUser> activeUsers;
    // Список сообщений чата
    protected ArrayList<ChatMessage> messages;

    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        // Вызвать унаследованную от HttpServlet версию init()
        super.init();
        // Извлечь из контекста карту пользователей и список сообщений
        activeUsers = (HashMap<String, ChatUser>) getServletContext().getAttribute("activeUsers");
        messages = (ArrayList<ChatMessage>) getServletContext().getAttribute("messages");
        // Если карта пользователей не определена
        if (activeUsers==null) {
            // Создать новую карту
// Поместить еѐ в контекст сервлета,
// чтобы другие сервлеты могли до него добраться
            activeUsers = new HashMap<String, ChatUser>();
            getServletContext().setAttribute("activeUsers", activeUsers);
        }
        if (messages==null) {
            messages = new ArrayList<ChatMessage>(100);
            getServletContext().setAttribute("messages", messages);
        }
    }
}
