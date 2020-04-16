package dev.hotel;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.repository.ClientRepository;

@RestController
@RequestMapping("/hotel")
public class WebApi {
	
	private static final Logger LOGGER = Logger.getLogger(WebApi.class.getName());
	
	@Autowired
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
}
