scalatra-template
=================

Template project for the web application which is based on Scalatra with Scala.js.

## Usage

Clone this repository and overwrite organization and name in `build.sbt` and hit the command below:

```
./sbt.sh compileAll
./sbt.sh ~server/jetty:start
```

Then access to http://localhost:8080/ using your web browser.

## Components

* Scala 2.12.1
* scalatra 2.5.0
* Scala.js 0.6.14
* twirl 1.3.0
* slick 3.2.0-M2
* scalatra-forms 1.1.0
* sbt 0.13.13
