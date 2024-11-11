package ca.gbc.approvalservice;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@ActiveProfiles("test")
@Testcontainers
public abstract class MongoDBBaseIntegrationTest {

    @Container
    protected static final MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @BeforeAll
    public static void setUp() {
        if (!mongoDBContainer.isRunning()) {
            mongoDBContainer.start();
        }

        // Set the MongoDB URI for Spring Data to connect to the TestContainers MongoDB instance
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

    @AfterAll
    public static void tearDown() {
        if (mongoDBContainer.isRunning()) {
            mongoDBContainer.stop();
        }
    }
}
