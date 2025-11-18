package ai.cyrock.vector;

import ai.cyrock.gigamap.vector.DistanceFunction;
import ai.cyrock.gigamap.vector.VMathF;
import ai.cyrock.gigamap.vector.VectorIndex;
import ai.cyrock.gigamap.vector.VectorIndexer;
import org.eclipse.serializer.util.X;
import org.eclipse.store.gigamap.types.GigaMap;

/**
 * Little demo of the vector index extension for the {@link GigaMap}.
 */
public class MainTestCities
{
    public static void main(final String[] args)
    {
        // Setup GigaMap and add entities (cities with X/Y coordinates)
        final GigaMap<City> gigaMap = GigaMap.New();
        gigaMap.addAll(CITIES);

        // Create the indexer
        final VectorIndexer<City> indexerCity = new IndexerCity("City XY");

        // add a vector index using the cities' X/Y coordinates as a trivial 2D Vector
        final VectorIndex<City> vectorIndex = indexerCity.addIndexTo(gigaMap);

        // search all cities within 100 km of ("similar to") a sample city
        vectorIndex.linearSearch(new City("Weiden", 133, -166), 100, System.out::println);
    }


    record City(String name, float x, float y){}


    static class IndexerCity extends VectorIndexer.Abstract<City>
    {
        IndexerCity(final String name)
        {
            super(name);
        }

        @Override
        public int dimensions()
        {
            return 2;
        }

        @Override
        public float[] index(final City city)
        {
            return X.floats(city.x, city.y);
        }

        @Override
        public DistanceFunction distanceFunction()
        {
            return VMathF::distanceEuclidian;
        }

    }


    // AI-generated list of 50 German cities with SIMPLIFIED positional data. Only for demonstration purposes.
    static final City[] CITIES =
    {
        new City("Niederdorla"  ,    0,    0), // roughly in the center
        new City("Aachen"       , -319, - 44),
        new City("Augsburg"     ,   36, -289),
        new City("Berlin"       ,  213,  161),
        new City("Bielefeld"    , -156,  100),
        new City("Bochum"       , -241,   33),
        new City("Bonn"         , -269, - 22),
        new City("Braunschweig" , - 14,  144),
        new City("Bremen"       , -128,  239),
        new City("Chemnitz"     ,  163, - 22),
        new City("Darmstadt"    , -142, -100),
        new City("Dortmund"     , -227,   44),
        new City("Dresden"      ,  234,    0),
        new City("Duisburg"     , -269,   33),
        new City("Düsseldorf"   , -277,   22),
        new City("Erfurt"       ,   43,    0),
        new City("Essen"        , -248,   33),
        new City("Frankfurt"    , -128, - 78),
        new City("Freiburg"     , -191, -356),
        new City("Fürth"        ,   52, -189),
        new City("Göttingen"    , - 71,   56),
        new City("Halle (Saale)",  100,   33),
        new City("Hamburg"      , - 21,  295),
        new City("Hannover"     , - 71,  161),
        new City("Heidelberg"   , -149, -167),
        new City("Ingolstadt"   ,   84, -273),
        new City("Karlsruhe"    , -156, -211),
        new City("Kassel"       , - 85,   33),
        new City("Kiel"         ,    0,  344),
        new City("Köln"         , -267,    0),
        new City("Krefeld"      , -277,   11),
        new City("Leipzig"      ,  128,   22),
        new City("Lübeck"       ,   29,  322),
        new City("Magdeburg"    ,   71,  144),
        new City("Mainz"        , -156, - 89),
        new City("Mannheim"     , -156, -156),
        new City("München"      ,   71, -272),
        new City("Münster"      , -234,  100),
        new City("Nürnberg"     ,   55, -185),
        new City("Oberhausen"   , -262,   33),
        new City("Offenbach"    , -128, - 89),
        new City("Paderborn"    , -149,   78),
        new City("Potsdam"      ,  184,  144),
        new City("Regensburg"   ,  131, -240),
        new City("Rostock"      ,  106,  367),
        new City("Stuttgart"    , -106, -206),
        new City("Ulm"          , - 28, -289),
        new City("Wiesbaden"    , -156, - 78),
        new City("Wolfsburg"    ,    0,  144),
        new City("Wuppertal"    , -255,   11),
        new City("Würzburg"     , - 28, -122),
    };

}
