package ca.gbc.roomservice;

import ca.gbc.roomservice.dto.RoomDTO;
import ca.gbc.roomservice.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomServiceIntegrationTest extends PostgreSQLBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoomService roomService;

    @Test
    public void testGetAllRooms() throws Exception {

        RoomDTO roomDTO = new RoomDTO((String) null,"Meeting Room", 20, "Projector, Whiteboard", true);

        mockMvc.perform(post("/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].roomName").value("Meeting Room"))
                .andExpect(jsonPath("$[0].capacity").value(20))
                .andExpect(jsonPath("$[0].features").value("Projector, Whiteboard"))
                .andExpect(jsonPath("$[0].availability").value(true));
    }

    @Test
    public void testUpdateRoomAvailability() throws Exception {

        RoomDTO roomDTO = new RoomDTO("Lecture Hall", 50,
                "Sound System, Projector", true);
        MvcResult result = mockMvc.perform(post("/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDTO)))
                .andExpect(status().isOk())
                .andReturn();


        String jsonResponse = result.getResponse().getContentAsString();
        RoomDTO createdRoom = objectMapper.readValue(jsonResponse, RoomDTO.class);
        String roomId = String.valueOf(createdRoom.getId());
        System.out.println("Created Room ID: " + roomId);

        assertThat(roomId).isNotNull();

        mockMvc.perform(put("/rooms/" + roomId + "/availability")
                        .param("availability", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomName").value("Lecture Hall"))
                .andExpect(jsonPath("$.capacity").value(50))
                .andExpect(jsonPath("$.features").value("Sound System, Projector"))
                .andExpect(jsonPath("$.availability").value(true));
    }
}
