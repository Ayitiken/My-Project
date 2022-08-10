@ApiTest
Feature: Post Put Delete requests Api Test For Trello

  Scenario Outline: User should be able to send post put delete requests
    Given given baseUri
    When user should be able to send post request for creating a board "<board name>"
    And user should be able to send post request for creating a list "<list name>"
    And user should be able to send post request for creating the first card "<card name 1>"
    And user should be able to send post request for creating the second card "<card name 2>"
    And user should be able to send put request for updating one of the card "<card name 3>"
    And user should be able to send delete request for deleting the first card
    And user should be able to send delete request for deleting the second card
    And user should be able to send delete request for deleting the board
    Then verify status code
    Examples:
      |board name  |list name     |card name 1   |card name 2    |card name 3    |
      |Simple Board|The first list|The first Card|The second Card|Update the Card|

