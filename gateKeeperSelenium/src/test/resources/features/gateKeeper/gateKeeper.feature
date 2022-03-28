
Feature: GateKeeper


  @ControllingAllTitles
  Scenario: controlling All titles in page
    Given I expect element "text_mainTitle"  contains text "The goal of this assignment is to assess your testing skills in automation as well as your way of thinking. You should approach the problem in a critical manner and try to come up with a well-structured solution. It is required for your solution to be written with Selenium Java adapter, but use whatever additions you feel comfortable with or think is required for the task at hand."

  @ControlAdress
  Scenario: control adress areaThen I expect element conr
   Given I expect element "text_address"  contains text "Please extract and verify our address to the following:"

  @controlBobiAreaDisplayed
  Scenario: Controlling bobi
    When Write value "bobi" to element "searchArea"
    Then I expect is displayed check element "bobiDisplayed"

  @controlBobiAreaNotDisplayed
  Scenario: Controlling bobi
    Given Write value "cloudWise" to element "searchArea"
    Then I expect is not displayed check element "bobiDisplayed"

  @controlBobiAreaNotDisplayed
  Scenario: Controlling bobi search for numbers
    Given Write value "123123" to element "searchArea"
    Then I expect is not displayed check element "bobiDisplayed"

  @LetTheBattleBeginClickOrangeArrow
  Scenario: control clicking orange and expect pop up is closed
    When Click on the "button_letTheBattleBegin" element
    Given I expect is  displayed check element "icon_orangeArrow"
    When Click on the "icon_orangeArrow" element
    When "2000" wait milliseconds
    Then I expect is  not displayed check element "icon_orangeArrow"


  @LetTheBattleBeginClickGreyArrow
  Scenario: control clicking grey and expect pop up is not closed
    When Click on the "button_letTheBattleBegin" element
    Given I expect is  displayed check element "icon_orangeArrow"
    Then Click on the "icon_greyArrows" element
    Then I expect is  displayed check element "icon_orangeArrow"


  @websiteButtonControlAboutUs
  Scenario: controlling buttons on website and scroll
    When Open url "https://www.codeshake.io/"
    Then I expect is displayed check element "button_codeShakeAboutUs"
    Then I expect is displayed check element "button_codeShakeProducts"
    Given I expect is displayed check element "button_codeShakeVacancies"
    Then Scroll element "text_meetTeam"








