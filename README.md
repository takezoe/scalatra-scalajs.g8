scalatra-template
=================

Template project for the web application which is based on Scalatra with Scala.js.

##Usage

Clone this repository and overwrite organization and name in `build.sbt` and hit the command below:

```
./sbt.sh compileAll
./sbt.sh ~server/container:start
```

Then access to http://localhost:8080/ using your web browser.

##Components

* Scala 2.11.6
* scalatra 2.3.1
* Scala.js 0.6.2
* twirl 1.0.4
* slick 3.0.0
* scalatra-forms 0.1.0
* sbt 0.13.8
