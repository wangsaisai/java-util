import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionToStringExample {
    public static void main(String[] args) {
        try {
            // 你的代码，可能会抛出异常
        } catch (Exception e) {
            String exceptionAsString = getStackTraceAsString(e);
            System.out.println(exceptionAsString);
        }
    }

    private static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
