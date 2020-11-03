Feature: Unpublish a job offer

  As a manager,
  I want to unpublish a job offer,
  In order to candidates don't apply to the job offer.

  Scenario: Unpublish job offers

    Given a manager
    And there is a published job offer
    When he unpublishes the job offer
    Then the job offer is stored as unpublished
