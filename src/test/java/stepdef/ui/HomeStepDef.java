package stepdef.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BaseTest;
import org.junit.Assert;
import utility.PageActionLibrary;
import java.util.ArrayList;
import java.util.List;

import static locators.Web.homePage.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


public class HomeStepDef extends BaseTest {

    List<String> coinsName = new ArrayList<>();
    List<String> rankingCoinsName = new ArrayList<>();

    @Given("Open Browser and navigate to")
    public void open_Browser_and_navigate_to() {
        getDriver();
        waitForMilliSeconds(10000);
    }

    @Given("Open Browser and navigate to website and perform login")
    public void open_Browser_and_navigate_to_website_and_perform_login() {
        getDriver();
        PageActionLibrary.clickElement(LOGIN_BUTTON);
        PageActionLibrary.sendText(LOGIN_EMAIL_ADDRESS_TEXT_BOX, getConfigMAP().get("login_email_address"));
        PageActionLibrary.sendText(LOGIN_EMAIL_PASSWORD_TEXT_BOX, getConfigMAP().get("login_password"));
        PageActionLibrary.clickElement(LOG_IN_BUTTON);
        waitForMilliSeconds(10000);
    }


    @When("Click on View All Button {string}")
    public void clickOnViewAllButton(String viewCount) {
        PageActionLibrary.clickElement(SHOW_ROWS_OPTION1);
        PageActionLibrary.clickElement(SHOW_ROWS_OPTION.replace("view_option",viewCount));
        waitForMilliSeconds(3000);
    }

    @Then("Verify that hundred result display {int}")
    public void verify_that_hundred_result_display(int viewCount) {
       PageActionLibrary.handledSmartLoad();
        waitForMilliSeconds(3000);
        Assert.assertEquals(PageActionLibrary.getElementNameList(COINS_VIEW_LIST_NAMES).size(),viewCount);
    }

    @When("^Select random five cryptocurrencies Add to Watchlist$")
    public void select_random_five_cryptocurrencies_Add_to_Watchlist(List<String> data) {
        PageActionLibrary.scrollToThePoint(450);
        for (String datum : data) {
            PageActionLibrary.clickElement(COINS_STAR_ICON_ADD_WATCH_LIST.replace("coin_name", datum));
        }
    }

    @And("Open the watchlist in a different browser tab")
    public void open_the_watchlist_in_a_different_browser_tab() {
        PageActionLibrary.openLinkInNewTab(WATCH_LIST_TAB_LINK);
        PageActionLibrary.SwitchToNewTab();
    }

    @And("Click on the Watchlist tab")
    public void click_on_the_Watchlist_tab() {
        PageActionLibrary.clickElement(WATCH_LIST_TAB_LINK);
    }

    @Then("^verify all the options you selected are added to the watchlist$")
    public void verify_all_the_options_you_selected_are_added_to_the_watchlist(List<String> data) {
        coinsName.addAll(data);
        Assert.assertTrue(PageActionLibrary.getElementNameList(WATCH_LIST_ADDED_COINS_NAME).containsAll(coinsName));
    }

    @When("Display the dropdown menu on the Cryptocurrencies tab {string}")
    public void display_the_dropdown_menu_on_the_Cryptocurrencies_tab(String tabOption) {
        PageActionLibrary.performMouseOverOnMainMenu(FILTER_TAB_NAME.replace("tab_name",tabOption));
    }

    @And("Click any of the {string} options on this menu")
    public void clickAnyOfTheOptionsOnThisMenu(String ranking){
        PageActionLibrary.performMouseOverAndClickOnSUbMenu(CRYPTOCURRENCY_SUBMENU_OPTION.replace("submenu_option",ranking));
    }

    @And("Record the data on the current page")
    public void record_the_data_on_the_current_page() {
        PageActionLibrary.handledSmartLoad();
        waitForMilliSeconds(5000);
        System.out.println(PageActionLibrary.getElementNameList(COINS_VIEW_LIST_NAMES));
        rankingCoinsName.addAll(PageActionLibrary.getElementNameList(COINS_VIEW_LIST_NAMES));
    }

    @And("Apply any combination of filters, displayed in the three dropdown menus above the tabs {string} {string}")
    public void apply_any_combination_of_filters_displayed_in_the_three_dropdown_menus_above_the_tabs(String tabOption,String filterName) {
        PageActionLibrary.performMouseOverOnMainMenu(FILTER_TAB_NAME.replace("tab_name",tabOption));
        PageActionLibrary.performMouseOverAndClickOnSUbMenu(CRYPTOCURRENCY_SUBMENU_OPTION.replace("submenu_option",filterName));
    }

    @Then("Check against the data recorded in Step four")
    public void checkAgainstTheDataRecordedInStepFour() {
        PageActionLibrary.handledSmartLoad();
        List<String> stockCoinsName =  PageActionLibrary.getElementNameList(COINS_VIEW_LIST_NAMES);
        System.out.println(stockCoinsName);
        System.out.println(rankingCoinsName);
        assertThat("Compare Ranking filter data with Stock filter",
                stockCoinsName, containsInAnyOrder(rankingCoinsName.toArray()));
    }



}
