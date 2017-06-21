package javagame;

import java.io.File;
import java.security.SecureRandom;

public final class Const {

    // SecureRandom for create random object
    public final static SecureRandom RAND = new SecureRandom();

    // Root path 
    public final static String ROOT_PATH = new File("").getAbsolutePath();

    // Game name
    public final static String GAME_NAME = "نام بازی";
    public final static String GAME_ICON = "\\src\\resources\\Game\\Icon.jpg";

    // Line
    public final static String LINE_ROOT_IMAGE = "\\src\\resources\\Line\\MainLines";
    public final static String LINE_IMAGE = "\\src\\resources\\Line\\MainLines\\Line{0}.png";
    public final static int LINE_HEIGHT = 100;
    public final static int LINE_WIDTH = 87;
    public final static boolean LINE_DIRECTION_RTL = true;
    public final static boolean LINE_DIRECTION_LTR = false;

    // Middle Line
    public final static String MIDDLE_LINE_IMAGE = "\\src\\resources\\Line\\MiddleLine.png";
    public final static int MIDDLE_LINE_WIDTH = 50;
    public final static int MIDDLE_LINE_HEIGHT = 50;

    // Crosswalk
    public final static String CROSSWALK_IMAGE = "\\src\\resources\\Line\\Crosswalk.png";
    public final static int CROSSWALK_WIDTH = 100;
    public final static int CROSSWALK_HEIGHT = 20;

    // Car 
    public final static String CAR_ROOT_IMAGE = "\\src\\resources\\Cars";
    public final static int CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED = 110;
    public final static float CAR_SPEED_RATE_NEAR_CROSSWALK_CHANGE = .4f;
    public final static int CAR_COUNT = 4;

    // Cars Speed Rate
    public final static int CREATE_CAR_SPEED_RATE = 100;
    public final static int CREATE_CAR_SLEEP_TIME = 3000;
    public final static int CREATE_CAR_MAX_SLEEP_TIME = 200;

    //Setting File
    public final static String MAIN_SETTING_FILE = "\\src\\resources\\Setting\\Setting.xml";

    // Game size
    public final static int GAME_WINDOWS_WIDTH = 800;
    public final static int TOP_MARGIN = 40;

    // Repaint time
    public final static long SLEEP_TIME_RE_PAINTING = 30;

    // Sheep
    public final static String SHEEP_PATH_IMAGE = "\\src\\resources\\Sheep\\{0}.png";
    public final static String SHEEP_ROOT_IMAGE = "\\src\\resources\\Sheep";
    public final static int SHEEP_DISTANCE_LINE_WHEN_GAME_START = 30;
    public final static int SHEEP_IGONORE_DISTANCE_IN_CROSSWALK = 30;

    // Change speed near other car 
    public final static int CHANGE_SPEED_DISTANCE_FOR_REACH = 100;
    public final static float CHANGE_SPEED_RATE_DISTANCE_FOR_REACH = .5f;

    // Save and load
    public final static String SAVE_FILE_ADDRESS_LINE = "\\src\\resources\\Resume\\Line.txt";
    public final static String SAVE_FILE_ADDRESS_SHEEP = "\\src\\resources\\Resume\\Sheep.txt";
    public final static String SAVE_FILE_ADDRESS_CAR = "\\src\\resources\\Resume\\Cars.txt";
    public final static String SAVE_FILE_ADDRESS_SETTING = "\\src\\resources\\Resume\\Setting.xml";

    // Reply
    public final static String REPLY_ROOT_ADDRESS = "\\src\\resources\\Replies";
    public final static String REPLY_LINE_ADDRESS = "\\Line.txt";
    public final static String REPLY_CAR_ADDRESS = "\\Cars.txt";
    public final static String REPLY_SHEEP_ADDRESS = "\\Sheep.txt";
    public final static String REPLY_SETTING_ADDRESS = "\\Setting.xml";
    
    // Map
    public final static String MAP_ROOT_ADDRESS = "\\src\\resources\\Map";

}
