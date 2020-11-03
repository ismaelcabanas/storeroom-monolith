Feature: Activate a job offer

  As a manager,
  I want to activate a unpublished job offer,
  In order to come back to find good candidates for the job.

  Scenario: Activate unpublished job offers

    Given a manager
    And there is an unpublished job offer
    When he activates the job offer
    Then the job offer is stored as published

  Scenario: Cannot activate published job offers

    Given a manager
    And there is a published job offer
    When he activates the job offer
    Then he is notified the job offer cannot be activated

  Scenario: Cannot activate expired job offers

    Given a manager
    And there is a expired job offer
    When he activates the job offer
    Then he is notified the job offer cannot be activated
