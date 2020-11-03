Feature: Create a draft job offer

  As a manager,
  I want to create a job offer before publish it,
  In order to redact it calmly.

  Scenario: The job offer is created

    Given a manager
    And he wants to create a job offer 2 vacancies for Java Developer
    When he saves the job offer
    Then the job offer is stored as draft
