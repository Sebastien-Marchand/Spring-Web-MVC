package dev.hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.repository.ClientRepository;

@RestController
@RequestMapping("/hotel")
@EnableJpaRepositories("dev.repository")
public class WebApi {
	
	private static final Logger LOGGER = Logger.getLogger(WebApi.class.getName());
	
	
	public WebApi(ClientRepository clientRp) {
		super();
		this.clientRp = clientRp;
	}

	ClientRepository clientRp;
	
		@RequestMapping(method = RequestMethod.GET, path = "/clients")
		public List<Client> Clien(@RequestParam Integer start,  @RequestParam Integer size) {
			List<Client> msg = clientRp.findAll(PageRequest.of(start, size)).toList();
			for ( Client leclient : msg)
			{
				LOGGER.info(leclient.getNom());
			}
			return msg;
		}
		
		//http://localhost:8080/hotel/dcf129f1-a2f9-47dc-8265-1d844244b192
		@RequestMapping(method = RequestMethod.GET, path = "/{leUUID}")
		public ResponseEntity<Client> RequeteUUID(@PathVariable UUID leUUID) {
			Optional<Client> uuidClient = clientRp.findById(leUUID);
			if(uuidClient.isPresent())
			{
				return ResponseEntity.status(HttpStatus.OK).body(uuidClient.get());
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
		//http://localhost:8080/hotel/reservations
		//Reservation(LocalDate dateDebut, LocalDate dateFin, Client client, List<Chambre> chambres
		//		@RequestMapping(method = RequestMethod.POST, path = "/reservations")
	//			public ResponseEntity<Client> PostClients(@Valid @RequestBody  Client leClient ) {
					
					
			//		if(leClient.getNom().length() >= 2 )
		//			{
			//			return ResponseEntity.status(HttpStatus.OK).body(uuidClient.get());
			//		}
		//			else {
		//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		//			}
					
		//		}
}
