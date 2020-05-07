package br.com.faculdadedelta.modelo;

public class Serie {

	private Long id;
	private Genero genero;
	private Status status;
	private String nome;
	private String comentario;
	private Integer notaAvaliacao;

	public Serie() {
	}

	public Serie(Long id, Genero genero, Status status, String nome, String comentario, Integer notaAvaliacao) {
		super();
		this.id = id;
		this.genero = genero;
		this.status = status;
		this.nome = nome;
		this.comentario = comentario;
		this.notaAvaliacao = notaAvaliacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(Integer notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
