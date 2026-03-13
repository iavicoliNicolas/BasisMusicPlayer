package Exceptions;

import java.util.UUID;

public class SongNotFoundException extends EntityNotFoundException {
    public SongNotFoundException(UUID id) {
        super("Song not found with id: " + id);
    }
}