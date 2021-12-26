# Xiaourl
This Application allows us to shorten a long URL

Usage
-----
* If original url length is <= 100, then below will work.

`GET https://www.javedrpi.com/shorturl?url=https://medium.com/big-data-engineering/hello-kafka-world-the-complete-guide-to-kafka-with-docker-and-python-f788e2588cfc` 

* Need to add the client_id and api_token configured in DB to test below. This can be used to shorten url with length more than 100 characters.

`curl --location --request POST 'https://www.javedrpi.com/shorty' \
--header 'client_id: <SOME_VALUE>' \
--header 'api_token: <SOME_VALUE>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "originalUrl": "https://stackoverflow.com/questions/40511450/printing-hashmap-of-hashmaps-map-entry-or-java8"
}'`


