package dea.spotitube.mappers;

import dea.spotitube.data.mappers.TrackMapper;
import dea.spotitube.CCC.DTO.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackMapperTest {
    private TrackMapper sut;
    private ResultSet resultSetMock;

    @BeforeEach
    void setup() {
        sut = new TrackMapper();
        resultSetMock = mock(ResultSet.class);
    }

    @Test
    void mapperShouldReturnRightValues() throws SQLException {
        var expected = new TrackDTO(1, "title", "performer", 1, "duration", 1, "publicationDate", "description", true);
        when(resultSetMock.next()).thenReturn(true, false);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("title")).thenReturn("title");
        when(resultSetMock.getString("performer")).thenReturn("performer");
        when(resultSetMock.getString("album")).thenReturn("duration");
        when(resultSetMock.getInt("duration")).thenReturn(1);
        when(resultSetMock.getInt("playcount")).thenReturn(1);
        when(resultSetMock.getString("publicationDate")).thenReturn("publicationDate");
        when(resultSetMock.getString("description")).thenReturn("description");
        when(resultSetMock.getBoolean("offlineAvailable")).thenReturn(true);

        var actual = sut.toMapper(resultSetMock);

        assertEquals(expected.getDuration(), actual.get(0).getDuration());
        assertEquals(expected.isOfflineAvailable(), actual.get(0).isOfflineAvailable());
        assertEquals(expected.getPublicationDate(), actual.get(0).getPublicationDate());
        assertEquals(expected.getDescription(), actual.get(0).getDescription());
        assertEquals(expected.getPlaycount(), actual.get(0).getPlaycount());
        assertEquals(expected.getAlbum(), actual.get(0).getAlbum());
        assertEquals(expected.getPerformer(), actual.get(0).getPerformer());
        assertEquals(expected.getTitle(), actual.get(0).getTitle());
        assertEquals(expected.getId(), actual.get(0).getId());
    }
}
