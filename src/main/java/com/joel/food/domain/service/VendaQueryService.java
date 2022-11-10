package com.joel.food.domain.service;

import java.util.List;

import com.joel.food.domain.filter.VendaDiariaFilter;
import com.joel.food.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);


}
