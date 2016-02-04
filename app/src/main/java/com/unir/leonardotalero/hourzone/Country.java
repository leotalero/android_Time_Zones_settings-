package com.unir.leonardotalero.hourzone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by leonardotalero on 1/31/16.
 */




    public  class Country {

        private String name;
        private TimeZone timezone;
    private String url;

        public Country(String name, TimeZone timezone,String url) {
            this.name = name;
            this.timezone = timezone;
            this.url = url;
        }


        public Country() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TimeZone getTimezone() {
            return timezone;
        }

        public void setTimezone(TimeZone timezone) {
            this.timezone = timezone;
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

