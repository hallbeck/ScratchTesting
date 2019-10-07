package com.testing.testCases;



import com.testing.utils.DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Scratch {
    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String FILMS_URL = BASE_URL + "films/";
    private static final String PLANETS_URL = BASE_URL + "planets/";
    private static final String PEOPLE_URL = BASE_URL + "people/";
    private static final String SPECIES_URL = BASE_URL + "species/";
    private static final String STARSHIPS_URL = BASE_URL + "starships/";
    private static final String VEHICLES_URL = BASE_URL + "vehicles/";

    @Test(dataProvider = "people", dataProviderClass = DataProviders.class)
    public void firstTest(int number, String name){
        ValidatableResponse response = given()
                .header("Content-Type","application/json")
                .get(PEOPLE_URL+number)
                .then()
                .log().body(true)
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(name));
    }
    @Test(/*dataProvider = "people", dataProviderClass = DataProviders.class*/)
    public static void secondTest(/*int number, String name*/){
        JsonPath response = given()
                .header("Content-Type","application/json")
                .get(PEOPLE_URL).jsonPath();
        int count = response.getInt("count");

        for(int i = 1; i < count+2; i++) {
            JsonPath single = given()
                    .header("Content-Type", "application/json")
                    .get(PEOPLE_URL +i+"/").jsonPath();
            System.out.println("number: " + i);
            String pname = single.get("name");
            System.out.println("Name: " + pname + " was born in "+single.get("birth_year"));
        }
    }

    @Test(dataProvider = "single", dataProviderClass = DataProviders.class)
    public void findSpecificActor(String personLookingFor) {
        JsonPath response = given().header("Accept", "application/json")
                .log().all()
                .get(PEOPLE_URL+"?search="+personLookingFor).jsonPath();

        int count = response.getInt("count");
        //response = response.setRoot("results");
       // System.out.println("response: " + response);
        for(int i = 0; i < count; i++) {
            String url = response.getList("results", Name.class).get(i).url;
            System.out.println("url: " + url);
            JsonPath single = given()
                    .header("Content-Type", "application/json")
                    .get(url).jsonPath();
            System.out.println("personLookingFor: " + personLookingFor);
            String pname = single.getString("name").replaceAll("[^\\dA-Za-z ]", "");
            System.out.println("Name: " + pname + " was born in "+single.getString("birth_year").replaceAll("[^\\dA-Za-z ]", ""));
        }


    }
    public static class Name {
        public String name; //string -- The name of this person.
        public String birth_year; // string -- The birth year of the person, using the in-universe standard of BBY or ABY - Before the Battle of Yavin or After the Battle of Yavin. The Battle of Yavin is a battle that occurs at the end of Star Wars episode IV: A New Hope.
        public String eye_color; // string -- The eye color of this person. Will be "unknown" if not known or "n/a" if the person does not have an eye.
        public String gender; // string -- The gender of this person. Either "Male", "Female" or "unknown", "n/a" if the person does not have a gender.
        public String hair_color; // string -- The hair color of this person. Will be "unknown" if not known or "n/a" if the person does not have hair.
        public String height; // string -- The height of the person in centimeters.
        public String mass; // string -- The mass of the person in kilograms.
        public String skin_color; // string -- The skin color of this person.
        public String homeworld; // string -- The URL of a planet resource, a planet that this person was born on or inhabits.
        public List<String> films; // array -- An array of film resource URLs that this person has been in.
        public List<String>species; // array -- An array of species resource URLs that this person belongs to.
        public List<String>starships; // array -- An array of starship resource URLs that this person has piloted.
        public List<String>vehicles; // array -- An array of vehicle resource URLs that this person has piloted.
        public String url; // string -- the hypermedia URL of this resource.
        public String created; // string  -- the ISO 8601 date format of the time that this resource was created.
        public String edited; // string -- the ISO 8601 date format of the time that this resource was edited.

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirth_year() {
            return birth_year;
        }

        public void setBirth_year(String birth_year) {
            this.birth_year = birth_year;
        }

        public String getEye_color() {
            return eye_color;
        }

        public void setEye_color(String eye_color) {
            this.eye_color = eye_color;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHair_color() {
            return hair_color;
        }

        public void setHair_color(String hair_color) {
            this.hair_color = hair_color;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getSkin_color() {
            return skin_color;
        }

        public void setSkin_color(String skin_color) {
            this.skin_color = skin_color;
        }

        public String getHomeworld() {
            return homeworld;
        }

        public void setHomeworld(String homeworld) {
            this.homeworld = homeworld;
        }

        public List<String> getFilms() {
            return films;
        }

        public void setFilms(List<String> films) {
            this.films = films;
        }

        public List<String> getSpecies() {
            return species;
        }

        public void setSpecies(List<String> species) {
            this.species = species;
        }

        public List<String> getStarships() {
            return starships;
        }

        public void setStarships(List<String> starships) {
            this.starships = starships;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<String> vehicles) {
            this.vehicles = vehicles;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEdited() {
            return edited;
        }

        public void setEdited(String edited) {
            this.edited = edited;
        }
    }

    /*public class NameList {
        private ArrayList<Name> results;
        private String next;
        private String previous;
        private int count;

        public NameList() {
            results = new ArrayList<>();
        }
        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }


        public ArrayList<Name> getResults() {
            return results;
        }

        public void setResults(ArrayList<Name> results) {
            this.results = results;
        }


        // standard constructor and getter/setter
    }*/


}
