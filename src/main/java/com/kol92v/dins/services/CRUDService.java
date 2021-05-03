package com.kol92v.dins.services;

import com.kol92v.dins.converters.ConverterEntityDTO;
import com.kol92v.dins.dto.DTObj;
import com.kol92v.dins.entity.EntityDB;
import com.kol92v.dins.exceptionhandling.exception.EmptyStringException;
import com.kol92v.dins.exceptionhandling.exception.GetEntityException;
import com.kol92v.dins.exceptionhandling.exception.SaveEntityException;
import com.kol92v.dins.exceptionhandling.exception.UpdateEntityException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**Это абстрактный обобщенный класс, от которого сервисы могут унаследовать основную логику обработки запросов.
 * Данный класс работает с объектами типа {@link EntityDB} и {@link JpaRepository}.
 * Все запросы к БД обрабатываются объектами типа {@link JpaRepository}, который так же работает с переданными ему типами.
 * @param <Entity> Entity типа {@link EntityDB}
 * @param <Repository> DAO реализующий интерфейс {@link JpaRepository} и работающий с сущностями {@link Entity}
 *                    и ID типа {@link Integer}
 * @see EntityDB
 * @see JpaRepository
 * @see EntitySubstringFinder
 * */
@AllArgsConstructor
public abstract class CRUDService<Entity extends EntityDB, Repository extends JpaRepository<Entity, Integer>> {

    /**
     * DAO работающий с сущностями типа {@link Entity} и их ID типа {@link Integer}
     * */
    protected final Repository repository;

    /**
     * Метод проверет существует ли сущность в БД, полученная из параетра метода
     * @param entity проверяемая сущность типа {@link Entity}
     * @return возвращает true если сущность представлена в базе, иначе false
     * */
    protected boolean isPresentEntityInBase(Entity entity) {
        return repository.findById(entity.getId()).isPresent();
    }

    /**
     *Метод сохраняет сущность из параметров метода в базу данных
     * @param entity сущность которую требуется сохранить
     * @return сохраненный объект
     * @throws SaveEntityException при попытки сохранить уже существующую сущность в БД
     * */
    public Entity save(Entity entity) {
        if (isPresentEntityInBase(entity)) {
            throw new SaveEntityException(entity);
        } else {
            return repository.save(entity);
        }
    }

    /**
     *Метод обновлет сущность из параметров метода в базу данных
     * @param entity сущность которую требуется обновить
     * @return обновленный объект
     * @throws UpdateEntityException при попытки обновить сущность которая не представлена в БД
     * */
    public Entity update(Entity entity) {
        if (!isPresentEntityInBase(entity)) {
            throw new UpdateEntityException(entity);
        } else {
            return repository.save(entity);
        }
    }

    /**
     *Метод удаляет сущность по переданному id из параметров метода в базе данных
     * @param id ID сущности которую требуется удалить
     * */
    public void delete(int id) {
        repository.deleteById(id);
    }

    /**
     *Метод возвращает список всех сущностей из БД
     * @return список всех представленных объект в БД
     */
    public List<Entity> getAllEntity() {
        return repository.findAll();
    }

    /**
     *Метод возвращает сущность из БД, по id полученного из параметров метода
     * @param id id ID сущности которую требуется вернуть
     * @return объект из БД полученный по id
     * @throws GetEntityException при попытки получить не существующую сущность в БД
     * */
    public Entity getEntity(int id) {
        return repository.findById(id).orElseThrow(() -> new GetEntityException(id));
    }

    /**
     *В данном методе реализована логика проверки правильности запроса поиска сущности по подстроке.
     * Метод принимает два параметра.
     * @param substring подстрока по которой будет осущетсвляться поиск
     * @param entitySubstringFinder реализация функционального итерфейса типа {@link EntitySubstringFinder},
     *                              в методе которого прописана логика запроса к БД
     * */
    protected List<Entity> getEntityBySubstringFunctional(String substring, EntitySubstringFinder<Entity> entitySubstringFinder) {
        if (substring.isEmpty() || isSubstringBlank(substring)) throw new EmptyStringException();
        List<Entity> entityBySubstring = entitySubstringFinder.getEntityBySubstring(substring);
        if (entityBySubstring.size() == 0) throw new GetEntityException(substring);
        return entityBySubstring;
    }

    /**
     *Метод проверяет содержит ли переданная в параметрах строка только пробелы
     * @param substring строка для проверки
     * @return возвращает true, если строка содержит только пробелы, иначе false
     * */
    private boolean isSubstringBlank(String substring) {
        for (char c : substring.toCharArray()) if (c != ' ') return false;
        return true;
    }

    /**
     *При реализации данного метода необходимо использовать
     * метод {@link CRUDService#getEntityBySubstringFunctional(String, EntitySubstringFinder)}.
     * Во второй параметр которого передать объект реализующий логику запроса к самой БД,
     * а в первый строку из параметра нашего метода.
     * @param substring подстрока по которой будет осущствляться поиск сущностей в БД
     * @return список сущностей из БД, у которых есть вхождения подстроки, из параметра метода,
     * в выбранном поле, по которому должен осуществляться поиск в БД.
     * */
    abstract public List<Entity> getEntityBySubstring(String substring);
}
