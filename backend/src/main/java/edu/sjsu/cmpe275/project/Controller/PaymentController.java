package edu.sjsu.cmpe275.project.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class PaymentController {

	@PostMapping("makePayment")
	public ResponseEntity<Object> makePayment(@RequestBody String payment) {
		System.out.println();
		return null;
	}
}
