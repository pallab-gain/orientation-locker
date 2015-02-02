package MyUtils;

/**
 * Created by xerxes on 1/31/15.
 * "I have not failed, I have just found 10000 ways that won't work."
 */
public class Constants {
    public interface ACTION{
        public static String MAIN_ACTION = "action_run_main";
        public static String BOOT_RECEIVE = "action_run_bootcomplete";
        public static String STARTFOREGROUND_ACTION = "action_run_start_forgraound";
        public static String STOPFOREGROUND_ACTION = "action_run_stop_forground";
        public static String SET_ORIENTATION = "action_run_set_orientation";
        public static String ON_SCREEN_ON = "action_run_on_screen_on";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 10235;
    }
}
