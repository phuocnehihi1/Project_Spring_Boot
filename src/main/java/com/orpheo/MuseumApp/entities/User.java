package com.orpheo.MuseumApp.entities;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.orpheo.MuseumApp.ENUM.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  private String firstname;
	  private String lastname;
	  private String email;
	  private String password;
	  @Enumerated(EnumType.STRING)
	  private Role role;

//	  @OneToMany(mappedBy = "user")
//	  private List<Token> tokens;

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return role.getAuthorities();
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public User() {}
	public User(Integer id, String firstname, String lastname, String email, String password, Role role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	  
}
