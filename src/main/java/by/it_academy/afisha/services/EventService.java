package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IAbstractDao;
import by.it_academy.afisha.dao.api.IConcertEventDao;
import by.it_academy.afisha.dao.api.IFilmEventDao;
import by.it_academy.afisha.dao.entity.AbstractEvent;
import by.it_academy.afisha.dao.entity.ConcertEvent;
import by.it_academy.afisha.dao.entity.FilmEvent;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.mappers.EventMapper;
import by.it_academy.afisha.validators.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventService implements IAfishaService {

    private final IAbstractDao abstractDao;
    private final IConcertEventDao concertEventDao;
    private final IFilmEventDao filmEventDao;
    private final EventMapper mapper;
    private final ValidatorFactory factory;

    public EventService(IAbstractDao abstractDao, IConcertEventDao concertEventDao,
                        IFilmEventDao filmEventDao, EventMapper mapper, ValidatorFactory factory) {
        this.abstractDao = abstractDao;
        this.concertEventDao = concertEventDao;
        this.filmEventDao = filmEventDao;
        this.mapper = mapper;
        this.factory = factory;
    }

    @Override
    public void save(EventDto dto, Type type) {
        factory.getValidatorByClass(dto).validate(dto);

        AbstractEvent abstractEvent = mapper.getAbstractEvent(dto, type);
        abstractDao.save(abstractEvent);
    }

    @Override
    public void update(EventDto event, Type type, UUID uuid, LocalDateTime dtUpdate) {
        if (Type.FILMS.equals(type)) {
            Optional<FilmEvent> optional = filmEventDao.findById(uuid);

            FilmEvent filmEvent = optional.orElseThrow();

            if (!filmEvent.getDtUpdate().equals(dtUpdate)) {
                throw new IllegalArgumentException();
            }

            filmEvent.setDtEvent(event.getDtEvent());
            filmEvent.setDtEvent(event.getDtEvent());
            filmEvent.setDtEndOfState(event.getDtEndOfSale());
            filmEvent.setStatus(event.getStatus());
            filmEvent.getFilm().setTitle(event.getTitle());
            filmEvent.getFilm().setDescription(event.getDescription());

            abstractDao.save(filmEvent);

        }else if (Type.CONCERTS.equals(type)) {
            ConcertEvent concertEvent = concertEventDao.findById(uuid).orElseThrow();

            if (!concertEvent.getDtUpdate().equals(dtUpdate)) {
                throw new IllegalArgumentException();
            }

            concertEvent.setDtEvent(event.getDtEvent());
            concertEvent.setDtEvent(event.getDtEvent());
            concertEvent.setDtEndOfState(event.getDtEndOfSale());
            concertEvent.setStatus(event.getStatus());
            concertEvent.getConcert().setTitle(event.getTitle());
            concertEvent.getConcert().setDescription(event.getDescription());

            abstractDao.save(concertEvent);

        }else {
            throw new IllegalArgumentException("Такого типа не обнаружено");
        }
    }

    @Override
    public List<AbstractEvent> getEvents(Type type) {

        if (Type.FILMS.equals(type)) {
            return new ArrayList<>(filmEventDao.findAll()).stream()
                    .filter(x -> x.getFilm() != null)
                    .collect(Collectors.toList());

        }else if (Type.CONCERTS.equals(type)){
            return new ArrayList<>(concertEventDao.findAll())
                    .stream()
                    .filter(x -> x.getConcert() != null)
                    .collect(Collectors.toList());

        }else {
            throw new IllegalArgumentException();
        }
    }
}
