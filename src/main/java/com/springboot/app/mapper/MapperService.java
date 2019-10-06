package com.springboot.app.mapper;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * @author sonia Interfaz para mapear de Dto a Entidad y de Entidad a Dto
 * @param <T>
 * @param <S>
 */
public interface MapperService<T, S> {

	/**
	 * Metodo que recibe una entidad y la mapea a Dto
	 * 
	 * @param entity
	 * @return Dto
	 */
	T mapToDto(S entity);

	/**
	 * Metodo que recibe un Dto y lo mapea a Entity
	 * 
	 * @param dto
	 * @return Entity
	 */
	S mapToEntity(T dto);

	/**
	 * Metodo que recibe una lista de entidades y lo mapea a lista de objeto tipo
	 * DTO.
	 * 
	 * @param listEntity
	 * @return listDto
	 */
	List<T> mapListToDto(List<S> listEntity);

	/**
	 * Metodo que recibe una pagina de entidades y lo mapea a pagina de objeto tipo
	 * DTO.
	 * 
	 * @param pageEntity
	 * @return pageDto
	 */
	Page<T> mapPageToDto(Page<S> pageEntity);

}
