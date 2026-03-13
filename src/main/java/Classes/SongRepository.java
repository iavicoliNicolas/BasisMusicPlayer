package Classes;

import Interfaces.Repository;

import java.util.*;

public class SongRepository implements Repository<Song, UUID> {

    private Map<UUID, Song> songs;

    public SongRepository() {
        this.songs = new HashMap<>();
    }

    @Override
    public void save(Song entity) {
        songs.put(entity.getId(), entity);
    }

    @Override
    public Optional<Song> findById(UUID uuid) {
        return Optional.ofNullable( songs.get(uuid) );
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs.values());
    }

    @Override
    public void deleteById(UUID uuid) {
        songs.remove(uuid);
    }
}
