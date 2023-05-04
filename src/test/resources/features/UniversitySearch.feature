Feature: University search
  This feature includes tests for http://universities.hipolabs.com/search API

  @countryFilter
  Scenario: Correct universities are returned when searching by country
    Given University search API is accessible
    When I search for universities by country 'Poland'
    Then the requests response will contain universities with country 'Poland'

  @nameFilter
  Scenario: Correct universities are returned when searching by name
    Given University search API is accessible
    When I search for universities by name 'Science'
    Then the requests response will contain universities containing name 'Science'

  @countryFilter
  Scenario: Correct universities are returned when searching with limit
    Given University search API is accessible
    When I search for universities with limit 5
    Then the requests response will contain 5 universities