Feature: Adding products to catalog

  As a admin user,
  I want to add a product to the catalog,
  In order to select it for my storeroom

  Scenario: The user adds a product with his details

    Given a admin user
    And he wants the product Botella de leche
    When he adds this product to catalog
    Then the product is stored within
