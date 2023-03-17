Feature: Verify that the painting exists on the search results page

  Scenario: Verify that the painting exists on the search results page using Google Chrome
    Given I am on the ArtNow homepage
    When I navigate to the Embroidered Paintings page
    And I select the "Landscapes" genre
    And I search for paintings
    Then I should see "Трамвайный путь. Гвоздецкая Татьяна" in the search results

  Scenario: Verify that the painting exists on the search results page using Mozilla Firefox
    Given I am on the ArtNow homepage
    When I navigate to the Embroidered Paintings page
    And I select the "Landscapes" genre
    And I search for paintings
    Then I should see "Трамвайный путь. Гвоздецкая Татьяна" in the search results