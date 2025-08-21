# Element Data

A simple web service which supports searching and sorting element data. Query results are returned as a list of
elements, formatted as a JSON document.

Data comes from https://www.kaggle.com/datasets/mexwell/periodic-table-of-elements
and is available under the [CCO: Public Domain](https://creativecommons.org/publicdomain/zero/1.0/) licence.

## Querying the data

The endpoint is -

`http://localhost:8080/getElements`

A number of query parameters are available to customize the search criteria. All query parameters are optional and
may be applied in any order. The full list of query parameters is:

**Parameter Examples**

* minDensity - 1.5
* maxDensity - 2.5
* phase solid | gas | liquid
* sort - name_asc | name_desc | density_asc | density_desc
* limit - 10

`http://localhost:8080/getElements?minDensity=1.5&maxDensity=2.5&phase=solid&sort=name_asc&limit=10`

# Building and Running

The application runs with Java 21.

`./gradlew clean build`

`./gradlew bootRun`

# List of things to look at

- Exceptions and ControllerAdvice
- Validation
- Logging
- Interfaces

