package com.petshop.manager.domain.utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    protected ModelMapper mapper;

    public MapperUtil() {
        mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());


    }

    public <S, D> List<D> mapAll(final Collection<S> entityList, Class<D> outCLass) {
        return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    public <S, D> D map(S source, Class<D> outClass) {
        return mapper.map(source, outClass);
    }

    public <S> S copy(final S source, S destination) {
        mapper.map(source, destination);
        return destination;
    }

    public ModelMapper getMapper() {
        return mapper;
    }
}
