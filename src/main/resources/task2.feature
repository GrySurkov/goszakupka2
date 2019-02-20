@goszacupka
Feature: A description

  Scenario: A scenario
    Given i am on "http://www.zakupki.gov.ru" page
    Then i see "Организации" and click them
    Then i see "Реестр зарегистрированных организаций" and click them
    Then i found element by cssSelector "#setParametersLink > a" and click them
    Then i found element by id "searchString" and enter "институт"
    Then i check element by id "fz94" and "select"
    Then i check element by id "fz223" and "unselect"
    Then i check element by id "withBlocked" and "unselect"
    Then i check element by id "svrDivision" and "select"
    Then i found element by cssSelector "#organizationLevelTag > div > div.collapsed.height30 > span.msPlaceholder" and click them
    Then i get list of elements by cssSelector "#organizationLevelTag > div > div.selectChose > div.expanded > ul" and choose elements "федеральный уровень"
    Then i found element by cssSelector "#organizationLevelTagSelectBtn > span" and click them
   # Then i found element by cssSelector "#searchButtonsBlock > div > span.bigOrangeBtn.margLeft20" and click them
    Then i entering data in fields and click button "#searchButtonsBlock > div > span.bigOrangeBtn.margLeft20"
    Then i get result and write there in file


