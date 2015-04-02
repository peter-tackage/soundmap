Soundmap
========
A demo to use Volley, Gson, Google Maps and AppCompat with data provided by the SoundCloud API.

It also demos pure JUnit tests with the Android Gradle plugin. Unfortunately, these
tests require that the resources directory is included on the classpath, which doesn't work just yet.

Sadly, the SoundCloud API doesn't treat Track geotags as a first class citizen, it is only supported via tags. 
This means that the result set is often populated with garbage results with no parseable geotag data.

So despite the best claims of http://soundcheck.soundcloud.com/audio/map-your-audio/ geotagging searching via the SoundCloud API is broken.

Additionally, it appears that you can't use the SoundCloud API to query for a keyword *and* a tag in the one
 query. This effectively defeats the purpose of the app, oh well ... but it was fun to write.

And it looks a bit like this -

 <img src="https://github.com/peter-tackage/assets/raw/master/screenshots/soundmap/Screenshot_2015-04-02-10-41-45.png" alt="Soundmap Screenshot" width="300">
