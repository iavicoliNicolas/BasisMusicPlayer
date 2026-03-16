package Classes;

import Exceptions.EntityNotFoundException;
import Exceptions.PlaylistNotFoundException;
import Exceptions.SongNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayListService {
    private PlayListRepository playLists;
    private SongService songService;

    public PlayListService() {
        this.playLists = new PlayListRepository();
        this.songService = new SongService();
    }

    /*
    - Crear una nueva playlist
    - Listar todas las playlists
    - Agregar una canción a una playlist
    - Quitar una canción de una playlist
    - Mostrar las canciones de una playlist con su duración total
    - Eliminar una playlist
     */

    public void createPlayList(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Playlist name cannot be empty");
        PlayList playList = new PlayList(UUID.randomUUID(), name);
        playLists.save(playList);
    }

    public List<PlayList> listPlaylists() {
        String message = "Empty repositoy";
        if ( playLists == null )
            throw new EntityNotFoundException(message);
        return playLists.findAll();
    }

    public void addSongToPlayList(UUID playlist, UUID songid) {
        PlayList playList = playLists.findById(playlist)
                .orElseThrow(() -> new PlaylistNotFoundException(playlist));

        Song song = songService.getSongById(songid)
                .orElseThrow(() -> new SongNotFoundException(songid));

        playList.addSong(song);
    }

    public void removeSongOfPlayList(UUID playListID, UUID songid) {
        PlayList playList = playLists.findById(playListID).orElseThrow(() -> new PlaylistNotFoundException(playListID));

        Song song = songService.getSongById(songid).orElseThrow(() -> new SongNotFoundException(songid));

        playList.removeSong(song);
    }
}
