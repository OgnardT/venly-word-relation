# venly-word-relation

The project can be run by using "mvn spring-boot:run"

To call the apis you can use curl command like those:

*curl -X POST localhost:8000/**{endpoint}** -H 'Content-type:application/json' -d '{"word1": "**{value}**", "word2": "**{value}**", "relation": "**{value}**"}'*

the available APIs are: 

GET: **/words** to retrieve a list of all data in the database

GET: **/words/{relation}** to retieve all words that contains the specified relation

POST: **/words** to insert a data in the database
