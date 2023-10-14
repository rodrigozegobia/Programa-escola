package com.ProjetoEscola.models.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;


import com.ProjetoEscola.models.pk.PrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_ROLE")
public class Role extends PrimaryKey {

	private static final long serialVersionUID = 2771653550343360576L;
	
	private String name;
	
	@ManyToMany
    @JoinTable(name = "professor_role", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private Set<Professor> professor = new HashSet<>();
	
	
	public Set<Professor> getProfessor() {
		return professor;
	}

	public void setProfessor(Set<Professor> professor) {
		this.professor = professor;
	}

	@ManyToMany
    @JoinTable(name = "aluno_role", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private Set<Aluno> aluno = new HashSet<>();
	
	public Set<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(Set<Aluno> aluno) {
		this.aluno = aluno;
	}
	
	@Column(name="NAME", length = 50, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + this.getId() +", name=" + name + ", professores=" + professor + ", alunos=" + aluno +"]";
	}
	
	

}
