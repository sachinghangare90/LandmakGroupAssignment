package stepdef.api;


import framework.BaseTest;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ApiStepDef extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ApiStepDef.class);
    RequestSpecification req_spec = null;
    List<Integer> ids= new ArrayList<>();
    List<String> names= new ArrayList<>();
    List<String> arr= new ArrayList<>();
    List<String> coinsName = new ArrayList<>();

    @Given("^Add Coins details are here$")
    public void add_Coins_details_are_here(List<String> data) {
        logger.info("You are Api Step Definition");
        arr.addAll(data);
    }

    @When("Retrieve the ID of bitcoin\\(BTC), usd tether\\(USDT), and Ethereum\\(ETH), using the {string} call")
    public void retrieve_the_ID_of_bitcoin_BTC_usd_tether_USDT_and_Ethereum_ETH_using_the_call(String endPoint) {
        req_spec = given().relaxedHTTPSValidation().baseUri(getConfigMAP().get("api_url")+endPoint).header("X-CMC_PRO_API_KEY",getConfigMAP().get("api_key")).contentType("application/json");
        String rep = req_spec.when().get().asString();
        Assert.assertEquals(req_spec.get().statusCode(),200);
        JsonPath js = new JsonPath(rep);
        int count = js.getInt("data.size()");
        for (int i = 0; i < count; i++) {
            for (String s : arr) {
                if (js.getString("data[" + i + "].name").equalsIgnoreCase(s)) {
                    ids.add(js.getInt("data[" + i + "].id"));
                    break;
                }
            }
        }
    }

    @Then("Once you have retrieved the IDs of these currencies, convert them to Bolivian Boliviano, using the {string} call")
    public void once_you_have_retrieved_the_IDs_of_these_currencies_convert_them_to_Bolivian_Boliviano_using_the_call(String endPoint) {
        for (Integer id : ids) {
            req_spec = given().relaxedHTTPSValidation().baseUri(getConfigMAP().get("api_url") + endPoint).header("X-CMC_PRO_API_KEY", getConfigMAP().get("api_key")).contentType("application/json")
                    .queryParam("id", id).queryParam("amount", "1").queryParam("convert", "BOB");
            Assert.assertEquals(req_spec.get().statusCode(),200);
            JsonPath js = new JsonPath(req_spec.when().get().asString());
            logger.info(js.getString("data.quote.BOB.price"));
        }
    }

    @When("Retrieve the Ethereum \\(ID {int}) technical documentation website from the {string} call")
    public void retrieve_the_Ethereum_ID_technical_documentation_website_from_the_call(Integer id, String endPoint) {
        req_spec = given().relaxedHTTPSValidation().baseUri(getConfigMAP().get("api_url") + endPoint).header("X-CMC_PRO_API_KEY", getConfigMAP().get("api_key")).contentType("application/json")
                .queryParam("id", id);
        Assert.assertEquals(req_spec.get().statusCode(),200);
    }

    @Then("Confirm that the following logo URL is present: {string}")
    public void confirm_that_the_following_logo_URL_is_present(String logoUrl) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertEquals(js.getString("data.1027.logo").trim(),logoUrl);
    }

    @Then("Confirm that the technical_doc URl is present: {string}")
    public void confirm_that_the_technical_doc_URl_is_present(String technicalDocUrl) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertEquals(js.getString("data.1027.urls.technical_doc").trim(),technicalDocUrl);
    }

    @Then("Confirm that the symbol of the currency is ETH: {string}")
    public void confirm_that_the_symbol_of_the_currency_is_ETH(String symbol) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertEquals(js.getString("data.1027.symbol").trim(),symbol);

    }

    @Then("Confirm that the date added is: {string}")
    public void confirm_that_the_date_added_is(String date) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertEquals(js.getString("data.1027.date_added").trim(),date);
    }

    @And("Confirm that the platform is null: {string}")
    public void confirmThatThePlatformIsNull(String platform) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertNull(js.getString("data.1027.platform"));
    }

    @Then("Confirm that the currency has the mineable tag associated with it: {string}")
    public void confirm_that_the_currency_has_the_mineable_tag_associated_with_it(String string) {
        JsonPath js = new JsonPath(req_spec.when().get().asString());
        Assert.assertEquals(js.getString("data.1027.tags.get(0)").trim(),string);
    }

    @Given("Add Coins details are here {int} {string}")
    public void add_Coins_details_are_here(Integer num, String string) {
        for(int i =1;i<=num;i++)
        {
            req_spec = given().relaxedHTTPSValidation().baseUri(getConfigMAP().get("api_url") + string).header("X-CMC_PRO_API_KEY", getConfigMAP().get("api_key")).contentType("application/json")
                    .queryParam("id", i);
            Assert.assertEquals(req_spec.get().statusCode(),200);
            JsonPath js = new JsonPath(req_spec.when().get().asString());
            names.add(js.getString("data."+i+".name"));
            ids.add(js.getInt("data."+i+".id"));
        }

    }

    @When("Check which currencies have the mineable tag associated with them {string}")
    public void check_which_currencies_have_the_mineable_tag_associated_with_them(String string) {
        for(int i =1;i<=ids.size();i++)
        {
            req_spec = given().relaxedHTTPSValidation().baseUri(getConfigMAP().get("api_url") + string).header("X-CMC_PRO_API_KEY", getConfigMAP().get("api_key"))
                    .contentType("application/json").accept("application/json").accept("deflate, gzip").queryParam("id", i);
            Assert.assertEquals(req_spec.get().statusCode(),200);
            JsonPath js = new JsonPath(req_spec.when().get().asString());
            Assert.assertEquals(js.getString("data."+i+".tags.get(0)").trim(), "mineable");
            waitForMilliSeconds(5000);
        }
    }

    @Then("^Verify that the correct cryptocurrencies have been printed out$")
    public void verify_that_the_correct_cryptocurrencies_have_been_printed_out(List<String> data) {
        coinsName.addAll(data);
        Assert.assertEquals(coinsName,names);
    }



}
