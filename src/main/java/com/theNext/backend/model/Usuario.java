package com.theNext.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "tb_Usuario")
public class Usuario{
	    
	    @Id	
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

	    private String name;

        private String password;

        private String usuario;

	    public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        private String email;
	    
        private int cpf;

        private int grupo;

        private boolean isAtivo;
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCpf() {
            return cpf;
        }

        public void setCpf(int cpf) {
            this.cpf = cpf;
        }

        public int getGrupo() {
            return grupo;
        }

        public void setGrupo(int grupo) {
            this.grupo = grupo;
        }

        public boolean isAtivo() {
            return isAtivo;
        }

        public void setAtivo(boolean isAtivo) {
            this.isAtivo = isAtivo;
        }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	
}


