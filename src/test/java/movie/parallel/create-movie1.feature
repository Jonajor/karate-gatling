Feature: Cadastrar novo filme
  Background:
#    * url baseUrl
#    * def pathUrl = 'movies'
     * def json = {"id":10000,"description":"Filme da Aline"}

  Scenario: criar e recuperar novo filme
    Given url 'http://localhost:8080/api/movies'
    And request {description: 'De volta para o Futuro'}
    When method post
    Then status 201
#    And match response == {"id":10000,"description":"De volta para o Futuro"}

  Scenario: Consulta
    Given url 'http://localhost:8080/api/movies/10000'
    When method get
    Then status 200
#    And match response == {id: '#notnull', description: 'De Volta para o Futuro'}

  Scenario: talterar filme
    Given url 'http://localhost:8080/api/movies/10001'
    And request json
    When method put
    Then status 200
#    And match response == {"id":10000,"description":"Filme da Aline"}