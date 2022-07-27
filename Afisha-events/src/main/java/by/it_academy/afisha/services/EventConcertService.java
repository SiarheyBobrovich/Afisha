package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dto.*;
import by.it_academy.afisha.exceptions.ConcertNotFoundException;
import by.it_academy.afisha.exceptions.InvalidVersionException;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import by.it_academy.afisha.services.api.IEventService;
import org.modelmapper.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
@Transactional(readOnly = true)
public class EventConcertService implements IEventService<EventConcertDto, EventConcert> {

    private final IEvenConcertDao concertDao;
    private final ModelMapper mapper;
    private final IClassifiersConnectService classifiersService;

    private final UserHolder userHolder;

    public EventConcertService(IEvenConcertDao concertDao,
                               ModelMapper mapper,
                               ClassifiersService classifiersService,
                               UserHolder userHolder) {
        this.concertDao = concertDao;
        this.mapper = mapper;
        this.classifiersService = classifiersService;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional
    public void save(EventConcertDto eventConcertDto, String author) {
        EventConcert newEventConcert = mapper.map(eventConcertDto, EventConcert.class);

        newEventConcert.setAuthor(author);

        classifiersService.isValidCategory(newEventConcert.getAction().getCategory());

        setDefaultFields(newEventConcert);

        concertDao.save(newEventConcert);
    }

    @Override
    @Transactional
    public void update(EventConcertDto updateSource,
                       UUID uuid,
                       LocalDateTime dtUpdate,
                       String author) {
        EventConcert eventConcert = concertDao.findByUuidAndAuthor(updateSource.getType(), uuid, author).orElseThrow(ConcertNotFoundException::new);

        classifiersService.isValidCategory(updateSource.getCategory());


        if (!eventConcert.getDtUpdate().equals(dtUpdate)) {
            throw new InvalidVersionException();
        }

        mapper.map(updateSource, eventConcert);

        concertDao.save(eventConcert);
    }

    @Override
    public Page<EventConcert> getAllEvents(Type type, Pageable page) {
        UserDto userInfo = userHolder.getUser();
        Page<EventConcert> eventConcertsPage;

        if (userInfo == null) {
            eventConcertsPage = concertDao.findByConcertTypeAndStatus(type, Status.PUBLISHED, page);

        }else if (userInfo.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventConcertsPage = concertDao.findAllConcertsType(type, page);

        }else {
            eventConcertsPage = concertDao.findByConcertTypeAndStatusAndAuthor(type, Status.PUBLISHED, userInfo.getUsername(), page);
        }

        return eventConcertsPage;
    }

    @Override
    public EventConcert getSingleEvent(Type type, UUID uuid) {
        UserDto user = userHolder.getUser();
        EventConcert eventConcert;

        if (user == null) {
            eventConcert = concertDao.findByUuidAndAndStatus(uuid, type, Status.PUBLISHED)
                    .orElseThrow(ConcertNotFoundException::new);

        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            eventConcert = concertDao.findByUuid(type, uuid)
                    .orElseThrow(ConcertNotFoundException::new);

        }else {
            eventConcert = concertDao.findByUuidAndStatusOrAuthor(uuid, type, Status.PUBLISHED, user.getUsername())
                    .orElseThrow(ConcertNotFoundException::new);
        }

        return eventConcert;
    }
}
