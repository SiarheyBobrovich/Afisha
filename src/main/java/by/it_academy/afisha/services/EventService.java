package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IConcertEventDao;
import by.it_academy.afisha.dao.api.IFilmEventDao;
import by.it_academy.afisha.dao.entity.ConcertEvent;
import by.it_academy.afisha.dao.entity.FilmEvent;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.mappers.EventMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class EventService implements IAfishaService {

    private final IFilmEventDao filmEventDao;
    private final IConcertEventDao concertEventDao;
    private final EventMapper mapper;

    public EventService(IFilmEventDao filmEventDao, IConcertEventDao concertEventDao, EventMapper mapper) {
        this.filmEventDao = filmEventDao;
        this.concertEventDao = concertEventDao;
        this.mapper = mapper;
    }

    @Override
    public void save(EventDto dto, Type type) {

        if (Type.FILMS.equals(type)) {
            filmEventDao.save(mapper.getFilmEvent(dto));

        }else if (Type.CONCERTS.equals(type)) {
            concertEventDao.save(mapper.getConcertEvent(dto));

        }else {
            throw new IllegalArgumentException();
        }
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
            filmEvent.setCurrency(event.getCurrency());
            filmEvent.getFilm().setTitle(event.getTitle());
            filmEvent.getFilm().setDescription(event.getDescription());

            filmEventDao.save(filmEvent);

        }else if (Type.CONCERTS.equals(type)) {
            ConcertEvent concertEvent = concertEventDao.findById(uuid).orElseThrow();

            if (!concertEvent.getDtUpdate().equals(dtUpdate)) {
                throw new IllegalArgumentException();
            }

            concertEvent.setDtEvent(event.getDtEvent());
            concertEvent.setDtEvent(event.getDtEvent());
            concertEvent.setDtEndOfState(event.getDtEndOfSale());
            concertEvent.setStatus(event.getStatus());
            concertEvent.setCurrency(event.getCurrency());
            concertEvent.getConcert().setTitle(event.getTitle());
            concertEvent.getConcert().setDescription(event.getDescription());

            concertEventDao.save(concertEvent);

        }else {
            throw new IllegalArgumentException("Такого типа не обнаружено");
        }
    }

    @Override
    public List<FilmEvent> getFilmEvents() {
        return filmEventDao.findAll();
    }

    @Override
    public List<ConcertEvent> getConcertEvents() {
        return concertEventDao.findAll();
    }
}
