Feature: Publish a job offer

  As a manager,
  I want to publish a job offer,
  In order to find good candidates for the job.

  Scenario: Publish drafted job offers

    Given a manager
    And there is a drafted job offer
    When he publishes the job offer
    Then the job offer is stored as published

  Scenario: Cannot publish published job offers

    Given a manager
    And there is a published job offer
    When he publishes the job offer
    Then he is notified the job offer cannot be published

  Scenario: Cannot publish expired job offers

    Given a manager
    And there is a expired job offer
    When he publishes the job offer
    Then he is notified the job offer cannot be published
