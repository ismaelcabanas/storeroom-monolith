Feature: As a storeroom owner user,
  I want to create a storeroom,
  In order to store my products

  Scenario: The user create a new storeroom

    Given a storeroom owner user
    When he creates the storeroom Kitchen Storeroom
    Then the storeroom is created