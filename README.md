# WebServiceJPA

Note that 8181 is used for the http port. Change the port in https://github.com/charroux/WebServiceJPA/blob/master/src/main/resources/application.properties

## Vérifier le contenu de la base

Open the H2 web console: http://localhost:8181/h2-console

In the login page make sure that you use jdbc:h2:mem:testdb as JDBC URL and org.h2.Driver as the Driver class.

## Get a list of persons (HTTP Get)

curl http://localhost:8181/persons

## Add a person (HTTP Post)

curl --header "Content-Type: application/json" --request POST --data '{"name":"Haddock"}' http://localhost:8181/persons

## Add a car (HTTP Post)

curl --header "Content-Type: application/json" --request POST --data '{"plateNumber":"22BB33"}' http://localhost:8181/vehicules

## Rent a car (HTTP Put)

curl --header "Content-Type: application/json" --request PUT --data '{"begin":"2020-10-09", "end":"2020-11-09"}' http://localhost:8181/vehicules/22BB33?personName=Haddock

## Call of a backend service

curl http://localhost:8181/frontendURL

The back end service: 


