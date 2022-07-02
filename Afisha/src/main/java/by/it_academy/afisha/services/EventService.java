package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.api.IEventDao;
import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.mappers.EventMapper;
import by.it_academy.afisha.validators.ValidatorContainer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventService implements IAfishaService {

    private final IEventDao dao;
    private final EventMapper mapper;
    private final ValidatorContainer factory;

    public EventService(IEventDao dao, EventMapper mapper, ValidatorContainer factory) {
        this.dao = dao;
        this.mapper = mapper;
        this.factory = factory;
    }

    @Override
    public void save(EventDto dto, Type type) {
        factory.getValidatorByClass(dto).validate(dto);

        final Event event = mapper.getEvent(dto, type);

        dao.save(event);
    }

    @Override
    public void update(EventDto dto, Type type, UUID uuid, LocalDateTime dtUpdate) {
        factory.getValidatorByClass(dto).validate(dto);

        Event event = dao.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("Такого события не обнаружено: Проверьте uuid."));

        if (!event.getAction().getType().equals(type)) {
            throw new IllegalArgumentException("Не верно указан тип события.");
        }

        if (!event.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Кто-то уже успел обновить событие.");
        }

        event.setDtEvent(dto.getDtEvent());
        event.setDtEvent(dto.getDtEvent());
        event.setDtEndOfSale(dto.getDtEndOfSale());
        event.setStatus(dto.getStatus());
        event.getAction().setTitle(dto.getTitle());
        event.getAction().setDescription(dto.getDescription());

        dao.save(event);
    }

    @Override
    public List<Event> getEvents(Type type) {
        return dao.findAllByActionType(type);
    }
}
