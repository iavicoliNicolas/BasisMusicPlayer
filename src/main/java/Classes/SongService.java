package Classes;

import Enums.Genre;
import Exceptions.SongNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SongService {
    private final SongRepository songRepository;

    public SongService() {

        this.songRepository = new SongRepository();
    }


    public void createNewSong(String name, String artist, int durationSeconds, Genre genre) {

        if ( artist.isBlank() ) {
            throw new IllegalArgumentException("\"Song artist cannot be empty\")");
        }
        if ( durationSeconds <= 0 ) {
            throw new IllegalArgumentException("Song duration must be greater than 0");
        }
        songRepository.save( new Song(name, artist, durationSeconds, genre) );
    }

    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> getSongById(UUID id){
        Optional<Song> song = songRepository.findById(id);
        if ( song.isEmpty() ) {
            throw new SongNotFoundException(id);
        }
        return song;
    }

    public void removeSongById(UUID id) {
        getSongById(id); // tira SongNotFoundException si no existe
        songRepository.deleteById(id);
    }


}
