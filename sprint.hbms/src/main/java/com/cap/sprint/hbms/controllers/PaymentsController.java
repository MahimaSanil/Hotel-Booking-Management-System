package com.cap.sprint.hbms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cap.sprint.hbms.entities.Payments;
import com.cap.sprint.hbms.entities.RoomDetails;
import com.cap.sprint.hbms.repos.IPaymentsRepository;
import com.cap.sprint.hbms.services.PaymentsServicesImpl;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1")
@Api(value = "Payments", tags = { "PaymentsAPI" })
public class PaymentsController 
{
	@Autowired
	PaymentsServicesImpl paymentsServicesImpl;
	

	
	
	/**
	 * This method is for adding Payment Details
	 * 
	 * 
	 * @return payment
	 * @throws NotFoundException
	 * @throws AlreadyExistsException
	 * 
	 */

	@PostMapping("/payment")
	@ApiOperation(value = "Add payment", notes = "Provide Payment Details ", response = Payments.class)
	
	public ResponseEntity<Payments> addPayments(@ApiParam(value = "Payment for Booking") @RequestBody Payments payment)
	{
		Payments payments = paymentsServicesImpl.addPayment(payment);
		return new ResponseEntity<Payments>(payments,HttpStatus.CREATED);
	}
	/**
	 * This method is for fetching payment details by id
	 * 
	 * @return Payments
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/payment/{id}")
	@ApiOperation(value = "View Payment by id", notes = "Provide payment id of payment to be viewed", response = Payments.class)
	public ResponseEntity<Payments> findPayment(@ApiParam(value = "ID value to view payment")@PathVariable int id)
	{
		Payments payment = paymentsServicesImpl.viewPayments(id).get();
		return new ResponseEntity<>(payment,HttpStatus.OK);
				
	}
	
	@GetMapping("/payments")
//	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "View all rooms", response = RoomDetails.class)
	public ResponseEntity<List<Payments>> findAllPayments() {
		List<Payments> rooms = paymentsServicesImpl.findAllPayments();
		return new ResponseEntity<>(rooms,HttpStatus.OK);
	}
}
