package com.kol92v.dins.converters;


import com.kol92v.dins.dto.DTObj;
import com.kol92v.dins.entity.EntityDB;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Данный обобщенный интерфейс работает с типами {@link EntityDB} и {@link DTObj}.
 * Он обеспечивает контракт на конвертацию {@link DTO} в {@link Entity}
 * и наоборот.
 * @param <Entity> объекты типа {@link EntityDB}
 * @param <DTO> объекты типа {@link DTObj}
 * */

public interface ConverterEntityDTO<Entity extends EntityDB, DTO extends DTObj> {

    /**
     * Принимает параметр {@link Entity} и конвертирует его в {@link DTO}
     * @param entity объект подлежащий конвертации в тип {@link DTO}
     * @return возвращает после конвертации объект типа {@link DTO}
     * */
    DTO convert(Entity entity);

    /**
     * Принимает параметр {@link DTO} и конвертирует его в {@link Entity}
     * @param dto объект подлежащий конвертации в тип {@link Entity}
     * @return возвращает после конвертации объект типа {@link Entity}
     * */
    Entity convert(DTO dto);

    /**
     * В дефолтной реализации метод работает со списком, в своей работе использует логику
     * метода {@link ConverterEntityDTO#convert(EntityDB)}
     * @param allEntity список объектов типа {@link Entity}
     * @return возвращает после конвертации список объектов типа {@link DTO}
     * */
    default List<DTO> convertToDTO(List<Entity> allEntity) {
        return allEntity.stream().map(this::convert).collect(Collectors.toList());
    }

    /**
     * В дефолтной реализации метод работает со списком, в своей работе использует логику
     * метода {@link ConverterEntityDTO#convert(DTObj)}
     * @param allDTO список объектов типа {@link DTO}
     * @return возвращает после конвертации список объектов типа {@link Entity}
     * */
    default List<Entity> convertToEntity(List<DTO> allDTO) {
        return allDTO.stream().map(this::convert).collect(Collectors.toList());
    }

}
