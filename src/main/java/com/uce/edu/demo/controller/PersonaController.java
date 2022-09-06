package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Persona;
import com.uce.edu.demo.service.IPersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService iPersonaService;

	// GET
	// http://localhost:8080/personas
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) { // nombre de la vista (String)
		List<Persona> lista = this.iPersonaService.buscarTodos();
		modelo.addAttribute("personas", lista);
		return "vistaListaPersonas";
	}

	// Consultando
	// Path /{}
	@GetMapping("/buscarUno/{idPersona}")
	public String buscarPersona(@PathVariable("idPersona") Integer id, Model modelo) {
		// sysout(id)
		Persona person = this.iPersonaService.buscarporId(id);
		modelo.addAttribute("persona", person);
		return "vistaPersona"; // modelo del cuadro
	}

	@PutMapping("/actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id, Persona persona) {
		persona.setId(id);
		this.iPersonaService.actualizar(persona);
		return "redirect:/personas/buscar"; // cuando quiero redireccionar
	}

	@DeleteMapping("/borrar/{idPersona}")
	public String borrarPersona(@PathVariable("idPersona") Integer id) {
		this.iPersonaService.eliminar(id);
		return "redirect:/personas/buscar";
	}

	@PostMapping("/insertar")
	public String insertarPersona(Persona persona) {
		this.iPersonaService.guardar(persona);
		return "redirect:/personas/buscar";
	}

	// Metodos de redireccionamientos a paginas
	@GetMapping("/nuevaPersona")
	public String paginaNuevaPersona(Persona persona) {
		return "vistaNuevaPersona";
	}

}
