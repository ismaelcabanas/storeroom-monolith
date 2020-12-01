Feature: As a storeroom owner user,
  I want to consume a product from my storeroom,
  In order to cook it or eat it

  Scenario: The user consume stock of an existent product

    Given a storeroom owner user
    And he has a storeroom
    And his storeroom has 6 Botellas de leche
    When he consumes 4 Botellas de leche from his storeroom
    Then he has 2 Botellas de leche in his storeroom

  Scenario: The user consumes more stock than existent product stock

    Given a storeroom owner user
    And he has a storeroom
    And his storeroom has 3 Botellas de leche
    When he consumes 4 Botellas de leche from his storeroom
    Then he is notified the product cannot be consumed
