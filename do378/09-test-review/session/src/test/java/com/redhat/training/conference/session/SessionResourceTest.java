package com.redhat.training.conference.session;

import com.redhat.training.conference.speaker.Speaker;
import com.redhat.training.conference.speaker.SpeakerService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class SessionResourceTest {

    @RestClient
    @InjectMock
    SpeakerService speakerService;

    @Test
    public void testCreateSession() {

        given()
                .contentType("application/json")
                .and()
                .body(sessionWithSpeakerId(12))
                .when()
                .post("/sessions")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("speakerId", equalTo(12));
    }

    @Test
    public void testGetSessionWithSpeaker() {

        int speakerId = 12;

        Mockito.when(
                speakerService.getById(Mockito.anyInt())
        ).thenReturn(
                new Speaker(speakerId, "Pablo", "Solar")
        );

        given()
                .contentType("application/json")
                .and()
                .body(sessionWithSpeakerId(speakerId))
                .post("/sessions");

        when()
                .get("/sessions/1")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("speaker.firstName", equalTo("Pablo"));
    }

    private Session sessionWithSpeakerId(int speakerId) {
        Session session = new Session();
        session.speakerId = speakerId;
        return session;
    }

}
