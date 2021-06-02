package cucumber.definitions;

import com.epam.hw.lesson2.ButtonsListForJdbc;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

import static org.openqa.selenium.By.cssSelector;
import static org.testng.Assert.assertEquals;

public class JdbcTestSteps {
    private WebDriver driver;
    private static Connection connection;

    private void assertTrue(boolean valid) {
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/123";
        String user = "testuser123";
        String passwd = "testpassword123";
        return DriverManager.getConnection(url, user, passwd);
    }
    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    private void assertFalse(boolean closed) {
    }

    @Before
    public void init() throws SQLException {
        connection = getNewConnection();
    }

    @BeforeClass
    public void appSetup() {
        driver = new ChromeDriver();

    }

    @AfterTest
    public void cleanUp() throws SQLException {
        String query = "INSERT INTO user(state) VALUES (?) WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setNull(1, Types.NULL);
        statement.setString(2, "12345");
        statement.execute();
    }

    @AfterClass
    public void closeUp() {
        driver.close();
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

    @Given("column userId exists in the DB")
    public void checkThatColumnUserIdExists() throws SQLException {
        String query = "SELECT userId FROM User WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "12345");
        boolean hasResult = statement.execute();
        assertTrue(hasResult);
    }

    @And("column state exists in the DB")
    public void checkThatColumnStateExists() throws SQLException {
        String query = "SELECT state FROM User WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "12345");
        boolean hasResult = statement.execute();
        assertTrue(hasResult);
    }

    @And("state column = null")
    public void checkThatStateColumnIsNull() throws SQLException {
        String query = "SELECT state FROM User WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "12345");
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        boolean isNull = resultSet.wasNull();
        assertEquals(true, isNull, "state value is not null");
    }

    @When("user with customerId '12345' clicks |”(.*)|” ")
    public void clickTheButton(String buttonName, ButtonsListForJdbc buttonsListForJdbc) {
        selectButton(buttonsListForJdbc).click();
    }

    private WebElement selectButton(ButtonsListForJdbc buttonsListForJdbc) {
        return driver.findElements(cssSelector("Button")).stream()
                .filter(button -> button.getText().equals(buttonsListForJdbc.getButtonsDesc()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No button with such text " + buttonsListForJdbc.getButtonsDesc()));
    }

    @Then("column state should have |”(.*)|” ")
    public String checkStateColumnValueInDB(String value, ButtonsListForJdbc buttonsListForJdbc) throws SQLException {
        String query = "SELECT state FROM User WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "12345");
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        String columnState = resultSet.getString("state");
        assertEquals("started", columnState, "state column doesn't have such value" + ButtonsListForJdbc.STR.getButtonsDesc());
        return value = buttonsListForJdbc.getButtonsDesc();
    }
}
