package dev.hotel;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.repository.ClientRepository;

@WebMvcTest(WebApi.class)
class HotelWebApiApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientRepository clientRp;
	
    @Test
    void testgetClient() throws Exception {
    	when(this.clientRp.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(Arrays.asList(new Client("Captain", "Crabe"))));
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders.get("/hotel/clients?start=0&size=2")
    			).andExpect(MockMvcResultMatchers.status().isOk())
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").isNotEmpty())
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Captain"));
    }
    
    @Test
    void testClientByUuid() throws Exception {
        UUID uuid = UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844255b192");
        
        Mockito.when(clientRp.findById(uuid)).thenReturn(Optional.of(new Client("Sponge", "bob")));

        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/dcf129f1-a2f9-47dc-8265-1d844255b192"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Sponge"));
    }

}
