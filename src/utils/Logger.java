package utils;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Logger class is used to capture login information from the login attempts
 *
 */
public class Logger {


    /**
     * Logger method is used to create the login activity file if it does not exists
     * Login string append is used to pass the login date, username entered and result string and print it to the file
     * @param append
     * @throws IOException
     */
    public static void Logger(String append) throws IOException {

        String filename = "login_activity.txt";
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter loginFile = new PrintWriter(fwriter);
        loginFile.println(append);
        loginFile.close();

    }
}
