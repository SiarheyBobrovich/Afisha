package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.api.IEventFilmDao;
import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.Event;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.*;
import by.it_academy.afisha.exceptions.CategoryNotFoundException;
import by.it_academy.afisha.exceptions.EntityNotFoundException;
import by.it_academy.afisha.exceptions.InvalidVersionException;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.modelmapper.*;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Validated
public class EventService implements IAfishaService {

    private final IEvenConcertDao concertDao;
    private final IEventFilmDao filmDao;
    private final ModelMapper mapper;
    private final IClassifiersConnectService classifiersService;

    private final UserHolder userHolder;

    public EventService(IEvenConcertDao concertDao,
                        IEventFilmDao filmDao,
                        ModelMapper mapper,
                        ClassifiersService classifiersService,
                        UserHolder userHolder) {
        this.concertDao = concertDao;
        this.filmDao = filmDao;
        this.mapper = mapper;
        this.classifiersService = classifiersService;
        this.userHolder = userHolder;
    }

    @Override
    public void save(EventFilmDto eventFilmDto, String author) {
        EventFilm newEventFilm = mapper.map(eventFilmDto, EventFilm.class);

        newEventFilm.setAuthor(author);

        classifiersService.isValidCountry(newEventFilm.getAction().getCountry());

        setDefaultFields(newEventFilm);

        filmDao.save(newEventFilm);
    }

    @Override
    public void save(EventConcertDto eventConcertDto, String author) {
        EventConcert newEventConcert = mapper.map(eventConcertDto, EventConcert.class);

        newEventConcert.setAuthor(author);

        classifiersService.isValidCategory(newEventConcert.getAction().getCategory());

        setDefaultFields(newEventConcert);

        concertDao.save(newEventConcert);
    }

    @Override
    public void update(EventFilmDto updateSource,
                       UUID uuid,
                       LocalDateTime dtUpdate,
                       String author) {
        EventFilm eventFilm = filmDao.findByUuidAndAuthor(uuid, author).orElseThrow(() ->
                new EntityNotFoundException(uuid, "Фильма не обнаружено: Проверьте uuid.")
        );

        if (!eventFilm.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        classifiersService.isValidCountry(updateSource.getCountry());

        mapper.map(updateSource, eventFilm);

        filmDao.save(eventFilm);
    }

    @Override
    public void update(EventConcertDto updateSource,
                       UUID uuid,
                       LocalDateTime dtUpdate,
                       String author) {
        EventConcert eventConcert = concertDao.findByUuidAndAuthor(uuid, author).orElseThrow(() ->
                new EntityNotFoundException(uuid, "Концерта не обнаружено: Проверьте uuid.")
        );

        if (!classifiersService.isValidCategory(updateSource.getCategory())) {
            throw new CategoryNotFoundException();
        }


        if (!eventConcert.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        mapper.map(updateSource, eventConcert);

        concertDao.save(eventConcert);
    }

    @Override
    public PageDtos<PageEventDto> getEventFilms(Pageable page) {
        UserDto userInfo = userHolder.getUser();
        PageDtos<PageEventDto> eventFilmsPage;

        if (Objects.isNull(userInfo)) {
            eventFilmsPage = new PageDtos<>(filmDao.findByFilmTypeAndStatus(Type.FILMS, Status.PUBLISHED, page)
                    .map(eventFilm -> mapper.map(eventFilm, PageEventDto.class)));

        } else if (userInfo.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventFilmsPage = new PageDtos<>(filmDao.findAllByFilmType(Type.FILMS, page)
                    .map(eventFilm -> mapper.map(eventFilm, PageEventDto.class)));

        } else {
            eventFilmsPage = new PageDtos<>(
                    filmDao.findByFilmTypeAndStatusOrAuthor(Type.FILMS, Status.PUBLISHED, userInfo.getUsername(), page)
                            .map(entity -> mapper.map(entity, PageEventDto.class)));
        }

        return eventFilmsPage;
    }

    @Override
    public PageDtos<PageEventDto> getEventConcerts(Pageable page) {
        UserDto userInfo = userHolder.getUser();
        PageDtos<PageEventDto> eventConcertsPage;

        if (userInfo == null) {
            eventConcertsPage = new PageDtos<>(
                    concertDao.findByConcertTypeAndStatus(Type.CONCERTS, Status.PUBLISHED, page)
                            .map(entity -> mapper.map(entity, PageEventDto.class)));

        }else if (userInfo.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventConcertsPage = new PageDtos<>(
                    concertDao.findAllByConcertType(Type.CONCERTS, page)
                            .map(entity -> mapper.map(entity, PageEventDto.class)));

        }else {
            eventConcertsPage = new PageDtos<>(
                    concertDao.findByConcertTypeAndStatusAndAuthor(Type.CONCERTS, Status.PUBLISHED, userInfo.getUsername(), page)
                            .map(entity -> mapper.map(entity, PageEventDto.class)));
        }

        return eventConcertsPage;
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
