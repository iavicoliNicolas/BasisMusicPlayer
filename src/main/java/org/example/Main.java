package org.example;

import Classes.PlayList;
import Classes.PlayListService;
import Classes.Song;
import Classes.SongService;
import Enums.Genre;
import Exceptions.PlaylistNotFoundException;
import Exceptions.SongNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);
        SongService songService = new SongService();
        PlayListService playListService = new PlayListService(songService);
        int option;

        do {
            System.out.println("========================================");
            System.out.println("    GESTOR DE PLAYLISTS MUSICALES    ");
            System.out.println("========================================");

            System.out.println("1. Gestión de Canciones\n" +
                    "2. Gestión de Playlists\n" +
                    "0. Salir\n" +
                    "Seleccione una opción: _");
            option = sc1.nextInt();
            switch (option) {
                case 1:
                    menuCanciones(sc1, songService);
                    break;
                case 2:
                    menuPlaylists(sc1, playListService);
                    break;
                case 0:
                    System.out.println("Finalizacion");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (option != 0);
    }

    public static void menuCanciones(Scanner sc, SongService songService) {

        int option;
        String name;
        String artist;
        int duration;
        Genre genre = null;

        do {
            System.out.println("--- Submenú Canciones ---\n" +
                    "1. Crear canción\n" +
                    "2. Listar canciones\n" +
                    "3. Buscar canción por ID\n" +
                    "4. Eliminar canción\n" +
                    "0. Volver\n" +
                    "Seleccione una opción: _\n");
            option = sc.nextInt(); sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear cancion");
                    System.out.println("Introducir nombre");
                    name = sc.nextLine();
                    System.out.println("Introducir artista");
                    artist = sc.nextLine();
                    System.out.println("introducir duracion (en sergundos)");
                    duration = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Seleccione género:\n" +
                            "1. ROCK\n" +
                            "2. POP\n" +
                            "3. ELECTRONIC\n" +
                            "4. HIP_HOP\n" +
                            "5. JAZZ\n" +
                            "6. CLASSICAL\n" +
                            "7. REGGAETON\n" +
                            "8. OTHER");
                    int genreOption = sc.nextInt(); sc.nextLine(); //para no comerse el /n
                    switch (genreOption) {
                        case 1: genre = Genre.ROCK; break;
                        case 2: genre = Genre.POP; break;
                        case 3: genre = Genre.ELECTRONIC; break;
                        case 4: genre = Genre.HIP_HOP; break;
                        case 5: genre = Genre.JAZZ; break;
                        case 6: genre = Genre.CLASSICAL; break;
                        case 7: genre = Genre.REGGAETON; break;
                        case 8: genre = Genre.OTHER; break;
                    }
                    songService.createNewSong(name,artist,duration,genre);
                    break;
                case 2:
                    List<Song> songs = songService.listSongs();
                    if (songs.isEmpty()) {
                        System.out.println("No hay canciones cargadas");
                    } else {
                        for (Song s : songs) {
                            System.out.println(s.toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("=== BUSCAR CANCIÓN ===");
                    System.out.println("Ingrese el ID de la canción:");
                    String idStr = sc.nextLine();
                    try {
                        UUID songId = UUID.fromString(idStr);
                        Optional<Song> found = songService.getSongById(songId);
                        System.out.println(found.get().toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (SongNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("=== ELIMINAR CANCIÓN ===");
                    System.out.println("Ingrese el ID de la canción:");
                    String deleteIdStr = sc.nextLine();
                    try {
                        UUID deleteId = UUID.fromString(deleteIdStr);
                        songService.removeSongById(deleteId);
                        System.out.println("✓ Canción eliminada exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (SongNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
                default:
                    throw new IllegalStateException("valor vacio: " + option);
            }
        } while (option != 0);

    }

    public static void menuPlaylists(Scanner sc, PlayListService playListService) {
        int option;
        String name;
        UUID playlistId;
        UUID songId;

        do {
            System.out.println("--- Submenú Playlists ---");
            System.out.println("1. Crear playlist");
            System.out.println("2. Listar playlists");
            System.out.println("3. Agregar canción a playlist");
            System.out.println("4. Quitar canción de playlist");
            System.out.println("5. Ver canciones de playlist");
            System.out.println("6. Eliminar playlist");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("=== CREAR PLAYLIST ===");
                    System.out.print("Ingrese nombre: ");
                    name = sc.nextLine();
                    try {
                        playListService.createPlayList(name);
                        System.out.println("✓ Playlist creada exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("=== LISTAR PLAYLISTS ===");
                    List<PlayList> playlists = playListService.listPlaylists();
                    if (playlists.isEmpty()) {
                        System.out.println("No hay playlists cargadas");
                    } else {
                        for (PlayList p : playlists) {
                            System.out.println(p.toString());
                        }
                    }
                    break;

                case 3:
                    System.out.println("=== AGREGAR CANCIÓN A PLAYLIST ===");
                    System.out.print("Ingrese ID de la playlist: ");
                    try {
                        playlistId = UUID.fromString(sc.nextLine());
                        System.out.print("Ingrese ID de la canción: ");
                        songId = UUID.fromString(sc.nextLine());
                        playListService.addSongToPlayList(playlistId, songId);
                        System.out.println("✓ Canción agregada exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (PlaylistNotFoundException | SongNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("=== QUITAR CANCIÓN DE PLAYLIST ===");
                    System.out.print("Ingrese ID de la playlist: ");
                    try {
                        playlistId = UUID.fromString(sc.nextLine());
                        System.out.print("Ingrese ID de la canción: ");
                        songId = UUID.fromString(sc.nextLine());
                        playListService.removeSongOfPlayList(playlistId, songId);
                        System.out.println("✓ Canción quitada exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (PlaylistNotFoundException | SongNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("=== VER CANCIONES DE PLAYLIST ===");
                    System.out.print("Ingrese ID de la playlist: ");
                    try {
                        playlistId = UUID.fromString(sc.nextLine());
                        PlayList pl = playListService.getPlaylistById(playlistId).orElseThrow();
                        System.out.println("Playlist: " + pl.getName());
                        System.out.println("----------------------------------");
                        int i = 1;
                        for (Song s : pl.getSongs()) {
                            int mins = s.getDurationSeconds() / 60;
                            int secs = s.getDurationSeconds() % 60;
                            System.out.printf("%d. %s - %s (%d:%02d)%n", i++, s.getTitle(), s.getArtist(), mins, secs);
                        }
                        System.out.println("----------------------------------");
                        int total = playListService.getTotalDuration(playlistId);
                        System.out.printf("Duración total: %d:%02d%n", total / 60, total % 60);
                        System.out.println("Cantidad de canciones: " + pl.getSongs().size());
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (PlaylistNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("=== ELIMINAR PLAYLIST ===");
                    System.out.print("Ingrese ID de la playlist: ");
                    try {
                        playlistId = UUID.fromString(sc.nextLine());
                        playListService.deletePlayList(playlistId);
                        System.out.println("✓ Playlist eliminada exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido");
                    } catch (PlaylistNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (option != 0);
    }

}
