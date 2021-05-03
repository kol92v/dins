package com.kol92v.dins.controllers;

import com.kol92v.dins.converters.ConverterEntityDTO;
import com.kol92v.dins.dto.DTO;
import com.kol92v.dins.entity.EntityDB;
import com.kol92v.dins.services.CRUDService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**Это абстрактный обобщенный класс, от которого контроллеры могут унаследовать основную логику распределния запросов.
 * Данный класс работает с объектами типа {@link EntityDB}, {@link DTO}
 * и {@link JpaRepository}.
 * Все запросы обрабатываются объектами типа {@link CRUDService}, который так же работает с переданными ему типами.
 * Для конвертации {@link Entity} в {@link DTOImpl} и наоборот используется {@link ConverterEntityDTO}.
 * @param <Entity> Entity типа {@link EntityDB}
 * @param <DTOImpl> DTO типа {@link DTO}
 * @param <Repository> DAO реализующий интерфейс {@link JpaRepository} и работающий с сущностями {@link Entity}
 *                    и ID типа {@link Integer}
 * @see EntityDB
 * @see DTO
 * @see CRUDService
 * @see ConverterEntityDTO
 * @see JpaRepository
 * */

@AllArgsConstructor
public class MainRestController<Entity extends EntityDB, DTOImpl extends DTO, Repository extends JpaRepository<Entity, Integer>> {

    /**
     * Сервис осуществляющий работу с сущностями в БД
     * */
    protected final CRUDService<Entity, Repository> crudService;

    /**
     * Объект осуществляющий конвертацию {@link Entity} в {@link DTOImpl} и наоборот
     * */
    protected final ConverterEntityDTO<Entity, DTOImpl> converterEntityDTO;

    /**
     * Метод конвертирует и отдает сервису для сохранения переданный параметр
     * @param dto объект для конвертации в {@link Entity} и передачу {@link MainRestController#crudService}
     * для дальнейшего сохранения в БД
     * @return {@link DTOImpl} сконвертированный из сохраненного {@link Entity} в базе данных
     * */
    @PostMapping("/")
    public DTOImpl save(@RequestBody DTOImpl dto) {
        Entity entity = converterEntityDTO.convert(dto);
        return converterEntityDTO.convert(crudService.save(entity));
    }

    /**
     * Метод конвертирует и отдает сервису для обновления переданный параметр
     * @param dto объект для конвертации в {@link Entity} и передачу {@link MainRestController#crudService}
     * для дальнейшего обновления в БД
     * @return {@link DTOImpl} сконвертированный из измененного {@link Entity} в базе данных
     * */
    @PutMapping("/")
    public DTOImpl update(@RequestBody DTOImpl dto) {
        Entity entity = converterEntityDTO.convert(dto);
        return converterEntityDTO.convert(crudService.update(entity));
    }

    /**
     * Метод передает {@link MainRestController#crudService} номер id по которому необходимо сделать удаление
     * @param id id сущности в БД, которое необходимо удалить
     * */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        crudService.delete(id);
    }

    /**
     * Метод конвертирует полученный из {@link MainRestController#crudService} список {@link Entity} в {@link DTOImpl}
     * и возвращет его клиенту
     * @return список всех {@link DTOImpl} из базы данных
     * */
    @GetMapping("/")
    public List<DTOImpl> getAllEntity() {
        return converterEntityDTO.convertToDTO(crudService.getAllEntity());
    }

    /**
     * Метод конвертирует {@link Entity} в {@link DTOImpl}, полученный по переданному id, и возвращает {@link DTOImpl}
     * @param id id возвращаемой сущности из БД
     * @return {@link DTOImpl} найденной сущности в БД
     * */
    @GetMapping("/{id}")
    public DTOImpl getEntity(@PathVariable int id) {
        return converterEntityDTO.convert(crudService.getEntity(id));
    }

    /**
     * Метод возвращает список {@link DTOImpl} по переданной подстроке в параметрах из сконвертированного
     * списка {@link Entity}
     * @param substring подстрока по которой будет оуществляться поиск {@link Entity} в БД
     * @return список {@link DTOImpl} найденных сущности в БД по переданной подстроке
     * */
    @GetMapping("/search?substring=substring")
    public List<DTOImpl> getEntityBySubstring(@RequestParam String substring) {
        return converterEntityDTO.convertToDTO(crudService.getEntityBySubstring(substring));
    }

}
