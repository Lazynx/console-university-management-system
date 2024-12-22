package main.java.kbtu.chill_guys.university_management_system.model.employee;

import java.time.LocalDateTime;

public class Message {
    private final Employee sender;
    private final Employee recipient; // исправлено на 'recipient'
    private final String content;
    private final LocalDateTime timestamp;

    public Message(Employee sender, Employee recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // Сохраняем текущее время
    }

    public Employee getSender() {
        return sender;
    }

    public Employee getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
