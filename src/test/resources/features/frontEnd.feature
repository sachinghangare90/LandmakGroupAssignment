

Feature: Frontend Task

  @Ui
    Scenario: Test 1
      Given Open Browser and navigate to
      When Click on View All Button "50"
      Then Verify that hundred result display 50

  @Ui1
    Scenario: Test 2
      Given Open Browser and navigate to website and perform login
      When Select random five cryptocurrencies Add to Watchlist
        | Bitcoin | Litecoin | Namecoin | Terracoin | Peercoin |
      And Open the watchlist in a different browser tab
      And Click on the Watchlist tab
      Then verify all the options you selected are added to the watchlist
        | Bitcoin | Litecoin | Namecoin | Terracoin | Peercoin |

  @Ui2
    Scenario: Test 3
      Given Open Browser and navigate to
      When Display the dropdown menu on the Cryptocurrencies tab "Cryptocurrencies"
      And Click any of the "Ranking" options on this menu
      And Record the data on the current page
      And Apply any combination of filters, displayed in the three dropdown menus above the tabs "Exchanges" "Spot"
      Then Check against the data recorded in Step four
