@Api

Feature: API
    Scenario: Test 1
      Given Add Coins details are here
        | Bitcoin | Tether | Ethereum |
      When Retrieve the ID of bitcoin(BTC), usd tether(USDT), and Ethereum(ETH), using the "/cryptocurrency/map" call
      Then Once you have retrieved the IDs of these currencies, convert them to Bolivian Boliviano, using the "/tools/price-conversion" call

    Scenario: Test 2
      When Retrieve the Ethereum (ID 1027) technical documentation website from the "/cryptocurrency/info" call
      Then Confirm that the following logo URL is present: "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png"
      And Confirm that the technical_doc URl is present: "[https://github.com/ethereum/wiki/wiki/White-Paper]"
      And Confirm that the symbol of the currency is ETH: "ETH"
      And Confirm that the date added is: "2015-08-07T00:00:00.000Z"
      And Confirm that the platform is null: "null"
      And Confirm that the currency has the mineable tag associated with it: "mineable"

    Scenario: Test 3
      Given Add Coins details are here 10 "/cryptocurrency/info"
      When Check which currencies have the mineable tag associated with them "/cryptocurrency/info"
      Then Verify that the correct cryptocurrencies have been printed out
        | Bitcoin | Litecoin | Namecoin | Terracoin | Peercoin | Novacoin | Devcoin | Feathercoin | Mincoin | Freicoin |