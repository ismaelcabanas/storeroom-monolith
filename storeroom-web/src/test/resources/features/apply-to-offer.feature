Feature: Candidate applies to job offer

  As a candidate,
  I want to apply to a job offer,
  In order to get a job.

  Scenario: Candidate apply to the job offer

    Given a candidate
    And there is a published job offer
    When he applies to the job offer
    Then a new inscription is stored

  Scenario: Candidate cannot apply to the same offer

    Given a candidate
    And there is a published job offer
    And he already applied to the job offer
    When he applies to the job offer
    Then he is notified he already applied to the job offer

  Scenario: Candidate cannot apply to unpublished job offer

    Given a candidate
    And there is a unpublished job offer
    When he applies to the job offer
    Then he is notified he cannot apply because the job offer is unpublished

  Scenario: Candidate cannot apply to expired job offer

    Given a candidate
    And there is a expired job offer
    When he applies to the job offer
    Then he is notified he cannot apply because the job offer is expired

  Scenario: Candidate cannot apply to drafted job offer

    Given a candidate
    And there is a drafted job offer
    When he applies to the job offer
    Then he is notified he cannot apply because the job offer is drafted
