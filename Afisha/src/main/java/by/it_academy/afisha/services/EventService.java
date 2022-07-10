package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.api.IEventFilmDao;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.Event;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.api.IEventDto;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
    public void save(@Valid IEventDto newEventDto, Type type) {
        switch (type) {
            case CONCERTS:
                EventConcert newEventConcert = mapper.map(newEventDto, EventConcert.class);

                classifiersService.isValidCategory(newEventConcert.getAction().getCategory());

                setDefaultFields(newEventConcert);

                concertDao.save(newEventConcert);
                break;

            case FILMS:
                EventFilm newEventFilm = mapper.map(newEventDto, EventFilm.class);

                classifiersService.isValidCountry(newEventFilm.getAction().getCountry());

                setDefaultFields(newEventFilm);

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

    private void setDefaultFields(Event event) {
        event.setUuid(UUID.randomUUID());
        event.getAction().setUuid(UUID.randomUUID());
        event.setDtCreate(LocalDateTime.now());
        event.setDtUpdate(event.getDtCreate());
    }
}
