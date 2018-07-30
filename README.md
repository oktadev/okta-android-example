# okta-android
Android App with authentication

This is the code for the blog post describing how to build an Android app without an IDE.
It describes the minimum files necessary for an Android project with Gradle build scripts.

## Setup

As per the article, you need to fill out `app/src/main/res/raw/okta_app_auth_config.json` as well as the defaultConfig section of `app/build.gradle` with your Okta app's Client ID and redirect URLs.

## Executing

Simply run `gradlew installDebug` and it should deploy to an attached device or emulator.
