package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.api.IEventFilmDao;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.api.IEventDto;
import by.it_academy.afisha.exceptions.ValidationException;
import by.it_academy.afisha.services.api.IAfishaService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@Validated
public class EventService implements IAfishaService {

    private final IEvenConcertDao concertDao;
    private final IEventFilmDao filmDao;
    private final ModelMapper mapper;

    private final Validator validator;

    public EventService(IEvenConcertDao concertDao, IEventFilmDao filmDao, ModelMapper mapper, Validator validator) {
        this.concertDao = concertDao;
        this.filmDao = filmDao;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void save(@Valid IEventDto newEventDto, Type type) {
//        Set<ConstraintViolation<IEventDto>> validate = validator.validate(newEventDto);
//
//        if (!validate.isEmpty()) {
//            Map<String, String> invalidFields = new HashMap<>(validate.size());
//
//            validate.forEach(x -> invalidFields.put(x.getPropertyPath().toString(), x.getMessage()));
//
//            throw new ValidationException(invalidFields);
//        }

        switch (type) {
            case CONCERTS:
                EventConcert newEventConcert = mapper.map(newEventDto, EventConcert.class);

                newEventConcert.setUuid(UUID.randomUUID());
                newEventConcert.getConcert().setUuid(UUID.randomUUID());
                newEventConcert.setDtCreate(LocalDateTime.now());
                newEventConcert.setDtUpdate(newEventConcert.getDtCreate());

                concertDao.save(newEventConcert);
                break;

            case FILMS:
                EventFilm newEventFilm = mapper.map(newEventDto, EventFilm.class);

                newEventFilm.setUuid(UUID.randomUUID());
                newEventFilm.getFilm().setUuid(UUID.randomUUID());
                newEventFilm.setDtCreate(LocalDateTime.now());
                newEventFilm.setDtUpdate(newEventFilm.getDtCreate());

                filmDao.save(newEventFilm);
                break;

            default:
                throw new IllegalArgumentException("Тип не обслуживается");
        }
    }

    @Override
    public void update(IEventDto updateSource, Type type, UUID uuid, LocalDateTime dtUpdate) {
       mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        switch (type) {
            case CONCERTS:
                EventConcert eventConcert = concertDao.findById(uuid).orElseThrow(() ->
                        new IllegalArgumentException("Такого события не обнаружено: Проверьте uuid.")
                );

                if (!eventConcert.getDtUpdate().equals(dtUpdate)) {
                   throw new IllegalStateException("Кто-то уже успел обновить событие.");
                }

                mapper.map(updateSource, eventConcert);
                concertDao.save(eventConcert);
                break;

            case FILMS:
                EventFilm eventFilm = filmDao.findById(uuid).orElseThrow(() ->
                        new IllegalArgumentException("Такого события не обнаружено: Проверьте uuid.")
                );

                if (!eventFilm.getDtUpdate().equals(dtUpdate)) {
                    throw new IllegalStateException("Кто-то уже успел обновить событие.");
                }

                mapper.map(updateSource, eventFilm);
                filmDao.save(eventFilm);
                break;

            default:
                throw new IllegalArgumentException("Тип не обслуживается");
        }

    }

    @Override
    public Page<EventFilm> getFilmEvents(Type type, Pageable page) {
        return filmDao.findAllByFilmType(type, page);
    }

    @Override
    public Page<EventConcert> getConcertEvents(Type type, Pageable pageable) {
        return concertDao.findAllByConcertType(type, pageable);
    }
}
