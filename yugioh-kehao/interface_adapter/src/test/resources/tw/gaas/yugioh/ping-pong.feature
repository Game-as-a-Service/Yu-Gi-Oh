Feature: Ping Pong API

  Scenario: Ping the API
    Given there is a ping API
    When I ping the ping API
    Then I receive a pong response
