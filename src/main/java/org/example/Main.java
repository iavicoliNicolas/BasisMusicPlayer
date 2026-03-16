package org.example;

import Classes.PlayList;
import Classes.PlayListService;
import Classes.Song;
import Classes.SongService;
import Enums.Genre;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Scanner sc1 = new Scanner(System.in);
        SongService songService = null;
        PlayListService playListService = null;
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
                    menuPlayList(sc1, playListService);
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
            option = sc.nextInt();
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
                    System.out.println("Seleccione género:");
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
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    throw new IllegalStateException("valor vacio: " + option);
            }
        } while (option != 0);

    }

    public static void menuPlayList(Scanner sc, PlayListService playListService) {

        int option;

        do {

            System.out.println("--- Submenú Playlists ---\n" +
                    "1. Crear playlist\n" +
                    "2. Listar playlists\n" +
                    "3. Agregar canción a playlist\n" +
                    "4. Quitar canción de playlist\n" +
                    "5. Ver canciones de playlist\n" +
                    "6. Eliminar playlist\n" +
                    "0. Volver\n" +
                    "Seleccione una opción: _");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    throw new IllegalStateException("valor vacio: " + option);
            }
        } while (option != 0);
    }

}
