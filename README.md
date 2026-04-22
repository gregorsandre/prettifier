# Prettifier

## Project Overview

Prettifier is a command-line tool that converts a flight itinerary text file into a customer-friendly format. It replaces encoded airport codes with full airport names, and converts ISO 8601 date/time tokens into readable formats.

## Setup and Installation

**Requirements:** Java (JDK 8 or later)

1. Clone or download the project
2. Create the build directory if it doesn't exist:
   ```
   mkdir -p build
   ```
3. Compile all source files:
   ```
   javac -d build src/csvReader.java src/inputFile.java src/outputFile.java src/prettifier.java
   ```

## Usage

```
java -cp build prettifier <input> <output> <airport-lookup>
```

| Argument | Description |
|---|---|
| `input` | Path to the input itinerary text file |
| `output` | Path where the converted output will be written |
| `airport-lookup` | Path to the airport lookup CSV file |

**Example:**
```
java -cp build prettifier ./input.txt ./output.txt ./airport-lookup.csv
```

## Input Format

The input file may contain the following encoded tokens:

### Airport Codes
| Token | Type | Example | Output |
|---|---|---|---|
| `#XXX` | IATA code (3 letters) | `#LAX` | Los Angeles International Airport |
| `##XXXX` | ICAO code (4 letters) | `##EGLL` | London Heathrow Airport |
| `*##City` | Municipality name | `*##London` | London |

### Dates and Times (ISO 8601)
| Token | Description | Example Output |
|---|---|---|
| `D(...)` | Date only | `05 Apr 2007` |
| `T12(...)` | 12-hour time with offset | `12:30PM (-02:00)` |
| `T24(...)` | 24-hour time with offset | `12:30 (-02:00)` |

- Zulu time offset `Z` is displayed as `(+00:00)`
- Malformed date/time tokens are left unchanged

## Error Handling

| Situation | Message |
|---|---|
| Wrong number of arguments | Usage instructions |
| Input file not found | `Input not found` |
| Airport lookup not found | `Airport lookup not found` |
| Airport lookup malformed | `Airport lookup malformed` |

In all error cases, the output file is not created or overwritten.

## Airport Lookup CSV Format

The CSV must contain the following columns (in any order):

`name`, `iso_country`, `municipality`, `icao_code`, `iata_code`, `coordinates`

The first row must be a header row. The file is considered malformed if any required column is missing or any cell is blank.
