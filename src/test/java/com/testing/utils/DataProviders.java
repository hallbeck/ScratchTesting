package com.testing.utils;

public class DataProviders {

    @org.testng.annotations.DataProvider(name = "people")
    public Object[][] people() {
        return new Object[][]{
                {1,"Luke Skywalker"},
        };
    }
    @org.testng.annotations.DataProvider(name = "single")
    public Object[][] single() {
        return new Object[][]{
                {"Jabba Desilijic Tiure"},
                {"Bolt"},
                {"fett"},
        };
    }
}
