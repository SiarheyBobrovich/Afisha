package by.it_academy.afisha;

import by.it_academy.afisha.dto.api.IEventDto;
import by.it_academy.afisha.dto.factories.EventDtoFactory;

public class ModelMapperTest {
//
//    private ModelMapper modelMapper;
//
//    private EventDtoFactory factory;
//
//    {
//        modelMapper = new ModelMapper();
//        modelMapper.addConverter(new EventFilmDtoToEventFilmModelConverter());
//    }
//
//    {
//        factory = new EventDtoFactory();
//        factory.setTitle("Harry Potter");
//        factory.setDescription("Some film");
//        factory.setDtEvent(LocalDateTime.MIN);
//        factory.setDtEndOfSale(LocalDateTime.MAX);
//        factory.setType(Type.FILMS);
//        factory.setStatus(Status.DRAFT);
//        factory.setCountry(UUID.fromString("8efab3d7-1045-4190-8a10-cda402e70940"));
//        factory.setReleaseYear(2022);
//        factory.setReleaseDate(LocalDate.of(2022, Month.JUNE, 30));
//        factory.setDuration(100);
//    }
//
//    @Test
//    public void eventFilmDtoToEventFilmEntity() {
//        IEventDto dto = factory.getDto();
//
//        EventFilm map = modelMapper.map(dto, EventFilm.class);
//
//        Assert.assertEquals(map.getDtEvent(), LocalDateTime.MIN);
//        Assert.assertEquals(map.getDtEndOfSale(), LocalDateTime.MAX);
//        Assert.assertEquals(map.getStatus(), Status.DRAFT);
//
//        Film action = map.getAction();
//
//        Assert.assertEquals(action.getCountry(), UUID.fromString("8efab3d7-1045-4190-8a10-cda402e70940"));
//        Assert.assertEquals(action.getDuration(), 100);
//        Assert.assertEquals(action.getReleaseDate(), LocalDate.of(2022, Month.JUNE, 30));
//        Assert.assertEquals(action.getReleaseYear(), 2022);
//        Assert.assertEquals(action.getTitle(), "Harry Potter");
//        Assert.assertEquals(action.getDescription(), "Some film");
//    }
//
//    @Test
//    public void updateEventFilm() {
//        IEventDto dto = factory.getDto();
//
//        EventFilm eventFilm = modelMapper.map(dto, EventFilm.class);
//
//        eventFilm.setUuid(UUID.fromString("ca678377-4756-4060-bf7d-e8a335e2884a"));
//        eventFilm.getAction().setUuid(UUID.fromString("8efab3d7-1045-4190-8a10-cda402e70940"));
//
//        factory.setCountry(UUID.randomUUID());
//        IEventDto dto1 = factory.getDto();
//
//        modelMapper.map(dto1, eventFilm);
//
//        Assert.assertNotEquals(UUID.fromString("8efab3d7-1045-4190-8a10-cda402e70940"), eventFilm.getAction().getCountry());
//        Assert.assertEquals(UUID.fromString("ca678377-4756-4060-bf7d-e8a335e2884a"), eventFilm.getUuid());
//        Assert.assertEquals(UUID.fromString("8efab3d7-1045-4190-8a10-cda402e70940"), eventFilm.getAction().getUuid());
//
//    }
}
