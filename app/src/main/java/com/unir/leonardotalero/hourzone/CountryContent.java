package com.unir.leonardotalero.hourzone;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CountryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Country> ITEMS = new ArrayList<Country>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Country> ITEM_MAP = new HashMap<String, Country>();
    //public static final Map<String, Country> ITEM_MAP_total = new HashMap<String, Country>();

    private static final int COUNT = 5;

    static {

        //int vari = R.drawable.agameofthrones;
        TimeZone myTimeZone;
        myTimeZone = TimeZone.getDefault();



        myTimeZone = TimeZone.getTimeZone("GMT-05:00");
        Country country_tempo =new Country("Colombia,Bogota",myTimeZone,"https://en.wikipedia.org/wiki/Colombia");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT+01:00");
        country_tempo =new Country("Spain,Madrid",myTimeZone,"https://en.wikipedia.org/wiki/Spain");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT-03:00");
        country_tempo =new Country("Argentina,Buenos Aires",myTimeZone,"https://en.wikipedia.org/wiki/Argentina");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT+01:00");
        country_tempo =new Country("France,Paris",myTimeZone,"https://en.wikipedia.org/wiki/France");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT-04:30");
        country_tempo =new Country("Venezuela,Caracas",myTimeZone,"https://en.wikipedia.org/wiki/Venezuela");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT-05:00");
        country_tempo =new Country("Usa,New York",myTimeZone,"https://es.wikipedia.org/wiki/Estados_Unidos");
        addItem(country_tempo);
        myTimeZone = TimeZone.getTimeZone("GMT+09:00");
        country_tempo =new Country("Japan,Tokio",myTimeZone,"https://en.wikipedia.org/wiki/Japan");
        addItem(country_tempo);



    }

    private static void addItemmap(Country item) {
        // ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }

    private static void addItem(Country item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }

    private static Country createCountryItem(String nombre,TimeZone timezone,String url) {

        return new Country(nombre,timezone,url);
    }


    /**
     * A dummy item representing a piece of content.
     */
    /*
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
    */
}
