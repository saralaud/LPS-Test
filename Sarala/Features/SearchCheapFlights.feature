#Author: saralaud@gmail.com
Feature: Search for the Cheapest Fare
  As a customer of flight center
  I want to search for the cheapest fare for the onward trip(domestic only)
  And the return trip
  So that I should be able to select the cheapest fares for both and get a total for the round trip.

  @FunctionalTest    
    Scenario: Customer Search the Cheapest Fare for domestic flights
    Given User on the Flight Center home page
    When User click on the Flight link on the menus
    Then User click on the Domestic flights link
    And User should navigated to booking page and verify the page text 
    And User entered Wellington from Depature field and select the dropdown value
    And User entered Auckland from Destination field and select the dropdown value
    And select the depature date from calender
    And select the retuning date from calender
    And select the no of passanger as 2 adults
    Then click on the "FIND DEALS" button
    Then verify the result page loaded and fares sorted in descending order
    
   @FunctionalTest 
    Scenario: Book the cheapest flight ticket for onward trip
    When customer select the cheapest fare for onward trip on the result page
    Then customer click on faretype and navigated to return flights booking page
    And Verify the Select Return Flight text on the result page
    
   @FunctionalTest
    Scenario: Customer book the cheapest return flight ticket
    When customer select the cheapest return flight on booking page
    And click on the returning Fare Type and verify the result page 
    
    @FunctionalTest
    Scenario: Response time
    Given verify the response time
    
    @FunctionalTest
    Scenario: Verify the total price for onward and return tickets based on the no of passaengers
     Given Get the price for onward and return tickets and validate the total outcome price
    
    @FunctionalTest
    Scenario: session expiree message
    Given verify the proper message for session expire
  
   
    
     
  