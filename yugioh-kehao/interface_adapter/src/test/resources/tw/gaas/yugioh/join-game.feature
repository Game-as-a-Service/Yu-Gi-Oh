Feature: Join Game

  Scenario: A player joins a new game
    Given there is a player
    When a player looking for a new game to join
    Then a player has joined a game
