package Exceptions;

import java.util.UUID;

public class PlaylistNotFoundException extends EntityNotFoundException {
    public PlaylistNotFoundException(UUID id) {
        super("Playlist not found with id: " + id);
    }
}