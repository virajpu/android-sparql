# Installing the SPARQL endpoint #

The SPARQL endpoint in this project is based on the Sesame triple store, adapted to work with the sdcard of an Android device, and on a web application deployable with the [iJetty](http://code.google.com/p/i-jetty/) web application server for Android.

To install it, use the following steps:
  * install iJetty from the Android market (see [here](https://market.android.com/details?id=org.mortbay.ijetty)
  * from iJetty, download the web application [sparqldroid.war](http://android-sparql.googlecode.com/files/sparqldroid.war) (at the address http://android-sparql.googlecode.com/files/sparqldroid.war)
  * start (or restart) iJetty. The SPARQL endpoint should be at the address http://ip.of.the.phone:8080/sparqldroid/sparql (it is initially empty)

# Populate the triple store from an Android application #

If you create an Android application, you can simply add (and also remove) triples from the triple store that is used by the SPARQL endpoint using the extension of the Sesame API. It is necessary to include:
  * openrdf-sesame-2.4.2-onejar.jar - the jar file of the Sesame API (see the Downloads section)
  * slf4j-android-1.6.1-RC1.jar - the jar file for the logging API on Android (see the Downloads section)
  * the [extension of the Sesame API](http://code.google.com/p/android-sparql/source/browse/#git%2Fuk) (available in the Source section)

The code below shows a simple example of adding a triple to the triple store

```
   boolean created = false;
   Repository repo;
   RepositoryConnection con;
   File dataDir = null;

  // ensure the sdcard is mounted
   String state = Environment.getExternalStorageState();
   if (Environment.MEDIA_MOUNTED.equals(state)) {
       // get the directory of the triple store
       File topDir = Environment.getExternalStorageDirectory();
       String path = topDir.getAbsolutePath()+"/sesame_dir";
       dataDir = new File(path);
       if(!dataDir.exists()) {
           created = dataDir.mkdir();
       } else {
           created = true;
               }
       }
       if(created) {
           // connect to the repository
           repo = new SailRepository(new AndroidNativeStore(dataDir));
           try {
              repo.initialize();
              con = repo.getConnection();
               try {
                    // create and add the triple
                    ValueFactory vf = con.getValueFactory();
                    URI mathieu = vf.createURI("http://example.com/rdf/mathieu");
                    URI predicate = vf.createURI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
                    URI classURI = vf.createURI("http://example.com/rdf/NiceGuy");

                    con.add(mathieu, predicate, classURI);
               } finally {
                    // close everything
                    con.close();
                    repo.shutDown();
               } catch(Exception e) {
                    e.printStackTrace();
                    Log.e("android-sparql", "Oh NO! "+e.getMessage());
                }
         }
   }

```