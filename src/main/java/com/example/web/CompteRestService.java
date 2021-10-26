package com.example.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.CompteRepository;
import com.example.entities.Compte;


@Component
@Path("/Banque")
public class CompteRestService {
	@Autowired
	private CompteRepository compteRepository;
	
	@POST
	@Path("/comptes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@RequestParam Compte compte) {
		
		compteRepository.save(compte);
		return Response
				.status(Response.Status.OK)
				.entity(compte)
				.build();
	}
	
	
	@GET
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> getUsers(){
		return compteRepository.findAll();
	}
	
	
	@GET
	@Path("/comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response getCompte(@PathParam(value = "code")Long code){
		Optional<Compte> cmp= compteRepository.findById(code);
		return Response
				.status(Response.Status.OK)
				.entity(cmp)
				.build();
	}


	@DELETE
	@Path("/comptes/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam(value = "code") Long code) {
		compteRepository.deleteById(code);
	}
	
	
	@PUT
    @Path("/comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response updateCompt(@PathParam(value="code") long code, @RequestParam Compte cpnew   ) {
	     Optional<Compte> cpold =Optional.of(compteRepository.findById(cpnew.getCode())).orElseThrow(RuntimeException::new);
	     compteRepository.save(cpnew);
	     return Response
					.status(Response.Status.OK)
					.entity(cpnew)
					.build();
		
	}
	   
	
	@GET
	@Path("/comptes/conversion/{code}")
	public double conversion(@PathParam(value = "code")Long code,Compte compte) {
		double eur=0.305331;
		double soldecmpt =compteRepository.findById(code).get().getSolde();
		double conversion=soldecmpt*eur;
		return conversion;
	}

}