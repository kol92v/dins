package com.kol92v.dins.controllers;

import com.kol92v.dins.converters.ConverterEntityDTO;
import com.kol92v.dins.dao.NoteJpaRepository;
import com.kol92v.dins.dto.NoteDTO;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.services.CRUDService;
import com.kol92v.dins.services.CRUDServiceNote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Данный RestController наследуется от обобщенного класса {@link MainRestController},
 * передает ему в параметры {@link Note}, {@link NoteDTO}, {@link NoteJpaRepository} для работы с этими типами.
 * В поле {@link MainRestController#crudService} передается реализация {@link CRUDServiceNote},
 * в поле {@link MainRestController#converterEntityDTO} передается {@link com.kol92v.dins.converters.ConverterNote}
 * @see CRUDServiceNote
 * @see com.kol92v.dins.converters.ConverterNote
 * */

@RestController
@RequestMapping("/notes")
public class RESTControllerNote extends MainRestController<Note, NoteDTO, NoteJpaRepository>{

    /**
     * Поле ссылается на объект из {@link MainRestController#crudService}
     *привиденный до типа {@link CRUDServiceNote}
     * */
    private final CRUDServiceNote crudServiceNote = (CRUDServiceNote) crudService;

    public RESTControllerNote(@Qualifier("CRUDServiceNote") CRUDService<Note, NoteJpaRepository> crudService,
                              @Qualifier("converterNote") ConverterEntityDTO<Note, NoteDTO> converterEntityDTO) {
        super(crudService, converterEntityDTO);
    }

    /**
     * Метод конвертирует {@link Note} в {@link NoteDTO} и наоброт,
     * и возвращает подборку записей, сформированную сервисом по переданному ID владельца записей
     * @param ownerId ID пользователя записией, по которому необходимо вернуть список записей данного
     * пользователя
     * @return список всех записей по переданному ID пользователя
     * */
    @GetMapping("/owner")
    public List<NoteDTO> getNotesByOwnerId(@RequestParam int ownerId) {
        return converterEntityDTO.convertToDTO(crudServiceNote.getNotesByOwnerId(ownerId));
    }
}
