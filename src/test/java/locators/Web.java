package locators;

public class Web {
    public static class homePage {
        public static final String LOGIN_BUTTON = "//button[text()='Log In']~xpath";
        public static final String LOGIN_EMAIL_ADDRESS_TEXT_BOX = "//input[@placeholder='Enter your email address...']~xpath";
        public static final String LOGIN_EMAIL_PASSWORD_TEXT_BOX = "//input[@placeholder='Enter your password...']~xpath";
        public static final String LOG_IN_BUTTON = "(//button[text()='Log In'])[2]~xpath";
        public static final String SHOW_ROWS_OPTION1="//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK'][normalize-space()='100']~xpath";
        public static final String SHOW_ROWS_OPTION ="//button[normalize-space()='view_option']~xpath";
        public static final String COINS_VIEW_LIST_NAMES ="//td//div[contains(@class,'dNOTPP')]/p~xpath";
        public static final String COINS_STAR_ICON_ADD_WATCH_LIST ="//p[normalize-space()='coin_name']/ancestor::tr/td//span[@class='icon-Star']~xpath";
        public static final String WATCH_LIST_TAB_LINK ="//a[@href='/watchlist/']~xpath";
        public static final String WATCH_LIST_ADDED_COINS_NAME = "//span[@class='icon-Star-Filled']/ancestor::tr//td//div[contains(@class,'dNOTPP')]/p~xpath";
        public static final String CRYPTOCURRENCY_SUBMENU_OPTION = "//div[contains(@class,'submenu')]/a[normalize-space()='submenu_option']~xpath";
        public static final String FILTER_TAB_NAME = "//a[normalize-space()='tab_name']~xpath";
    }
}
