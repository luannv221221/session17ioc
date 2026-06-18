package persentation;

import business.MovieBusiness;
import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveManager {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("====================MENU================");
            System.out.println("1. Danh sach phim");
            System.out.println("2. thêm mới phim");
            System.out.println("3. cập nhật");
            System.out.println("4. xóa");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("Lua chon");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    displayAllMovies();
                    break;
                case 2:
                    createMovie();
                    break;
                case 5:
                    System.out.println("Nhap vao ten phim");
                    String name = sc.nextLine();
                    Movie m = MovieBusiness.getMoviesByName(name);
                    if (m != null) {
                        System.out.println(m.toString());
                    } else {
                        System.out.println("KHOng tim thay");
                    }
                    break;
                default:
                    System.err.println("Sai lua chon");
            }
        } while (true);
    }

    public static void displayAllMovies() {
        List<Movie> movies = MovieBusiness.getMovies();
        movies.forEach(System.out::println);
    }

    public static void createMovie() {
        Movie m = new Movie();
        System.out.println("Nhap vao thong tin ");
        m.inputData(sc);
        if(MovieBusiness.addMovie(m)){
            System.out.println("Them moi thanh cong");
        } else {
            System.err.println("Them moi that bai");
        }
    }
}
