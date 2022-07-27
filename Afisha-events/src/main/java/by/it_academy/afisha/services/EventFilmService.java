package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEventFilmDao;
import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.EventFilmDto;
import by.it_academy.afisha.dto.UserDto;
import by.it_academy.afisha.exceptions.FilmNotFoundException;
import by.it_academy.afisha.exceptions.InvalidVersionException;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import by.it_academy.afisha.services.api.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Validated
@Transactional(readOnly = true)
public class EventFilmService implements IEventService<EventFilmDto, EventFilm> {

    private final IEventFilmDao filmDao;
    private final ModelMapper mapper;
    private final IClassifiersConnectService classifiersService;
    private final UserHolder userHolder;

    public EventFilmService(IEventFilmDao filmDao,
                            ModelMapper mapper,
                            IClassifiersConnectService classifiersService,
                            UserHolder userHolder) {
        this.filmDao = filmDao;
        this.mapper = mapper;
        this.classifiersService = classifiersService;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional
    public void save(EventFilmDto eventFilmDto, String author) {
        EventFilm newEventFilm = mapper.map(eventFilmDto, EventFilm.class);

        newEventFilm.setAuthor(author);

        classifiersService.isValidCountry(newEventFilm.getAction().getCountry());

        setDefaultFields(newEventFilm);

        filmDao.save(newEventFilm);
    }

    @Override
    @Transactional
    public void update(EventFilmDto updateSource,
                       UUID uuid,
                       LocalDateTime dtUpdate,
                       String author) {
        EventFilm eventFilm = filmDao.findByFilmTypeAndUuidAndAuthor(updateSource.getType(), uuid, author).orElseThrow(FilmNotFoundException::new);

        if (!eventFilm.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        classifiersService.isValidCountry(updateSource.getCountry());

        mapper.map(updateSource, eventFilm);

        filmDao.save(eventFilm);
    }

    @Override
    public Page<EventFilm> getAllEvents(Type type, Pageable page) {
        UserDto userInfo = userHolder.getUser();
        Page<EventFilm> eventFilmsPage;

        if (Objects.isNull(userInfo)) {
            eventFilmsPage = filmDao.findByFilmTypeAndStatus(type, Status.PUBLISHED, page);

        } else if (userInfo.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventFilmsPage = filmDao.findAllFilms(type, page);

        } else {
            eventFilmsPage = filmDao.findByStatusOrAuthor(type, Status.PUBLISHED, userInfo.getUsername(), page);
        }

        return eventFilmsPage;
    }

    @Override
    public EventFilm getSingleEvent(Type type, UUID uuid) {
        UserDto user = userHolder.getUser();
        EventFilm eventFilm;

        if (user == null) {
            eventFilm = filmDao.findByFilmTypeAndUuidAndStatus(type, uuid, Status.PUBLISHED)
                    .orElseThrow(FilmNotFoundException::new);

        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventFilm = filmDao.findByFilmTypeAndUuid(type, uuid)
                    .orElseThrow(FilmNotFoundException::new);
        }else {
            eventFilm = filmDao.findByFilmTypeAndFilmUuidAndStatusOrAuthor(type, uuid,Status.PUBLISHED, user.getUsername())
                    .orElseThrow(FilmNotFoundException::new);
        }

        return eventFilm;
    }
}
