# sesame-bagel
Swiftly take home

[![Build status](https://ci.appveyor.com/api/projects/status/vsvqf591aqb5anb3/branch/master?svg=true)](https://ci.appveyor.com/project/jbhsieh/sesame-bagel/branch/master)

## Installation
- install sbt
- sbt compile

## Usage
- sbt "run sample-filename"
- creates a collection of ProductRecords and just prints them to the console.

## Tests
- sbt test
- unit tests are not exhaustive but cover expected use cases.

## misc notes
- productId is Int because as 8 characters, it does not need a Long.
