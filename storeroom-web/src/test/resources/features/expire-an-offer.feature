Feature: Expire job offers

  As a manager,
  I want the job offers expire automatically,
  In order to candidates don't join to the job offer.

  Scenario: Expire job offers published

    Given a manager
    And there is a published job offer with 60 days of validity
    When 60 days have passed after the job offer was published
    Then the job offer is stored as expired

  Scenario: Expire job offers unpublished

    Given a manager
    And there is a unpublished job offer with 60 days of validity
    When 60 days have passed after the job offer was published
    Then the job offer is stored as expired
