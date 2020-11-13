Feature: As a admin user,
  I want to add a product to the catalog,
  In order to select it for my storeroom

  Scenario: The user adds a product with his details

    Given a admin user
    And he wants the product Botella de leche
    When he adds this product to catalog
    Then the product is stored within

  Scenario: The user adds a product which already exist

    Given a admin user
    And he added the product Botella de leche
    When he adds this product to catalog
    Then he is notified the product already exist
