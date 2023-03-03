import javax.crypto.NoSuchPaddingException;


public class Main {
    public static void main(String[] args) {
      System.out.println("Hello world!");
      var e = new NoSuchPaddingException();
      System.out.println(e.toString());
    }
}