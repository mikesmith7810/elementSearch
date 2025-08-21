# Element Data

A simple web service which supports searching and sorting element data. Query results are returned as a list of
elements, formatted as a JSON document.

Data comes from https://www.kaggle.com/datasets/mexwell/periodic-table-of-elements
and is available under the [CCO: Public Domain](https://creativecommons.org/publicdomain/zero/1.0/) licence.

## Querying the data

A number of query parameters are available to customize the search criteria. All query parameters are optional and
may be applied in any order. The full list of query parameters is:

**TBC**

* `TBC=Low|Medium|High` - Filter by TBC. When this parameter is omitted, all entries are returned.

# Building and Running

The application runs with Java 21.

`./gradlew clean build`

`./gradlew bootRun`

# List of things to look at

- ControllerAdvice
- Parsing Quotes from CVS
- Sorting
- Caching
- 
