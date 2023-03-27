package dea.spotitube.mappers;

import dea.spotitube.data.mappers.PlaylistMapper;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistsMapperTest {
    private PlaylistMapper sut;
    private ResultSet resultSetMock;

    @BeforeEach
    void setup() {
        sut = new PlaylistMapper();
        resultSetMock = mock(ResultSet.class);
    }

    @Test
    void mapperShouldReturnRightValuesIfTheUserIsOwner() throws SQLException {
        var expected = new PlaylistDTO(1, "name", true);
        when(resultSetMock.next()).thenReturn(true, false);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("name");
        when(resultSetMock.getString("fullname")).thenReturn("Luuk");

        var actual = sut.toMapper(resultSetMock);

        assertEquals(expected.getId(), actual.get(0).getId());
        assertEquals(expected.getName(), actual.get(0).getName());
        assertEquals(expected.getOwner(), actual.get(0).getOwner());
    }

    @Test
    void mapperShouldReturnRightValuesIfTheUserNotOwner() throws SQLException {
        var expected = new PlaylistDTO(1, "name", false);
        when(resultSetMock.next()).thenReturn(true, false);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("name");
        when(resultSetMock.getString("fullname")).thenReturn(null);

        var actual = sut.toMapper(resultSetMock);

        assertEquals(expected.getId(), actual.get(0).getId());
        assertEquals(expected.getName(), actual.get(0).getName());
        assertEquals(expected.getOwner(), actual.get(0).getOwner());
    }
}
