package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StudentManagementApis {

    private Response response;
    private JSONObject studentBody;
    public static final String BASE_URI = "http://localhost:9080";


    @Given("using the relevant api {string}")
    public void use_appropriate_endpoints(String endpoint) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = endpoint;
    }

    @When("Adding a new student in database")
    public void adding_a_new_student_in_database(DataTable dataTable) {
        Map<String, String> studentData = dataTable.asMaps().get(0);
        studentBody = new JSONObject();
        studentBody.put("firstName", studentData.get("firstName"));
        studentBody.put("lastName", studentData.get("lastName"));
        studentBody.put("id", studentData.get("studentId"));
        studentBody.put("nationality", studentData.get("nationality"));
        studentBody.put("studentClass", studentData.get("studentClass"));

        RequestSpecification request = given().log().all().contentType(ContentType.JSON).body(studentBody.toString());
        response = request.log().all().when().post();
    }


    @Then("Verify expected status code {int}")
    public void test_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @When("Deleting a student in database with id {int}")
    public void delete_a_student(int studentId) {
        RequestSpecification request = given().log().all().contentType(ContentType.JSON).body(String.format("{\"id\":%s}", studentId));
        response = request.log().all().delete();
    }

    @When("Updating existing student information")
    public void update_student_info(DataTable dataTable) {
        Map<String, String> studentData = dataTable.asMaps().get(0);
        studentBody = new JSONObject();
        studentBody.put("firstName", studentData.get("firstName"));
        studentBody.put("lastName", studentData.get("lastName"));
        studentBody.put("id", studentData.get("studentId"));
        studentBody.put("nationality", studentData.get("nationality"));
        studentBody.put("studentClass", studentData.get("studentClass"));

        RequestSpecification request = given().log().all().contentType(ContentType.JSON).body(studentBody.toString());
        response = request.log().all().when().put();
    }

    @When("fetching records based on students class {string}")
    public void fetching_records_based_on_student_class(String studentClass) {
        RequestSpecification request = given().log().all().contentType(ContentType.JSON).param("studentClass", studentClass);
        response = request.log().all().when().get();
    }

    @When("Fetching records based on studentId {int}")
    public void fetching_records_based_on_student_id(int studentId) {
        RequestSpecification request = given().log().all().contentType(ContentType.JSON).param("studentId", studentId);
        response = request.log().all().get();
    }


    @When("Fetching records based on studentClass {string} and studentId {int}")
    public void fetching_records_based_on_class_and_student_id(String studentClass, int studentId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(studentId));
        map.put("studentClass", studentClass);
        RequestSpecification request = given().queryParams(map).log().all().contentType(ContentType.JSON);
        response = request.log().all().when().get();
    }

    @Then("Verify that response has right values first name {string} and last name {string} and nationality {string}")
    public void response_validation(String firstName, String lastName, String nationality) {
        JsonPath resp = response.jsonPath();
        assertEquals(resp.get("firstName[0]"), firstName);
        assertEquals(resp.get("lastName[0]"), lastName);
        assertEquals(resp.get("nationality[0]"), nationality);
    }
}
