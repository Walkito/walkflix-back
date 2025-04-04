package br.com.walkflix.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperUtil {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static <T> T convertToDTO(Object newObj, Class<T> dtoClass){
        return new ModelMapper().map(newObj, dtoClass);
    }
}
