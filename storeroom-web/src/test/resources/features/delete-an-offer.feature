Feature: Delete a job offer

  As a manager,
  I want to delete a job offer,
  In order to delete unused offers.

  Scenario: Delete drafted job offers

    Given a manager
    And there is a drafted job offer
    When he deletes the job offer
    Then the job offer is deleted

  Scenario: Delete published job offers with inscriptions

    Given a manager
    And there is a published job offer with inscriptions
    When he deletes the job offer
    Then he is notified the job offer has inscriptions

  Scenario: Delete published job offers without inscriptions

    Given a manager
    And there is a published job offer without inscriptions
    When he deletes the job offer
    Then the job offer is deleted

  Scenario: Delete unpublished job offers with inscriptions

    Given a manager
    And there is a unpublished job offer with inscriptions
    When he deletes the job offer
    Then he is notified the job offer has inscriptions

  Scenario: Delete unpublished job offers without inscriptions

    Given a manager
    And there is a unpublished job offer without inscriptions
    When he deletes the job offer
    Then the job offer is deleted