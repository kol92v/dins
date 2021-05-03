package com.kol92v.dins.converters;


import com.kol92v.dins.dto.DTO;
import com.kol92v.dins.entity.EntityDB;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Данный обобщенный интерфейс работает с типами {@link EntityDB} и {@link DTO}.
 * Он обеспечивает контракт на конвертацию {@link DTOImpl} в {@link Entity}
 * и наоборот.
 * @param <Entity> объекты типа {@link EntityDB}
 * @param <DTOImpl> объекты типа {@link DTO}
 * */

public interface ConverterEntityDTO<Entity extends EntityDB, DTOImpl extends DTO> {

    /**
     * Принимает параметр {@link Entity} и конвертирует его в {@link DTOImpl}
     * @param entity объект подлежащий конвертации в тип {@link DTOImpl}
     * @return возвращает после конвертации объект типа {@link DTOImpl}
     * */
    DTOImpl convert(Entity entity);

    /**
     * Принимает параметр {@link DTOImpl} и конвертирует его в {@link Entity}
     * @param dto объект подлежащий конвертации в тип {@link Entity}
     * @return возвращает после конвертации объект типа {@link Entity}
     * */
    Entity convert(DTOImpl dto);

    /**
     * В дефолтной реализации метод работает со списком, в своей работе использует логику
     * метода {@link ConverterEntityDTO#convert(EntityDB)}
     * @param allEntity список объектов типа {@link Entity}
     * @return возвращает после конвертации список объектов типа {@link DTOImpl}
     * */
    default List<DTOImpl> convertToDTO(List<Entity> allEntity) {
        return allEntity.stream().map(this::convert).collect(Collectors.toList());
    }

    /**
     * В дефолтной реализации метод работает со списком, в своей работе использует логику
     * метода {@link ConverterEntityDTO#convert(DTO)}
     * @param allDTO список объектов типа {@link DTOImpl}
     * @return возвращает после конвертации список объектов типа {@link Entity}
     * */
    default List<Entity> convertToEntity(List<DTOImpl> allDTO) {
        return allDTO.stream().map(this::convert).collect(Collectors.toList());
    }

}
