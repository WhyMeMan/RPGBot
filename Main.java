import com.whymeman.rpgbot.src.threads.MainThread;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 */
public class Main
{
    private static MainThread mainThread;

    public static void main(String[] args)
    {
		mainThread = new MainThread();
		mainThread().start();
//        for (int i = 0; i < 3; i++)
//        {
//            mainThread = new MainThread();
//            mainThread.start();
//        }
    }
}