package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Persona;

@Repository
@Transactional
public class PersonaRepositoryImpl implements IPersonaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Persona buscarporId(Integer id) {
		return this.entityManager.find(Persona.class, id);
	}

	@Override
	public void insertar(Persona persona) {
		this.entityManager.persist(persona);
	}

	@Override
	public void actualizar(Persona persona) {
		this.entityManager.merge(persona);
	}

	@Override
	public void eliminar(Integer id) {
		Persona persona = this.buscarporId(id);
		this.entityManager.remove(persona);
	}

	@Override
	public List<Persona> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Persona> miQuery = this.entityManager.createQuery("SELECT p FROM Persona p", Persona.class);
		return miQuery.getResultList();
	}

}
