# User Application

### Reference Documentation
We’ll build a hypermedia-driven REST service with Spring HATEOAS, and Json-Api a library of APIs that you can use to easily create links pointing to Spring MVC controllers, build up resource representations, and control how they’re rendered into supported hypermedia formats such as HAL.

* [Official HAL documentation](http://stateless.co/hal_specification.html)
* [Official JSONAPI documentation](https://jsonapi.org/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)

### Examples

* JSON API 
  * http://localhost:8080/api/roles?include=users&fields[roles]=name&fields[users]=name
  * http://localhost:8080/api/users?fields[users]=name,email
```json
{
   "data":[
      {
         "id":"1",
         "type":"users",
         "attributes":{
            "name":"Mario",
            "email":"mario@microtema.de"
         },
         "links":{
            "self":"http://localhost:8080/api/users/1"
         }
      },
      {
         "id":"2",
         "type":"users",
         "attributes":{
            "name":"Aeneas",
            "email":"aeneas@microtema.de"
         },
         "links":{
            "self":"http://localhost:8080/api/users/2"
         }
      },
      {
         "id":"3",
         "type":"users",
         "attributes":{
            "name":"Emmy",
            "email":"emmy@web.de"
         },
         "links":{
            "self":"http://localhost:8080/api/users/3"
         }
      }
   ],
   "meta":{
      "totalResourceCount":null
   }
}
```
* HAL (Hypertext Application Language)
  
  http://localhost:8080/browser/index.html#/
