import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public abstract class BaseIntegrationTest {

    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    protected static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @BeforeAll
    public static void setUp() {
        if (!postgreSQLContainer.isRunning()) {
            postgreSQLContainer.start();
            System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
            System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        }

        if (!mongoDBContainer.isRunning()) {
            mongoDBContainer.start();
            System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
        }
    }
}