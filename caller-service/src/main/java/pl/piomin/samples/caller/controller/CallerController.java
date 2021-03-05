package pl.piomin.samples.caller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.piomin.samples.caller.model.Caller;
import pl.piomin.samples.caller.repository.CallerRepository;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/caller")
public class CallerController {

	private RestTemplate restTemplate;

	CallerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${spring.application.name}")
	private String appName;
	@Value("${POD_NAME}")
	private String podName;
	@Value("${POD_NAMESPACE}")
	private String podNamespace;
	@Autowired
	private CallerRepository repository;

	@GetMapping("/ping")
	public String ping(@RequestHeader HttpHeaders headers) {
		Caller c = repository.save(new Caller(new Date(), podName));
		String callme = callme(headers);
		return appName + "(id=" + c.getId() + "): " + podName + " in " + podNamespace
				+ " is calling " + callme;
	}

	private String callme(HttpHeaders headers) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		Set<String> headerNames = headers.keySet();
		headerNames.forEach(it -> map.put(it, headers.get(it)));
		HttpEntity httpEntity = new HttpEntity(map);
		ResponseEntity<String> entity = restTemplate
				.exchange("http://callme-service.serverless.svc.cluster.local/callme/ping",
						HttpMethod.GET, httpEntity, String.class);
		return entity.getBody();
	}

}
