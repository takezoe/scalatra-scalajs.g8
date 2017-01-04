scalatra-scalajs.g8
=================

giter8 template for web applications based on Scalatra and Scala.js

## Usage

You can create and run a project as following:

```
$ sbt new takezoe/scalatra-scalajs.g8
$ cd <name-of-app>
$ sbt compileAll
$ sbt ~server/jetty:start
```

Then open http://localhost:8080/ in your browser.

## Components

* Scala 2.12.1
* scalatra 2.5.0
* Scala.js 0.6.14
* twirl 1.3.0
* sbt 0.13.13
