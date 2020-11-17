Feature: As a storeroom owner user,
  I want to add a product to storeroom,
  In order to have available products for cooking and eating

  Scenario: The user adds stock of a new product

    Given a storeroom owner user
    And he has a storeroom
    When he adds 6 Botellas de leche to his storeroom
    Then he has 6 Botellas de leche in his storeroom

  Scenario: The user adds stock for an existent product

    Given a storeroom owner user
    And he has a storeroom
    And his storeroom has 3 Botellas de leche
    When he adds 4 Botellas de leche to his storeroom
    Then he has 7 Botellas de leche in his storeroom