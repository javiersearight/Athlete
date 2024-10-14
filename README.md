### Getting Started

clone the repo locally:

`git clone git@github.com:javiersearight/Athlete.git`

open the project using Android Studio

attach a test device

install debug build of the app

`./gradlew installDebug`

app requires Strava app for authentication. Webview for authentication is not supported

opend the app and when prompted 'authorize' app on Strava


Future improvements:

- add webview support for authentication
- add sharedpreferences support for oauth token to persist between app launched. Currently only maintained in memory
- add datastore support to persist athlete model. currently get authenticated athlete api call made to trigger authentication flow
- improved error handling to check authentication on all api calls
- add support for refreshing token once token has expired
