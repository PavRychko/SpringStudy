package lab2.practice;

@ReplaceWith(NewMessagePrinter.class)
public class MessagePrinter implements Printer {
    private String message;

    public void print() {
        System.out.println(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
