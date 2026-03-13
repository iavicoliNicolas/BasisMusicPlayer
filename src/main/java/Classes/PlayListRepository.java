package Classes;

import Interfaces.Repository;

import java.util.*;

public class PlayListRepository implements Repository<PlayList, UUID> {

    private Map<UUID, PlayList> playListMap;

    public PlayListRepository() {
        playListMap = new HashMap<>();
    }

    @Override
    public void save(PlayList entity) {
        playListMap.put(entity.getId(), entity);
    }

    @Override
    public Optional<PlayList> findById(UUID uuid) {
        return Optional.ofNullable( playListMap.get(uuid) );
    }

    @Override
    public List<PlayList> findAll() {
        return new ArrayList<>(playListMap.values());
    }

    @Override
    public void deleteById(UUID uuid) {
        playListMap.remove(uuid);
    }
}
