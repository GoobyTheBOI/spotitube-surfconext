package dea.spotitube.business;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class StringEncoder {

    public String stringToHash(String input) {
        // convert input to 256 hex
        return Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
    }
}
