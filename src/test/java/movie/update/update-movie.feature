Feature: Alterar filme
  Background:
#    * url baseUrl
#    * def pathUrl = 'movies/1'
    * def json = {"id":10000,"description":"Filme da Aline"}

  Scenario: alterar filme
    Given url 'http://localhost:8080/api/movies/10000'
    And request json
    When method put
    Then status 200
#    And match response == {"id":10000,"description":"Filme da Aline"}