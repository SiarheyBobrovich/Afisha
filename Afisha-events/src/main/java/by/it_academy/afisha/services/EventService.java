package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.api.IEventFilmDao;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.Event;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.*;
import by.it_academy.afisha.exceptions.EntityNotFoundException;
import by.it_academy.afisha.exceptions.InvalidVersionException;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.modelmapper.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
public class EventService implements IAfishaService {

    private final IEvenConcertDao concertDao;
    private final IEventFilmDao filmDao;
    private final ModelMapper mapper;
    private final IClassifiersConnectService classifiersService;

    public EventService(IEvenConcertDao concertDao, IEventFilmDao filmDao, ModelMapper mapper, ClassifiersService classifiersService) {
        this.concertDao = concertDao;
        this.filmDao = filmDao;
        this.mapper = mapper;
        this.classifiersService = classifiersService;
    }

    @Override
    public void save(EventFilmDto eventFilmDto) {
        EventFilm newEventFilm = mapper.map(eventFilmDto, EventFilm.class);

        classifiersService.isValidCountry(newEventFilm.getAction().getCountry());

        setDefaultFields(newEventFilm);

        filmDao.save(newEventFilm);
    }

    @Override
    public void save(EventConcertDto eventConcertDto) {

        EventConcert newEventConcert = mapper.map(eventConcertDto, EventConcert.class);

        classifiersService.isValidCategory(newEventConcert.getAction().getCategory());

        setDefaultFields(newEventConcert);

        concertDao.save(newEventConcert);
    }

    @Override
    public void update(EventFilmDto updateSource, UUID uuid, LocalDateTime dtUpdate) {
        classifiersService.isValidCountry(updateSource.getCountry());

        EventFilm eventFilm = filmDao.findById(uuid).orElseThrow(() ->
                new EntityNotFoundException(uuid, "Фильма не обнаружено: Проверьте uuid.")
        );

        if (!eventFilm.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        mapper.map(updateSource, eventFilm);

        filmDao.save(eventFilm);
    }

    @Override
    public void update(EventConcertDto updateSource, UUID uuid, LocalDateTime dtUpdate) {
        classifiersService.isValidCategory(updateSource.getCategory());

        EventConcert eventConcert = concertDao.findById(uuid).orElseThrow(() ->
                new EntityNotFoundException(uuid, "Концерта не обнаружено: Проверьте uuid.")
        );

        if (!eventConcert.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        mapper.map(updateSource, eventConcert);

        concertDao.save(eventConcert);
    }

    @Override
    public PageDtos<PageEventDto> getFilmEvents(Pageable page) {

        return new PageDtos<>(
                filmDao.findAllByFilmType(Type.FILMS, page)
                        .map(entity -> mapper.map(entity, PageEventDto.class))
        );
    }

    @Override
    public PageDtos<PageEventDto> getConcertEvents(Pageable page) {
        return new PageDtos<>(
                concertDao.findAllByConcertType(Type.CONCERTS, page)
                        .map(entity -> mapper.map(entity, PageEventDto.class))
        );
    }

    /**
     * Method for generating UUID and date for new entities
     * @param event a new entities
     */
    private void setDefaultFields(Event event) {
        event.setUuid(UUID.randomUUID());
        event.getAction().setUuid(UUID.randomUUID());
        event.setDtCreate(LocalDateTime.now());
        event.setDtUpdate(event.getDtCreate());
    }
}
