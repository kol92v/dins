package com.kol92v.dins.services;

import com.kol92v.dins.dao.NoteJpaRepository;
import com.kol92v.dins.dao.UserJpaRepository;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.exceptionhandling.exception.GetEntityException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CRUDServiceNote extends CRUDService<Note, NoteJpaRepository> {
    protected UserJpaRepository userJpaRepository;

    public CRUDServiceNote(NoteJpaRepository repository, UserJpaRepository userJpaRepository) {
        super(repository);
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<Note> getEntityBySubstring(String substring) {
        return getEntityBySubstringFunctional(substring, repository::findByPhoneNumberContains);
    }

    public List<Note> getNotesByOwnerId(int ownerId) {
        if (!isPresentOwnerNotesInBase(ownerId)) throw new GetEntityException(ownerId);
        return repository.findByUser_Id(ownerId);
    }

    protected boolean isPresentOwnerNotesInBase(int ownerId) {
        return userJpaRepository.findById(ownerId).isPresent();
    }
}
