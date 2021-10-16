package br.com.ordemservicos.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ordemservicos.models.enums.StatusOS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ORDEMSERVICOS")
public class OrdemServico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@Column(nullable = false, precision = 16, scale = 4)
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusOS status;
	
	private String observacoes;
	
}
