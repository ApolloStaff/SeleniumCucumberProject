Feature: Feature to automate buying on the website

  Scenario Outline: Login on the given website and buy the first item
    Given User is on the store website
    And User has logged into his account with his "<username>" and "<password>"
    When User adds multiple items available on the store page
    And Completes the purchase process
    Then User is shown the checkout complete page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | locked_out_user         | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

    # Ideal scenario
    # The user is locked from accessing the site
    # The last name field is bugged
    # The website experiences slow performance