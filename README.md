# UW-Open-Data-API
A Java Client Library for the University of Waterloo Open Data API. The Library fetches data from the UW Open Data servers,
parses the reuslts, and wraps them into pretty Java objects for you to use.

## Setup
The Library uses [org.json](http://www.json.org/java/) to parse JSON results. It must be included in your project for the library
to work. A download to the version used in this library can be found [here](https://code.google.com/p/org-json-java/downloads/list).

## Quick Start
Take a look at the core classes which are found [here](/Core). There you will find an overview, and examples for 
using one or more url at a time.

# Current Supported Endpoints
## Server
* [/server/time](/Server)
* [/server/codes](/Server)

## API
* [/api/usage](/API)
* [/api/services](/API)
* [/api/methods](/API)
* [/api/versions](/API)
* [/api/changelog](/API)

## Building
* [/builings/list](/Building)
* [/builings/{building_code}](/Building)
* [/builings/{building}/{room}/courses](/Building)

## Codes
* [/codes/units/](/Codes)
* [/codes/terms](/Codes)
* [/codes/groups](/Codes)
* [/codes/subjects](/Codes)
* [/codes/instructions](/Codes)

## Resources
* [/resources/tutors](/Resources)
* [/resources/printers](/Resources)
* [/resources/infosessions](/Resources)
* [/resources/goosewatch](/Resources)

## Terms
* [/terms/{term}/examschedule](/Terms)
* [/terms/{term}/{subject}/schedule](/Terms)
* [/terms/{term}/{subject}/{catalog_number}/schedule](/Terms)
* [/terms/{term}/infosessions](/Terms)

## Weather
* [/weather/current](/Weather)

## News
* [/news](/News)
* [/news/{site}](/News)
* [/news/{site}/{id}](/News)

## Events
* [/events](/Events)
* [/events/{site}](/Events)
* [/events/{site}/{id}](/Events)
* [/events/holidays](/Events)

# Obtaining a Key
You need a valid API key to use this library. You can get one [here](https://api.uwaterloo.ca/).

# Official API
The official UW Open Data API is located [here](https://github.com/uWaterloo/api-documentation). 

## TODO
* Add Services end points
* Add News end points
* Add Events end points
* Add Course end points
* Add Food Service end points
* Convert Objects to extends Parcelable for better android use
