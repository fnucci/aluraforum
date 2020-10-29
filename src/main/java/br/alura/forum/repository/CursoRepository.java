package br.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nome);
}
