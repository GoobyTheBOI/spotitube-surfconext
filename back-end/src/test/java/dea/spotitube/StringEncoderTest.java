package dea.spotitube;

import dea.spotitube.business.StringEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEncoderTest {
    private StringEncoder sut;

    @BeforeEach
    public void setup() {
        sut = new StringEncoder();
    }

    @Test
    void stringToHash() {
        final String input = "password";
        final String expected = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        var actual = sut.stringToHash(input);
        assertEquals(expected, actual);
    }
}
