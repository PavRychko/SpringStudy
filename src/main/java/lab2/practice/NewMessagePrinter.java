package lab2.practice;



public class NewMessagePrinter extends MessagePrinter {
    private String message;

    @Override
    public void print() {
        System.out.println(message);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
