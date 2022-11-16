package com.joel.food.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.food.domain.model.FotoProduto;
import com.joel.food.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		Long restauranteId = foto.getRestaurante();
		Long pprodutoId = foto.getProduto().getId();
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, pprodutoId);
		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}
		
		return produtoRepository.save(foto);
	}

}



