package com.restjwtauth.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.restjwtauth.models.User;
import com.restjwtauth.models.dto.PhoneDTO;
import com.restjwtauth.models.dto.UserDTO;

public class Mapper {

	private static ModelMapper modelMapper = new ModelMapper();

	/**
	 * Usa a dependência ModelMapper para facilmente converter de User para UserDTO.
	 * A necessidade desse método em particular é a presença do objeto Phone dentro de
	 * User.
	 * 
	 * @param user
	 * @return
	 */
	public static UserDTO mapperUserToUserDTO(User user) {

		UserDTO output = Mapper.map(user, UserDTO.class);
		output.setPhones(Mapper.mapAll(user.getPhones(), PhoneDTO.class));

		return output;
	}

	/**
	 * Configuração do ModelMapper. STRICT significa que ele mapea de forma
	 * automática procando por termos iguais.
	 */
	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public static <D, T> D map(final T entity, Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	/**
	 * Mapar lista de um objeto para lista de um outro
	 * 
	 * @param entityList
	 * @param outCLass
	 * @return
	 */
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}
}
